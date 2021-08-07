package com.nic.calculate.mybatisPlus.mapper;

import com.nic.calculate.mybatisPlus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Users and global privileges Mapper 接口
 * </p>
 *
 * @author nic
 * @since 2021-08-07
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
