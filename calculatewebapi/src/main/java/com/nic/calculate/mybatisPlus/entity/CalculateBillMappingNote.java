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
@TableName("calculate_Bill_Mapping_Note")
public class CalculateBillMappingNote extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 账单id
     */
    private String billId;

    /**
     * 记账内容
     */
    private String noteName;

    /**
     * 记录时间。Stirng字段存储，怎么存怎么分组用
     */
    private String noteTime;

    /**
     * 记账金额
     */
    private Double fund;

    /**
     * 记账类型
     */
    private String noteType;

    /**
     * 该笔帐支付人
     */
    private String fundPayUser;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;


}
