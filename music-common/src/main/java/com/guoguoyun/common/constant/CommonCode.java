package com.guoguoyun.common.constant;

public enum CommonCode implements ResultCode {
    OPENId_NOT(500,"openId为空值"),
    LOGIN_NOT(500,"选择正确的登录方式"),
    GOLD_NOT(500,"您的金币不足，请完成任务获得更多金币"),
    INVITECOD_NOT(500,"邀请码不存在"),
    USER_STATUS_ERROR(500,"该账号已被封禁"),
    REMINDTIME_ERROR(500,"选择的提前时间有误"),
    WITHDRAW_NOT(500,"您还不是会员"),
    UPPERLIMIT_NOT(500,"该物品兑换已上限"),
    BALANCE_NOT(500,"余额不足"),
    INVITECOD_ERROR(500,"该邀请码当天邀请次数超过上限"),
    SWITCH_MONEY_NOT(500,"请填200的倍数"),
    OPENId_ERROR(500,"openId无效"),
    OPENId_NULL_ERROR(500,"请先进行微信授权"),
    USERNAME_NOT_EXIST(500,"用户不存在"),
    LOGIN_PASSWORD_ERROR(500,"密码输入错误"),
    SMSCODE_EXPIRES(500,"验证码已过期"),
    SMSCODE_ERROR(500,"验证码错误"),
    UPDATE_AVATAR_ERROR(500,"上传头像不能为空"),
    PASSWORD_NULL_ERROR(500,"该用户没有设置密码"),
    PHONE_ERROR(500,"手机号已存在"),
    UPDATE_NEWPASSWORD_ERROR(500,"两个新密码不一致"),
    SEND_SMSCODE_FREQUENTLY(500,"发送验证码频繁"),
    DAY_LIMIT_CONTROL(500,"触发日发送限额"),
    UNKNOWN_ERROR(500,"短信发送频率超限"),
    MOBILE_NUMBER_ILLEGAL(500,"非法手机号"),
    BUSINESS_LIMIT_CONTROL(500,"短信发送频率超限"),
    SEND_SMSCODE_ERROR(500,"短信发送失败"),
    NULL_PRICE(500,"无记录"),
    PRODUCT_NOT_EXIST(500,"产品不存在"),
    CODE_ERROR(500,"code无效");


    //操作代码
    int code;
    //提示信息
    String message;
    private CommonCode(int code, String message){

        this.code = code;
        this.message = message;
    }
    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    @Override
    public String toString() {
        return "CommonCode{" +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}