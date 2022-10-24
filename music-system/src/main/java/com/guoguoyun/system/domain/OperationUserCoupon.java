package com.guoguoyun.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 运营管理-优惠卷发放管理对象 operation_user_coupon
 *
 * @author ruoyi
 * @date 2022-02-15
 */
@Accessors(chain = true)
@Data
public class OperationUserCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 使用状态  */
    @Excel(name = "使用状态 ")
    private Integer useState;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 优惠券id */
    @Excel(name = "优惠券id")
    private Long couponId;

    /** 是否删除 1为是  0为否 */
    @Excel(name = "是否删除 1为是  0为否")
    private Integer isDelete;

    /** 是否长期 1为是 0为否 */
    @Excel(name = "是否长期 1为是 0为否")
    private Integer isLongTime;

    /** 使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** 有效期 */
    @Excel(name = "有效期")
    private Integer periodOfValidity;

    @TableField(exist = false)
    private Date startsTime;

    @TableField(exist = false)
    private Date endsTime;

    @TableField(exist = false)
    private Date useStartTime;

    @TableField(exist = false)
    private Date useEndTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setUseState(Integer useState)
    {
        this.useState = useState;
    }

    public Integer getUseState()
    {
        return useState;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setCouponId(Long couponId)
    {
        this.couponId = couponId;
    }

    public Long getCouponId()
    {
        return couponId;
    }
    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
    public void setIsLongTime(Integer isLongTime)
    {
        this.isLongTime = isLongTime;
    }

    public Integer getIsLongTime()
    {
        return isLongTime;
    }
    public void setUseTime(Date useTime)
    {
        this.useTime = useTime;
    }

    public Date getUseTime()
    {
        return useTime;
    }
    public void setPeriodOfValidity(Integer periodOfValidity)
    {
        this.periodOfValidity = periodOfValidity;
    }

    public Integer getPeriodOfValidity()
    {
        return periodOfValidity;
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
                .append("endTime", getEndTime())
                .append("useState", getUseState())
                .append("userId", getUserId())
                .append("couponId", getCouponId())
                .append("isDelete", getIsDelete())
                .append("isLongTime", getIsLongTime())
                .append("useTime", getUseTime())
                .append("periodOfValidity", getPeriodOfValidity())
                .toString();
    }
}
