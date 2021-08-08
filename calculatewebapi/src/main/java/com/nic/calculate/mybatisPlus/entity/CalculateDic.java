package com.nic.calculate.mybatisPlus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("calculate_Dic")
public class CalculateDic extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 字典code
     */
    private String dicCode;

    /**
     * 字典名称
     */
    private String dicName;

    /**
     * 排序
     */
    private Integer dicOrder;

    /**
     * 字典类型
     */
    private String dicType;

    /**
     * 字典描述
     */
    private String dicDescription;

    private String field0;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String field6;

    private String field7;

    private String field8;

    private String field9;

    private Integer isDelete;


}
