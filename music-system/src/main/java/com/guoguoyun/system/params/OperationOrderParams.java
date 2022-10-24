package com.guoguoyun.system.params;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 运营管理-订单管理对象 operation_order
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
@Accessors(chain = true)
@Data
public class OperationOrderParams extends BaseEntity
{

    /**
     * 支付方式 1微信 2支付宝
     */
    @Excel(name = "支付方式")
    private Integer payType;

    /** 购买类型  1专辑 2歌曲 */
    @Excel(name = "购买类型 ")
    private Integer shopType;

    private Long shopId;

    /** 金额 */
    @Excel(name = "金额")
    private Double shopPrice;


    /** 购买者 */
    @Excel(name = "购买者")
    private Long userId;



}
