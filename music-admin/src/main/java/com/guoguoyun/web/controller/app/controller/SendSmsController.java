package com.guoguoyun.web.controller.app.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.util.StringUtil;
import com.guoguoyun.common.constant.CommonCode;
import com.guoguoyun.common.constant.Constants;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.redis.RedisCache;
import com.guoguoyun.common.utils.SMSVerificationUtils;
import com.guoguoyun.framework.web.exception.ExceptionCast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/app/sms")
public class SendSmsController {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SMSVerificationUtils smsVerificationUtils;

    @GetMapping("/send")
    public AjaxResult sendSms(String phone) {


        String verifyCodeFlagKey = Constants.SYS_CONFIG_KEY + "SMS";

        if (redisCache.hasKey(verifyCodeFlagKey)) {
            ExceptionCast.cast(CommonCode.SEND_SMSCODE_FREQUENTLY);
        }


        // 生成验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);

        // 存储短信
        String verifyCodeKey = Constants.RedisPrefixKey + ":" + phone;

        redisCache.setCacheObject(verifyCodeKey, verifyCode);
        redisCache.expire(verifyCodeKey,  TimeUnit.SECONDS.toMillis(Constants.SmsCodeExpires));
        redisCache.setCacheObject(verifyCodeFlagKey, verifyCode);
        redisCache.expire(verifyCodeFlagKey ,Constants.FlagExpires);

        // 发送短信
        SendSmsResponse sendSms = null;
        try {
            LOGGER.info("------开始发送验证码------");
            sendSms = smsVerificationUtils.sendSms(phone, verifyCode);
        } catch (ClientException e) {
            String message = e.getMessage();
            if (message.equals("isv.DAY_LIMIT_CONTROL")) {
                ExceptionCast.cast(CommonCode.DAY_LIMIT_CONTROL);
            } else if (message.equals("isv.MOBILE_NUMBER_ILLEGAL")) {
                ExceptionCast.cast(CommonCode.MOBILE_NUMBER_ILLEGAL);
            } else if (message.equals("isv.BUSINESS_LIMIT_CONTROL")) {
                ExceptionCast.cast(CommonCode.BUSINESS_LIMIT_CONTROL);
            } else {
                ExceptionCast.cast(CommonCode.UNKNOWN_ERROR);
            }
        }
        if (!StringUtil.isEmpty(sendSms.getCode()) && !"OK".equals(sendSms.getCode()))
            ExceptionCast.cast(CommonCode.SEND_SMSCODE_ERROR);
        if (null == sendSms && !"OK".equals(sendSms.getCode()))
            ExceptionCast.cast(CommonCode.SEND_SMSCODE_ERROR);
        return AjaxResult.success();

    }
}