package com.guoguoyun.web.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author
 * @Description
 * @create
 * Version 1.0
 */
@Component
@Data
public class AlipayConfig {
    // 应用APPID
    @Value("${alyPay.appId}")
    public String appId;

    // 生成公钥时对应的私钥（填自己的）
    @Value("${alyPay.privateKey}")
    public String privateKey;

    //异步回调接口:得放到服务器上，且使用域名解析 IP
    @Value("${alyPay.notifyUrl}")
    public String notifyUrl;



    //编码类型
    public String charset = "UTF-8";

    //数据类型
    public String format = "json";

    // 公钥
    @Value("${alyPay.publicKey}")
    public String publicKey;

    //签名类型
    public String signtype = "RSA2";

    // 应用公钥证书路径
    private String app_cert_path;

    // 支付宝公钥证书路径
    private String alipay_cert_path;
    // 支付宝根证书路径
    private String alipay_root_cert_path;

    // 订单关闭交易时间
    private String timeoutExpress = "30m";

    // 销售产品码  电脑网站支付固定为:FAST_INSTANT_TRADE_PAY   APP支付:QUICK_MSECURITY_PAY
    private String productCode = "QUICK_MSECURITY_PAY";




}
