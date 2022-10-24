package com.guoguoyun.web.controller.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.domain.ShoppingCommodity;

import java.util.List;

public interface ShopService {


    /**
     * 商品分类
     * @return
     */
    public List<Classify> shopTypeList();

    /**
     * 商品
     * @param classifyId 分类id
     * @return
     */
    public Page<ShoppingCommodity> shopList(Long classifyId, Integer pageNum, Integer pageSize);


    /**
     * 商品详情
     * @param shopId
     * @return
     */
    public ShoppingCommodity getShopInfo(Long shopId);

    public List<OperationAdvertising> indexShop();
}
