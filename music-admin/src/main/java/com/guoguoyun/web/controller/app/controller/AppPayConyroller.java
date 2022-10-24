package com.guoguoyun.web.controller.app.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.params.OperationOrderParams;
import com.guoguoyun.web.controller.app.service.AppPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/app/pay")
public class AppPayConyroller {

    @Autowired
    private AppPayService appPayService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/unifiedOrder")
    public AjaxResult unifiedOrder(OperationOrderParams orderParams) {
        Long userId = tokenService.getLoginUser().getUserId();
        orderParams.setUserId(userId);
        return appPayService.addOrder(orderParams);
    }


    /**
     * 支付宝支付回调
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/alyNotify")
    public AjaxResult alyNotify(HttpServletRequest request, HttpServletResponse response) {
        String msg = appPayService.alyNotify(request, response);
        return AjaxResult.success(msg);
    }

    /**
     * 微信支付回调
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/wxNotify")
    public AjaxResult wxNotify(HttpServletRequest request, HttpServletResponse response)throws Exception {
        InputStream is = null;
        String xmlBack = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";
        try {
            is = request.getInputStream();
            // 将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            xmlBack = appPayService.wxNotify(sb.toString());
        } catch (Exception e) {

        } finally {

            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(xmlBack.getBytes());
            out.flush();
            out.close();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return AjaxResult.success(WXPayUtil.xmlToMap(xmlBack));
    }
}
