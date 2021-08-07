package com.nic.calculate.mybatisPlus.service.impl;

import com.nic.calculate.mybatisPlus.entity.User;
import com.nic.calculate.mybatisPlus.mapper.UserMapper;
import com.nic.calculate.mybatisPlus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Users and global privileges 服务实现类
 * </p>
 *
 * @author nic
 * @since 2021-08-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
