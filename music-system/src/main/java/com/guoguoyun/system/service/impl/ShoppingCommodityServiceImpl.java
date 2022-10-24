package com.guoguoyun.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.enums.ShopIsTopType;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import com.guoguoyun.system.params.ShoppingCommodityParams;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
import com.guoguoyun.system.response.ShoppingCommodityResponse;
import com.guoguoyun.system.service.IClassifyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.ShoppingCommodityMapper;
import com.guoguoyun.system.domain.ShoppingCommodity;
import com.guoguoyun.system.service.IShoppingCommodityService;

/**
 * 商城管理-商品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Service
public class ShoppingCommodityServiceImpl extends ServiceImpl<ShoppingCommodityMapper, ShoppingCommodity> implements IShoppingCommodityService
{
    @Autowired
    private ShoppingCommodityMapper shoppingCommodityMapper;

    @Autowired
    private IClassifyService classifyService;

    /**
     * 查询商城管理-商品管理
     * 
     * @param id 商城管理-商品管理主键
     * @return 商城管理-商品管理
     */
    @Override
    public ShoppingCommodityResponse selectShoppingCommodityById(Long id)
    {
        ShoppingCommodity shoppingCommodity = shoppingCommodityMapper.selectShoppingCommodityById(id);
        ShoppingCommodityResponse shoppingCommodityResponse = BeanUtil.toBean(shoppingCommodity, ShoppingCommodityResponse.class);
        shoppingCommodityResponse.setShopIsTopName(ShopIsTopType.getEnumType(Integer.valueOf(shoppingCommodity.getShopIsTop().toString())).getInfo());
        shoppingCommodityResponse.setClassifyName(classifyService.selectClassifyById(shoppingCommodity.getClassifyId()).getClassifyName());
        List<ImgUrlVo> strings = JSONArray.parseArray(shoppingCommodity.getShopImg(), ImgUrlVo.class);
        shoppingCommodityResponse.setShopImgs(strings);
        return shoppingCommodityResponse;
    }

    /**
     * 查询商城管理-商品管理列表
     * 
     * @param shoppingCommodity 商城管理-商品管理
     * @return 商城管理-商品管理
     */
    @Override
    public PageInfo selectShoppingCommodityList(ShoppingCommodity shoppingCommodity)
    {
        List<ShoppingCommodity> shoppingCommodities = shoppingCommodityMapper.selectShoppingCommodityList(shoppingCommodity);
        List<ShoppingCommodityResponse> shoppingCommodityResponses = shoppingCommodities.stream().map(t -> {
            ShoppingCommodityResponse shoppingCommodityResponse = BeanUtil.toBean(t, ShoppingCommodityResponse.class);
            shoppingCommodityResponse.setShopIsTopName(ShopIsTopType.getEnumType(Integer.valueOf(t.getShopIsTop().toString())).getInfo());
            Classify classify = classifyService.selectClassifyById(t.getClassifyId());
            if (ObjectUtil.isNotEmpty(classify)){
                shoppingCommodityResponse.setClassifyName(classify.getClassifyName());
            }else {
                shoppingCommodityResponse.setClassifyName("");
            }

            List<ImgUrlVo> strings = JSONArray.parseArray(t.getShopImg(), ImgUrlVo.class);
            shoppingCommodityResponse.setShopImgs(strings);
            return shoppingCommodityResponse;
        }).collect(Collectors.toList());
        PageInfo<ShoppingCommodity> page = new PageInfo<>(shoppingCommodities);
        PageInfo<ShoppingCommodityResponse> pageInfo = new PageInfo<>(shoppingCommodityResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(shoppingCommodityResponses);
        return pageInfo;
    }

    /**
     * 新增商城管理-商品管理
     * 
     * @param shoppingCommodity 商城管理-商品管理
     * @return 结果
     */
    @Override
    public int insertShoppingCommodity(ShoppingCommodity shoppingCommodity)
    {

        QueryWrapper<ShoppingCommodity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ShoppingCommodity::getShopNumber,shoppingCommodity.getShopNumber());
        ShoppingCommodity commodity = this.getOne(wrapper);
        if (ObjectUtil.isNotEmpty(commodity)){
            throw new SecurityException(MusicCode.ERROR_NUMBER_REOETITION);
        }

        shoppingCommodity.setCreateTime(DateUtils.getNowDate());
        shoppingCommodity.setId(IdUtil.getSnowflakeNextId());
        return shoppingCommodityMapper.insertShoppingCommodity(shoppingCommodity);
    }

    /**
     * 修改商城管理-商品管理
     * 
     * @param shoppingCommodity 商城管理-商品管理
     * @return 结果
     */
    @Override
    public int updateShoppingCommodity(ShoppingCommodity shoppingCommodity)
    {
        shoppingCommodity.setUpdateTime(DateUtils.getNowDate());
        return shoppingCommodityMapper.updateShoppingCommodity(shoppingCommodity);
    }

    /**
     * 批量删除商城管理-商品管理
     * 
     * @param ids 需要删除的商城管理-商品管理主键
     * @return 结果
     */
    @Override
    public int deleteShoppingCommodityByIds(Long[] ids)
    {
        return shoppingCommodityMapper.deleteShoppingCommodityByIds(ids);
    }

    /**
     * 删除商城管理-商品管理信息
     * 
     * @param id 商城管理-商品管理主键
     * @return 结果
     */
    @Override
    public int deleteShoppingCommodityById(Long id)
    {
        return shoppingCommodityMapper.deleteShoppingCommodityById(id);
    }

    @Override
    public void updateTop(ShoppingCommodityParams shoppingCommodityParams) {
        shoppingCommodityMapper.updateTop(shoppingCommodityParams.getId());
    }
}
