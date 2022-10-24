package com.guoguoyun.common.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: lmy
 * @Date: 2020/10/20 17:17
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "smsconfig")
public class SmsConfig {
    // 阿里云短信服务
    private String product;// 云通信短信API产品,无需替换
    private String domain;// 产品域名,无需替换
    private String accesskeyid;// AK key
    private String accesskeysecret;// AK value
    private String signname;// 短信签名
    private String chinase_templetecode;// 中文短信模板标识

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccesskeyid() {
        return accesskeyid;
    }

    public void setAccesskeyid(String accesskeyid) {
        this.accesskeyid = accesskeyid;
    }

    public String getAccesskeysecret() {
        return accesskeysecret;
    }

    public void setAccesskeysecret(String accesskeysecret) {
        this.accesskeysecret = accesskeysecret;
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

    public String getChinase_templetecode() {
        return chinase_templetecode;
    }

    public void setChinase_templetecode(String chinase_templetecode) {
        this.chinase_templetecode = chinase_templetecode;
    }

    @Override
    public String toString() {
        return "SmsConfig{" +
                "product='" + product + '\'' +
                ", domain='" + domain + '\'' +
                ", accesskeyid='" + accesskeyid + '\'' +
                ", accesskeysecret='" + accesskeysecret + '\'' +
                ", signname='" + signname + '\'' +
                ", chinase_templetecode='" + chinase_templetecode + '\'' +
                '}';
    }
}