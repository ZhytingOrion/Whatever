package com.nic.calculate.service;

import com.nic.calculate.help.BaseResponse;
import com.nic.calculate.mybatisPlus.entity.User;
import com.nic.calculate.mybatisPlus.mapper.UserMapper;
import com.nic.calculate.mybatisPlus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseService {

    @Autowired
    private UserServiceImpl userService;
    public BaseResponse getTestData() {
        BaseResponse<Object> response = new BaseResponse<>();
        List<User> list = userService.list();
        response.setData(list);
        response.setSuccess(true);
        return response;
    }
}
