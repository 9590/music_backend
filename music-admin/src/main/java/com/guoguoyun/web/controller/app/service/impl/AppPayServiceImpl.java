package com.guoguoyun.web.controller.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.enums.PayType;
import com.guoguoyun.common.utils.uuid.IdUtils;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.params.OperationOrderParams;
import com.guoguoyun.system.service.IAlbumService;
import com.guoguoyun.system.service.IAlbumSongService;
import com.guoguoyun.system.service.IOperationIndexService;
import com.guoguoyun.system.service.IOperationOrderService;
import com.guoguoyun.web.controller.app.service.AppPayService;
import com.guoguoyun.web.core.config.AlipayConfig;
import com.guoguoyun.web.core.config.WxpayConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class AppPayServiceImpl implements AppPayService {

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private IOperationOrderService operationOrderService;

    @Autowired
    private WxpayConfig wxpayConfig;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IAlbumSongService albumSongService;

    @Override
    public AjaxResult addOrder(OperationOrderParams order) {
        OperationOrder operationOrder = BeanUtil.toBean(order, OperationOrder.class);
        operationOrder.setType(order.getShopType());
        operationOrder.setPayNumber(IdUtils.orderNoId());

        if (operationOrder.getType() == 1){
            Album album = albumService.getById(operationOrder.getShopId());
            operationOrder.setShopName(album.getAlbumName());
            operationOrder.setShopType(album.getAlbumClassifyId());
            operationOrder.setWriter(album.getAlbumNumber());
        }else {
            AlbumSong song = albumSongService.getById(operationOrder.getShopId());
            operationOrder.setShopName(song.getSongName());
            operationOrder.setWriter(song.getSongNumber());

        }
        operationOrderService.insertOperationOrder(operationOrder);
        if (operationOrder.getPayType() == PayType.WX.getCode()) {
            return this.wxPay(operationOrder);
        } else {
            return this.alyPay(operationOrder);
        }
    }

    @Override
    public AjaxResult alyPay(OperationOrder order) {
        //????????????
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(), alipayConfig.getFormat(),
                alipayConfig.getCharset(), alipayConfig.getPublicKey(),
                alipayConfig.getSigntype());

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        // ????????????
        model.setBody(order.getRemark());
        // ????????????
        model.setSubject(order.getShopName());
        // ????????????????????????
        model.setOutTradeNo(order.getPayNumber());
        // ??????????????????????????????  ?????????????????????
        model.setTimeoutExpress(alipayConfig.getTimeoutExpress());
        // ????????????
        model.setTotalAmount(order.getShopPrice().toString());
        // ???????????????  ???????????????????????????:FAST_INSTANT_TRADE_PAY   APP??????:QUICK_MSECURITY_PAY
        model.setProductCode(alipayConfig.getProductCode());
        request.setBizModel(model);
        AlipayTradeAppPayResponse response = null;
        try {
            response = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //??????????????????????????????
        if (response.getCode().equals("10000")) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }


    }

    /**
     * ?????????????????????
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String alyNotify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = new HashMap<String, String>();
        String result = "failure";
        //?????????????????????request????????????
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // ????????????????????????????????????????????????????????????mysign???sign??????????????????????????????????????????
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCertCheckV1(params, alipayConfig.getPublicKey(), alipayConfig.getCharset(), "RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

//???????????????
        String outTradeNo = request.getParameter("out_trade_no"); // ???????????????
        String tradeNo = request.getParameter("trade_no"); // ?????????
        // ??????????????????
        String tradeStatus = request.getParameter("trade_status"); //????????????
        // ????????????
        String totalAmount = request.getParameter("total_amount"); //????????????
        // ????????????
        String payDate = request.getParameter("gmt_payment"); //????????????
        QueryWrapper<OperationOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.lambda().eq(OperationOrder::getPayNumber, outTradeNo);
        OperationOrder order = operationOrderService.getOne(orderQueryWrapper);
        order.setTradeNo(tradeNo);
        // ?????????????????????
        if (signVerified) {// ????????????
            if (tradeStatus.equals("TRADE_SUCCESS")) {  // ??????????????????????????????
                order.setPayState(MusicCode.PAY_STATUS_SUCCESS);

                //?????????????????????????????????????????????

                result = "success";
            } else {
                order.setPayState(MusicCode.PAY_STATUS_LOSE);
                result = "failure";
            }
        } else {
            order.setPayState(MusicCode.PAY_STATUS_LOSE);
            result = "failure";
        }
        order.setUpdateTime(new Date());
        operationOrderService.updateById(order);
        return result;
    }

    /**
     * ??????????????????
     *
     * @param notifyStr
     * @return
     */
    @Override
    public String wxNotify(String notifyStr) {
        String xmlBack = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        WXPay wxpayApp = new WXPay(wxpayConfig);
        try {
            Map<String, String> resultMap = WXPayUtil.xmlToMap(notifyStr);
            if (wxpayApp.isPayResultNotifySignatureValid(resultMap)) {
                String returnCode = resultMap.get("return_code");  //??????
                String outTradeNo = resultMap.get("out_trade_no");//???????????????
                String transactionId = resultMap.get("transaction_id");//?????????????????????
                QueryWrapper<OperationOrder> orderQueryWrapper = new QueryWrapper<>();
                orderQueryWrapper.lambda().eq(OperationOrder::getPayNumber,outTradeNo);
                OperationOrder order = operationOrderService.getOne(orderQueryWrapper);
                if (returnCode.equals("SUCCESS")) {
                    if (StringUtils.isNotBlank(outTradeNo)) {

                        /* ????????????   */

                        order.setPayState(MusicCode.PAY_STATUS_SUCCESS);
                        order.setTradeNo(transactionId);
                        operationOrderService.updateOperationOrder(order);

                        xmlBack = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                        return xmlBack;
                    }
                }else {
                    order.setPayState(MusicCode.PAY_STATUS_LOSE);
                    operationOrderService.updateOperationOrder(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlBack;
    }


    /**
     * ??????????????????
     *
     * @param order
     * @return
     */
    @Override
    public AjaxResult wxPay(OperationOrder order) {
        Map<String, String> returnMap = new HashMap<>();
        Map<String, String> responseMap = new HashMap<>();
        Map<String, String> requestMap = new HashMap<>();

        WXPay wxpay = new WXPay(wxpayConfig);
        requestMap.put("body", order.getShopName());                                     // ????????????
        requestMap.put("out_trade_no", order.getPayNumber());                          // ???????????????
        requestMap.put("total_fee", String.valueOf((int) (order.getShopPrice() * 100)));   // ?????????
        requestMap.put("spbill_create_ip", "127.0.0.1"); // ??????IP
        requestMap.put("trade_type", "APP");                              // App????????????
        requestMap.put("notify_url", wxpayConfig.getPayNotifyUrl());   // ??????????????????????????????????????????
        try {
            Map<String, String> resultMap = wxpay.unifiedOrder(requestMap);
            String returnCode = resultMap.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = resultMap.get("result_code");
                if ("SUCCESS".equals(resultCode)) {
                    responseMap = resultMap;
                }
            }

            // 3?????????????????????
            Long time = System.currentTimeMillis() / 1000;
            String timestamp = time.toString();
            returnMap.put("appid", wxpayConfig.getAppID());
            returnMap.put("partnerid", wxpayConfig.getMchID());
            returnMap.put("prepayid", responseMap.get("prepay_id"));
            returnMap.put("noncestr", responseMap.get("nonce_str"));
            returnMap.put("timestamp", timestamp);
            returnMap.put("package", "Sign=WXPay");
            returnMap.put("sign", WXPayUtil.generateSignature(returnMap, wxpayConfig.getKey()));//??????????????????

            return AjaxResult.success(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.success();
    }
}
