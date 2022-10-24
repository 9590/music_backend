package com.guoguoyun.system.response;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 运营管理-优惠卷管理对象 operation_coupon
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Accessors(chain = true)
@Data
public class OperationCouponResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    private String couponName;

    /** 抵扣金额 */
    @Excel(name = "抵扣金额")
    private Double couponDeduction;

    /** 抵扣规则 */
    @Excel(name = "抵扣规则")
    private Double deductionRules;

    /** 适用类型 */
    @Excel(name = "适用类型")
    private String couponTypeName;


    /** 状态 1为启用 2为禁用 */
    @Excel(name = "状态 1为启用 2为禁用")
    private String couponStateName;

}
