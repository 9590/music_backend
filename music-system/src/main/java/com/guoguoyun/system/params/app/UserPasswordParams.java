package com.guoguoyun.system.params.app;

import com.guoguoyun.system.params.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserPasswordParams extends BaseParam {


    @NotBlank(message = "旧密码不能为空", groups = {BaseParam.edit.class})
    private String oldPassword;

    @NotBlank(message = "新密码不能为空", groups = {BaseParam.edit.class})
    private String newPasswordOne;

    @NotBlank(message = "新密码不能为空", groups = {BaseParam.edit.class})
    private String newPasswordTwo;

    private Long userId;
}
