package com.guoguoyun.framework.config;

import com.guoguoyun.common.constant.HttpStatus;
import com.guoguoyun.common.core.domain.model.LoginUser;
import com.guoguoyun.common.exception.ServiceException;
import com.guoguoyun.framework.web.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wwy
 * @Date: 2020/12/29 13:26
 */
@Component
public class AppApiInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //进行token验证
        LoginUser loginUser = tokenService.getLoginUser(request);
        if(loginUser != null){
            //刷新token时间
            tokenService.verifyToken(loginUser);
            LOGGER.info("-----开始进行token校验-----");
            request.setAttribute("token",loginUser.getToken());
        }else{
            throw new ServiceException("没有权限", HttpStatus.FORBIDDEN);
        }
        return true;
    }
}
