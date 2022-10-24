package com.guoguoyun.web.controller.app.controller;

import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.domain.model.LoginUser;
import com.guoguoyun.common.utils.ServletUtils;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.app.UserLoginParams;
import com.guoguoyun.web.controller.app.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app/user")
public class UserLoginController extends BaseController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Validated(BaseParam.login.class) UserLoginParams userLoginParams){
        Map<String, Object> login = userLoginService.login(userLoginParams);
        return AjaxResult.success(login);
    }
}
