package com.nic.calculate.controller;


import com.nic.calculate.help.BaseResponse;
import com.nic.calculate.regular.dto.BillDetailDto;
import com.nic.calculate.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController("BaseAPI")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @RequestMapping(name = "test", method = RequestMethod.POST, path = "/api/test")
    public BaseResponse testapi(@RequestBody List<BillDetailDto> list){
        return baseService.calculateTheBillResult(list);
    }

}
