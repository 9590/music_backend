package com.guoguoyun.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 运营管理-优惠卷管理对象 operation_coupon
 *
 * @author ruoyi
 * @date 2022-02-15
 */
@Accessors(chain = true)
@Data
public class OperationCoupon extends BaseEntity
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

    /** 状态 1为启用 2为禁用 */
    @Excel(name = "状态 1为启用 2为禁用")
    private Integer couponState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date endTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCouponName(String couponName)
    {
        this.couponName = couponName;
    }

    public String getCouponName()
    {
        return couponName;
    }
    public void setCouponDeduction(Double couponDeduction)
    {
        this.couponDeduction = couponDeduction;
    }

    public Double getCouponDeduction()
    {
        return couponDeduction;
    }
    public void setDeductionRules(Double deductionRules)
    {
        this.deductionRules = deductionRules;
    }

    public Double getDeductionRules()
    {
        return deductionRules;
    }
    public void setCouponState(Integer couponState)
    {
        this.couponState = couponState;
    }

    public Integer getCouponState()
    {
        return couponState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("couponName", getCouponName())
                .append("couponDeduction", getCouponDeduction())
                .append("deductionRules", getDeductionRules())
                .append("couponState", getCouponState())
                .toString();
    }
}
