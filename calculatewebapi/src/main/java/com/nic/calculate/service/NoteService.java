package com.nic.calculate.service;

import com.nic.calculate.help.BaseResponse;
import com.nic.calculate.mybatisPlus.entity.CalculateBill;
import com.nic.calculate.mybatisPlus.entity.CalculateBillMappingUser;
import com.nic.calculate.mybatisPlus.service.impl.CalculateBillMappingUserServiceImpl;
import com.nic.calculate.mybatisPlus.service.impl.CalculateBillServiceImpl;
import com.nic.calculate.regular.request.SignBillInfoRequest;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NoteService {

    @Autowired
    private CalculateBillServiceImpl calculateBillService;

    @Autowired
    private CalculateBillMappingUserServiceImpl calculateBillMappingUserService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public BaseResponse<Object> signBillInfo(SignBillInfoRequest request) {
        BaseResponse<Object> response = new BaseResponse<>();
        String operator = httpServletRequest.getHeader("userId");
        //sign into bill table
        CalculateBill billEntity = new CalculateBill();
        billEntity.setId(UUID.randomUUID() + "");
        billEntity.setBillCount(request.getBillCount());
        billEntity.setBillName(request.getBillName());
        billEntity.setBeginTime(request.getBeginDay());
        billEntity.setCreateUserId(operator);
        billEntity.setOriginFund(request.getOriginFund());

        boolean saveBillSuccess = calculateBillService.save(billEntity);
        if (!saveBillSuccess){
            response.setMessage(String.format("save bill info is fail. entity : [%s]", JSON.toString(billEntity)));
            return response;
        }

        //sign alis mapping user info table
        List<CalculateBillMappingUser> billMappingUsers = new ArrayList<>();
        request.getAliasNames().forEach(aliasDto -> {
            CalculateBillMappingUser billMappingUser = new CalculateBillMappingUser();
            billMappingUser.setId(UUID.randomUUID()+"");
            billMappingUser.setBillId(billEntity.getId());
            billMappingUser.setAliasName(aliasDto.getAliasName());
            billMappingUser.setOrder(aliasDto.getOrderNumber());
            if (aliasDto.getOrderNumber() == 0){
                // default index =0 is current user
                billMappingUser.setUserId(operator);
            }
            billMappingUsers.add(billMappingUser);
        });

        boolean saveMappingUserSuccess = calculateBillMappingUserService.saveBatch(billMappingUsers);
        if (!saveMappingUserSuccess){
            response.setMessage(String.format("save bill mapping user info is fail. entity : [%s]", JSON.toString(billMappingUsers)));
            billEntity.setIsDelete(1);
            calculateBillService.save(billEntity);
            return response;
        }
        //check response
        response.setSuccess(true);
        return response;
    }
}
