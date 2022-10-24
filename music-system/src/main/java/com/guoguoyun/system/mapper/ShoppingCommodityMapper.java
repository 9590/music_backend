package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.ShoppingCommodity;
import org.springframework.stereotype.Repository;

/**
 * 商城管理-商品管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Repository
public interface ShoppingCommodityMapper extends BaseMapper<ShoppingCommodity>
{
    /**
     * 查询商城管理-商品管理
     * 
     * @param id 商城管理-商品管理主键
     * @return 商城管理-商品管理
     */
    public ShoppingCommodity selectShoppingCommodityById(Long id);

    /**
     * 查询商城管理-商品管理列表
     * 
     * @param shoppingCommodity 商城管理-商品管理
     * @return 商城管理-商品管理集合
     */
    public List<ShoppingCommodity> selectShoppingCommodityList(ShoppingCommodity shoppingCommodity);

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
     * 删除商城管理-商品管理
     * 
     * @param id 商城管理-商品管理主键
     * @return 结果
     */
    public int deleteShoppingCommodityById(Long id);

    /**
     * 批量删除商城管理-商品管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShoppingCommodityByIds(Long[] ids);

    /**
     * 修改置顶状态
     * @param id
     */
    public void updateTop(Long id);
}
