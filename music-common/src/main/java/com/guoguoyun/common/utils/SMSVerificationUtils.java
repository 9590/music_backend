package com.guoguoyun.common.utils;

import com.alibaba.fastjson.JSONObject;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.pagehelper.util.StringUtil;
import com.guoguoyun.common.core.redis.RedisCache;
import com.guoguoyun.common.model.AlyConfigFile;
import com.guoguoyun.common.model.SmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMSVerificationUtils {
    protected static Logger smsLogger= LoggerFactory.getLogger("SMSVerificationUtils");

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SmsConfig smsConfig;

    public SendSmsResponse sendSms(String telephone, String code) throws ClientException {
        AlyConfigFile aly = null;
        if (aly == null) {
            aly = new AlyConfigFile();
            aly.setAccesskeyid(smsConfig.getAccesskeyid());
            aly.setProduct(smsConfig.getProduct());
            aly.setDomain(smsConfig.getDomain());
            aly.setAccesskeysecret(smsConfig.getAccesskeysecret());
            aly.setSignname(smsConfig.getSignname());
            aly.setChinaseTempletecode(smsConfig.getChinase_templetecode());
        }
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aly.getAccesskeyid(),
                aly.getAccesskeysecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", aly.getProduct(),
                aly.getDomain());
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        smsLogger.info("格式化手机号 :  00 + 国际区号 + 号码   ==========>"+"   telphone :"+ telephone);
        request.setPhoneNumbers(telephone);// 接收号码格式为00+国际区号+号码
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(aly.getSignname());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(aly.getChinaseTempletecode());

        request.setTemplateCode(aly.getChinaseTempletecode());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        if(!StringUtil.isEmpty(code)){
            request.setTemplateParam("{\"code\":\"" + code + "\"}");
        }
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        smsLogger.info("阿里云短信服务回执详情："+ JSONObject.toJSONString(sendSmsResponse));
        if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            smsLogger.info("阿里云短信服务回执详情 短信发送成功！");
            //saveSMSToDB(request.getPhoneNumbers(), areaCode, code, request.getSignName()+request.getTemplateCode(), sendSmsResponse.getRequestId());
        } else {
            smsLogger.info("阿里云短信服务回执详情 短信发送失败！");
        }
        return sendSmsResponse;

    }

}