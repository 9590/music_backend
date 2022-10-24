package com.guoguoyun.web.controller.app.service;

import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.params.OperationOrderParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AppPayService {

    /**
     * 保存订单
     * @param order
     */
    public AjaxResult addOrder(OperationOrderParams order);

    /**
     * 支付宝统一下单
     * @return
     */
    public AjaxResult alyPay(OperationOrder order);

    /**
     * 微信统一下单
     * @param order
     * @return
     */
    public AjaxResult wxPay(OperationOrder order);

    /**
     * 支付宝支付回调
     * @param request
     * @param response
     * @return
     */
    public String alyNotify(HttpServletRequest request, HttpServletResponse response);

    /**
     * 微信支付回调
     * @param notifyStr
     * @return
     */
    public String wxNotify(String notifyStr);
}
