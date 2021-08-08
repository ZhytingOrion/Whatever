package com.nic.calculate.mybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author nic
 * @since 2021-08-08
 */
@Data
@TableName("calculate_Bill_Mapping_User")
public class CalculateBillMappingUser extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 账单id
     */
    private String billId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称、别名
     */
    private String aliasName;

    private Integer order;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


    public CalculateBillMappingUser() {
        this.createTime = new Date(System.currentTimeMillis());
        this.updateTime = new Date(System.currentTimeMillis());
        this.isDelete = 0;
    }


}
