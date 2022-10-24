package com.guoguoyun.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlyConfigFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    private String product;

    private String domain;

    private String accesskeyid;

    private String accesskeysecret;

    private String signname;

    private String chinaseTempletecode;

    private String englishTempletecode;

    private String cloudWalletVerification;

    private String cloudWalletNotification;

    private String endpoint;

    private String bucketName;


    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getChinaseTempletecode() {
        return chinaseTempletecode;
    }

    public void setChinaseTempletecode(String chinaseTempletecode) {
        this.chinaseTempletecode = chinaseTempletecode;
    }

    public String getEnglishTempletecode() {
        return englishTempletecode;
    }

    public void setEnglishTempletecode(String englishTempletecode) {
        this.englishTempletecode = englishTempletecode;
    }

    public String getCloudWalletVerification() {
        return cloudWalletVerification;
    }

    public void setCloudWalletVerification(String cloudWalletVerification) {
        this.cloudWalletVerification = cloudWalletVerification;
    }

    public String getCloudWalletNotification() {
        return cloudWalletNotification;
    }

    public void setCloudWalletNotification(String cloudWalletNotification) {
        this.cloudWalletNotification = cloudWalletNotification;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}