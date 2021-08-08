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
@TableName("calculate_Bill")
public class CalculateBill extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 创建者id
     */
    private String createUserId;

    /**
     * 账单名称
     */
    private String billName;

    /**
     * 账单人数
     */
    private Integer billCount;

    /**
     * 团体资金（初始资金）
     */
    private Double originFund;

    /**
     * 账单开始时间
     */
    private Date beginTime;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    public CalculateBill() {
        this.createTime = new Date(System.currentTimeMillis());
        this.updateTime = new Date(System.currentTimeMillis());
        this.isDelete = 0;
    }
}
