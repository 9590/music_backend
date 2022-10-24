package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.ShoppingCommodity;
import com.guoguoyun.system.params.ShoppingCommodityParams;
import com.guoguoyun.system.response.ShoppingCommodityResponse;

/**
 * 商城管理-商品管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
public interface IShoppingCommodityService extends IService<ShoppingCommodity>
{
    /**
     * 查询商城管理-商品管理
     * 
     * @param id 商城管理-商品管理主键
     * @return 商城管理-商品管理
     */
    public ShoppingCommodityResponse selectShoppingCommodityById(Long id);

    /**
     * 查询商城管理-商品管理列表
     * 
     * @param shoppingCommodity 商城管理-商品管理
     * @return 商城管理-商品管理集合
     */
    public PageInfo selectShoppingCommodityList(ShoppingCommodity shoppingCommodity);

    /**
     * 新增商城管理-商品管理
     * 
     * @param shoppingCommodity 商城管理-商品管理
     * @return 结果
     */
    public int insertShoppingCommodity(ShoppingCommodity shoppingCommodity);

    /**
     * 修改商城管理-商品管理
     * 
     * @param shoppingCommodity 商城管理-商品管理
     * @return 结果
     */
    public int updateShoppingCommodity(ShoppingCommodity shoppingCommodity);

    /**
     * 批量删除商城管理-商品管理
     * 
     * @param ids 需要删除的商城管理-商品管理主键集合
     * @return 结果
     */
    public int deleteShoppingCommodityByIds(Long[] ids);

    /**
     * 删除商城管理-商品管理信息
     * 
     * @param id 商城管理-商品管理主键
     * @return 结果
     */
    public int deleteShoppingCommodityById(Long id);

    /**
     * 修改置顶状态
     * @param shoppingCommodityParams
     */
    public void updateTop(ShoppingCommodityParams shoppingCommodityParams);
}
