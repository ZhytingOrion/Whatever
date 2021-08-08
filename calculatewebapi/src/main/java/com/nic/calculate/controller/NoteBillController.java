package com.nic.calculate.controller;


import com.nic.calculate.help.BaseHelp;
import com.nic.calculate.help.BaseResponse;
import com.nic.calculate.regular.request.SignBillInfoRequest;
import com.nic.calculate.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RestController("NoteBillAPI")
public class NoteBillController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(name = "signBillInfo", method = RequestMethod.POST, path = "/api/notebill/signinfo")
    public BaseResponse signBillInfo(@RequestBody SignBillInfoRequest request) {
        BaseResponse<Object> response = new BaseResponse<>();

        if (request == null) {
            response.setMessage("Request parameters cannot be empty, Please check");
            return response;
        }
        if (StringUtils.isEmpty(request.getBillName())) {
            response.setMessage("Request parameters [BillName] cannot be empty, Please check");
            return response;
        }
        if (request.getAliasNames() == null || request.getAliasNames().size() == 0) {
            response.setMessage("Request parameters [AliasNames] cannot be empty, Please check");
            return response;
        }
        if (request.getAliasNames().size() != request.getBillCount()) {
            response.setMessage("Error in request parameters, please check");
            return response;
        }
        if (StringUtils.isEmpty(request.getBeginDay())) {
            request.setBeginDay(new Date());
        }

        response = noteService.signBillInfo(request);
        response.setMessage(String.format("%s! [%s]", response.getSuccess() ? "Success" : "Fail", response.getMessage()));
        return response;
    }
}
