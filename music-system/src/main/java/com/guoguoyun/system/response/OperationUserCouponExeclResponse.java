package com.guoguoyun.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 运营管理-优惠卷发放管理对象 operation_user_coupon
 *
 * @author ruoyi
 * @date 2022-02-14
 */
@Accessors(chain = true)
@Data
public class OperationUserCouponExeclResponse
{
    private static final long serialVersionUID = 1L;

    /** 使用状态  */
    @Excel(name = "使用状态 ")
    private String useState;

    @Excel(name = "优惠券类型")
    private String couponName;

    /** 抵扣金额 */
    @Excel(name = "抵扣金额（元）")
    private Double couponDeduction;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "发放时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "失效时间 ")
    private String endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date useTime;

    /** 用户昵称 */
    @Excel(name = "用户")
    private String userName;







}
