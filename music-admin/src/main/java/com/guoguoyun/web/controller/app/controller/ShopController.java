package com.guoguoyun.web.controller.app.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.ShoppingCommodity;
import com.guoguoyun.web.controller.app.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/shop")
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;


    /**
     * 商城轮播图
     * @return
     */
    @GetMapping("/indexShop")
    public AjaxResult indexShop(){
        return AjaxResult.success(shopService.indexShop());
    }


    /**
     * 商品列表
     * @param classifyId
     * @return
     */
    @GetMapping("/shopList/{classifyId}")
    public TableDataInfo shopList(@PathVariable("classifyId") Long classifyId,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        Page<ShoppingCommodity> shoppingCommodities = shopService.shopList(classifyId,pageNum,pageSize);
        return getDataTable(shoppingCommodities.getRecords(),shoppingCommodities.getTotal());
    }


    /**
     * 商品分类
     * @return
     */
    @GetMapping("/shopTypeList")
    public AjaxResult shopTypeList(){
        return AjaxResult.success(shopService.shopTypeList());
    }


    /**
     * 商品详情
     * @param shopId
     * @return
     */
    @GetMapping("/getShopInfo/{shopId}")
    public AjaxResult getShopInfo(@PathVariable("shopId") Long shopId){
        return AjaxResult.success(shopService.getShopInfo(shopId));
    }
}
