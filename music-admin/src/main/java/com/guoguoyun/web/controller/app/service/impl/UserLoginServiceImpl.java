package com.guoguoyun.web.controller.app.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.constant.CommonCode;
import com.guoguoyun.common.constant.Constants;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.core.domain.entity.SysUser;
import com.guoguoyun.common.core.domain.model.LoginUser;
import com.guoguoyun.common.core.redis.RedisCache;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.http.HttpUtils;
import com.guoguoyun.common.utils.uuid.IdUtils;
import com.guoguoyun.framework.web.exception.ExceptionCast;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.params.app.UserLoginParams;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.web.controller.app.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    private static final String TOKEN = "Authorization";


    /**
     * 登录接口
     *  登录方式 1账号密码  2手机验证码  3微信 4qq 5微博
     * @param userLoginParams
     * @return
     */
    @Override
    public Map<String, Object> login(UserLoginParams userLoginParams) {
        Map<String, Object> map = null;
        switch (userLoginParams.getType()){
            case 1:
                map = this.accountAndPassword(userLoginParams);
                break;
            case 2:
                map = this.accountAndCode(userLoginParams);
                break;
            case 3:
                map = this.wxLogin(userLoginParams);
                break;
            case 4:break;
            case 5:break;
            default:
                ExceptionCast.cast(CommonCode.LOGIN_NOT);
                break;
        }
        return map;
    }


    /**
     * 账号密码登录
     * @param userLoginParams
     * @return
     */
    private Map<String, Object> accountAndPassword(UserLoginParams userLoginParams){
        QueryWrapper<AppUser> appUserQueryWrapper = new QueryWrapper<>();
        appUserQueryWrapper.lambda().eq(AppUser::getUserAccount,userLoginParams.getUserAccount());
        AppUser appUser = appUserService.getOne(appUserQueryWrapper);

        if (ObjectUtil.isEmpty(appUser)){
            ExceptionCast.cast(CommonCode.USERNAME_NOT_EXIST);
        }
        if (ObjectUtil.isEmpty(appUser.getPassword())){
            ExceptionCast.cast(CommonCode.PASSWORD_NULL_ERROR);
        }
        if (!appUser.getPassword().equals(userLoginParams.getPassword())){
            ExceptionCast.cast(CommonCode.LOGIN_PASSWORD_ERROR);
        }
        appUser.setLastLoginTime(DateUtils.getNowDate());
        appUserService.updateById(appUser);
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(new SysUser());
        loginUser.setUserId(appUser.getId());
        String token = tokenService.createToken(loginUser);
        Map<String, Object> map = new HashMap<>(3);
        map.put(TOKEN,"Bearer "+token);
        map.put("user",appUser);
        return map;
    }


    /**
     * 手机号验证码登录
     * @param userLoginParams
     * @return
     */
    private Map<String,Object> accountAndCode(UserLoginParams userLoginParams){
//        String code = redisCache.getCacheObject(Constants.RedisPrefixKey + ":" + userLoginParams.getUserAccount());
//        if (code == null){
//            ExceptionCast.cast(CommonCode.SMSCODE_EXPIRES);
//        }
//        if (!userLoginParams.getCode().equals(code)){
//            ExceptionCast.cast(CommonCode.SMSCODE_ERROR);
//        }

        Map<String, Object> map = new HashMap<>();
        QueryWrapper<AppUser> appUserQueryWrapper = new QueryWrapper<>();
        appUserQueryWrapper.lambda().eq(AppUser::getUserAccount,userLoginParams.getUserAccount());
        AppUser appUser = appUserService.getOne(appUserQueryWrapper);
        if (ObjectUtil.isNotEmpty(appUser)){
            appUser.setLastLoginTime(DateUtils.getNowDate());
            appUserService.updateById(appUser);
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(new SysUser());
            loginUser.setUserId(appUser.getId());
            String token = tokenService.createToken(loginUser);
            map.put(TOKEN,"Bearer "+token);
            map.put("user",appUser);
        }else {
            AppUser user = new AppUser();
            user.setId(IdUtil.getSnowflakeNextId());
            user.setCreateTime(DateUtils.getNowDate());
            user.setLastLoginTime(DateUtils.getNowDate());
            user.setUserAccount(userLoginParams.getUserAccount());
            user.setUserAvatar("http://localhost:8094/profile/upload/2022/03/01/f8344adb-0799-4e5a-9f89-b3eaae4e6e07.jpg");
            user.setUserName("果粉"+userLoginParams.getUserAccount().substring(userLoginParams.getUserAccount().length()-6));
            appUserService.save(user);
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(new SysUser());
            loginUser.setUserId(user.getId());
            String token = tokenService.createToken(loginUser);
            map.put(TOKEN,"Bearer "+token);
            map.put("user",user);
        }

        return map;
    }


    /**
     * 微信登录
     * @param userLoginParams
     * @return
     */
    private Map<String,Object> wxLogin(UserLoginParams userLoginParams){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +  "wx592ebedd9e1f0778" +
                "&secret=" +  "3885b3b6478a95176b5c4abdab858deb" +
                "&code=" + userLoginParams.getCode() +
                "&grant_type=authorization_code";

        String sendGet = HttpUtils.sendGet(url);
        JSONObject json = JSONObject.parseObject(sendGet);
        if ("40029".equals(json.getString("errcode"))) {
            throw new SecurityException("code无销");
        }

        String openId = json.getString("openid");
        String accessToken = json.getString("access_token");
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken +
                "&openid=" + openId +
                "&lang=zh_CN";
        String userInfoJson = HttpUtils.sendGet(url);
        JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
        if(String.valueOf(jsonObject.get("errcode")).equals("40014")){
            throw new SecurityException("微信登录失败");
        }

        QueryWrapper<AppUser> appUserQueryWrapper = new QueryWrapper<>();
        appUserQueryWrapper.lambda().eq(AppUser::getWxOpenid,openId);
        AppUser user = appUserService.getOne(appUserQueryWrapper);
        Map<String, Object> map = new HashMap<>();
        if (ObjectUtil.isNotEmpty(user)){
            user.setLastLoginTime(DateUtils.getNowDate());
            appUserService.updateById(user);
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(new SysUser());
            loginUser.setUserId(user.getId());
            String token = tokenService.createToken(loginUser);
            map.put(TOKEN,"Bearer "+token);
            map.put("user",user);
        }else {
            AppUser appUser = new AppUser();
            appUser.setWxOpenid(jsonObject.getString("openid"));
            appUser.setUserName(jsonObject.getString("nickname"));
            appUser.setUserAvatar(jsonObject.getString("headimgurl"));
            appUser.setSex(jsonObject.getString("sex"));
            appUser.setCityName(jsonObject.getString("province"));
            appUser.setAreaName(jsonObject.getString("city"));
            appUser.setId(IdUtil.getSnowflakeNextId());
            appUser.setCreateTime(DateUtils.getNowDate());
            appUser.setLastLoginTime(DateUtils.getNowDate());
            appUserService.save(appUser);
            LoginUser loginUser = new LoginUser();
            loginUser.setUser(new SysUser());
            loginUser.setUserId(appUser.getId());
            String token = tokenService.createToken(loginUser);
            map.put(TOKEN,"Bearer "+token);
            map.put("user",appUser);
        }
        return map;
    }
}
