package com.guoguoyun.system.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 运营管理-优惠卷发放管理对象 operation_user_coupon
 *
 * @author ruoyi
 * @date 2022-02-14
 */
@Accessors(chain = true)
@Data
public class OperationUserCouponParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 优惠券id */
    @Excel(name = "优惠券id")
    @NotNull(message = "优惠券id不能为空", groups = {BaseParam.add.class})
    private Long couponId;

    /** 用户id */
    @NotNull(message = "用户id不能为空", groups = {BaseParam.add.class})
    private List<Long> userIds;

    /** 有效期 */
    @Excel(name = "有效期")
    private Integer periodOfValidity;

    /** 是否长期 1为是 0为否 */
    @Excel(name = "是否长期 1为是 0为否")
    private Integer isLongTime;


    private Date startTime;

    private Date endTime;

    private Date useStartTime;

    private Date useEndTime;

    private Integer useState;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}
