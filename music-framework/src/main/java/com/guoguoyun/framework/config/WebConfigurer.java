package com.guoguoyun.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Author: wwy
 * @Date: 2020/12/29 13:33
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private AppApiInterceptor appApiInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(appApiInterceptor).addPathPatterns("/app/**").excludePathPatterns("/app/user/login",
                "/app/sms/send",
                "/app/index/**",
                "/app/userMessage/getRoleInfo/**",
                "/app/shop/**",
                "/app/userSearchLog/searchThing",
                "/app/userSearchLog/searchWordLog",
                "/common/upload",
                "/common/provinceList",
                "/common/cityList",
                "/app/userContribute/contributeList",
                "/app/userContribute/artistList",
                "/app/userContribute/artistAlbum",
                "/app/userContribute/artistDetail",
                "/app/userContribute/artistDynamicstate",
                "/app/contentArticle/articleIndex",
                "/app/contentArticle/articleIndexNew"
                );
    }
}
