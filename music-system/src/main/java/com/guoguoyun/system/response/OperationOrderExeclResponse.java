package com.guoguoyun.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 运营管理-订单管理对象 operation_order
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
@Accessors(chain = true)
@Data
public class OperationOrderExeclResponse
{
    private static final long serialVersionUID = 1L;

    /** 主键/订单编号 */
    @Excel(name = "订单编号")
    private Long id;

    /** 支付编号 */
    @Excel(name = "支付编号")
    private String payNumber;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String payType;

    /** 购买类型 */
    @Excel(name = "购买类型")
    private String shopType;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String shopName;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String writer;

    /** 金额 */
    @Excel(name = "金额")
    private Double shopPrice;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "购买时间" , width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
