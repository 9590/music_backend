package com.guoguoyun.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 运营管理-优惠卷发放管理对象 operation_user_coupon
 *
 * @author ruoyi
 * @date 2022-02-14
 */
@Accessors(chain = true)
@Data
public class OperationUserCouponResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;


    private String endTime;

    /** 使用状态  */
    @Excel(name = "使用状态 ")
    private String useState;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userName;

    private String couponName;

    /** 抵扣金额 */
    @Excel(name = "抵扣金额")
    private Double couponDeduction;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date useTime;


}
