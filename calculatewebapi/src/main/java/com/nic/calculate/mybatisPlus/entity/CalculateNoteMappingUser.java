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
@TableName("calculate_Note_Mapping_User")
public class CalculateNoteMappingUser extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 记账id
     */
    private String noteId;

    /**
     * 待付款金额
     */
    private Double needPayFund;

    /**
     * 待付款用户id
     */
    private String needPayUserId;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


}
