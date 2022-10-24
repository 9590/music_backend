package com.guoguoyun.web.controller.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.enums.AdvertisingLocation;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.domain.ShoppingCommodity;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import com.guoguoyun.system.service.IClassifyService;
import com.guoguoyun.system.service.IOperationAdvertisingService;
import com.guoguoyun.system.service.IShoppingCommodityService;
import com.guoguoyun.web.controller.app.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private IShoppingCommodityService shoppingCommodityService;

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private IOperationAdvertisingService advertisingService;


    /**
     * 商品分类
     * @return
     */
    @Override
    public List<Classify> shopTypeList() {
        QueryWrapper<Classify> classifyQueryWrapper = new QueryWrapper<>();
        classifyQueryWrapper.lambda().eq(Classify::getClassifyType, MusicCode.CLASSIFY_TYPE_SHOP);
        List<Classify> classifies = classifyService.list(classifyQueryWrapper);
        return classifies;
    }

    /**
     * 商品
     * @param classifyId 分类id
     * @return
     */
    @Override
    public Page<ShoppingCommodity> shopList(Long classifyId,Integer pageNum,Integer pageSize) {
        if (ObjectUtil.isEmpty(classifyId)){
            throw new SecurityException("请传商品分类id");
        }
        Page<ShoppingCommodity> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ShoppingCommodity> shoppingCommodityQueryWrapper = new QueryWrapper<>();
        shoppingCommodityQueryWrapper.lambda().eq(ShoppingCommodity::getClassifyId,classifyId);
        shoppingCommodityQueryWrapper.lambda().orderByDesc(ShoppingCommodity::getCreateTime);
        Page<ShoppingCommodity> commodityPage = shoppingCommodityService.page(page, shoppingCommodityQueryWrapper);
        commodityPage.getRecords().forEach(t ->{
            if (ObjectUtil.isNotEmpty(t.getShopImg())){
                List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(t.getShopImg(), ImgUrlVo.class);
                t.setImgUrls(imgUrlVos);
                if (imgUrlVos.size() != 0){
                    t.setShopImg(imgUrlVos.get(0).getUrl());
                }
            }
        });
        return commodityPage;
    }

    /**
     * 商品详情
     * @param shopId
     * @return
     */
    @Override
    public ShoppingCommodity getShopInfo(Long shopId) {
        ShoppingCommodity byId = shoppingCommodityService.getById(shopId);
        if (ObjectUtil.isNotEmpty(byId.getShopImg())){
            List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(byId.getShopImg(), ImgUrlVo.class);
            byId.setImgUrls(imgUrlVos);
        }
        return byId;
    }

    @Override
    public List<OperationAdvertising> indexShop() {
        QueryWrapper<OperationAdvertising> advertisingQueryWrapper = new QueryWrapper<>();
        advertisingQueryWrapper.lambda().eq(OperationAdvertising::getAdvShow,1);
        advertisingQueryWrapper.lambda().orderByAsc(OperationAdvertising::getAdvSort);
        advertisingQueryWrapper.lambda().eq(OperationAdvertising::getAdvLocation, AdvertisingLocation.SHOPFLASHVIEW.getCode());
        return advertisingService.list(advertisingQueryWrapper);
    }
}
