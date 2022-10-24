package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 运营管理-优惠卷管理对象 operation_coupon
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Accessors(chain = true)
@Data
public class OperationCouponParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class})
    private Long id;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    @NotBlank(message = "优惠券名称不能为空", groups = {BaseParam.edit.class,add.class})
    private String couponName;

    /** 抵扣金额 */
    @Excel(name = "抵扣金额")
    @NotNull(message = "抵扣金额不能为空", groups = {BaseParam.edit.class,add.class})
    private Double couponDeduction;

    /** 抵扣规则 */
    @Excel(name = "抵扣规则")
    @NotNull(message = "抵扣规则不能为空", groups = {BaseParam.edit.class,add.class})
    private Double deductionRules;



    /** 状态 1为启用 2为禁用 */
    @Excel(name = "状态 1为启用 2为禁用")
    @NotNull(message = "状态不能为空", groups = {BaseParam.edit.class})
    private Integer couponState;


}
