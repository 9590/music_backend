package com.guoguoyun.web.controller.app.service;

import com.guoguoyun.system.params.app.UserLoginParams;

import java.util.Map;

public interface UserLoginService {


    public Map<String,Object> login(UserLoginParams userLoginParams);
}
