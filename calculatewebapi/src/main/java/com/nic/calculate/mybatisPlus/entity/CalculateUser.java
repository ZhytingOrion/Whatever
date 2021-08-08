package com.nic.calculate.mybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author nic
 * @since 2021-08-08
 */
@Data
@TableName("calculate_User")
public class CalculateUser extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 微信昵称
     */
    private String nicName;

    /**
     * 微信唯一id
     */
    private String unionId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户头像
     */
    private String userHeadImg;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


}
