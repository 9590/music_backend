package com.guoguoyun.system.params.app;


import com.guoguoyun.system.params.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserLoginParams extends BaseParam {

    //账号
    private String userAccount;

    //密码
    private String password;

    //验证码或微信code
    private String code;

    //登录方式 1手机密码  2手机验证码  3微信 4qq 5微博
    @NotNull(message = "登录方式不能为空", groups = {login.class})
    private Integer type;
}
