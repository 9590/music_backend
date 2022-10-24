package com.guoguoyun.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.enums.PayType;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
import com.guoguoyun.system.response.OperationOrderResponse;
import com.guoguoyun.system.service.IClassifyService;
import com.guoguoyun.system.service.IShoppingCommodityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.OperationOrderMapper;
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.service.IOperationOrderService;

/**
 * 运营管理-订单管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
@Service
public class OperationOrderServiceImpl extends ServiceImpl<OperationOrderMapper, OperationOrder> implements IOperationOrderService
{
    @Autowired
    private OperationOrderMapper operationOrderMapper;

    @Autowired
    private IClassifyService classifyService;


    /**
     * 查询运营管理-订单管理
     * 
     * @param id 运营管理-订单管理主键
     * @return 运营管理-订单管理
     */
    @Override
    public OperationOrder selectOperationOrderById(Long id)
    {
        return operationOrderMapper.selectOperationOrderById(id);
    }

    /**
     * 查询运营管理-订单管理列表
     * 
     * @param operationOrder 运营管理-订单管理
     * @return 运营管理-订单管理
     */
    @Override
    public List<OperationOrderResponse> selectOperationOrderList(OperationOrder operationOrder)
    {
        List<OperationOrder> operationOrders = operationOrderMapper.selectOperationOrderList(operationOrder);
        List<OperationOrderResponse> operationOrderResponses = operationOrders.stream().map(t -> {
            OperationOrderResponse operationOrderResponse = BeanUtil.toBean(t, OperationOrderResponse.class);
            operationOrderResponse.setPayType(PayType.getEnumType(t.getPayType()).getInfo());
            Classify classify = classifyService.getById(t.getShopType());
            if (ObjectUtil.isNotEmpty(classify)){
                operationOrderResponse.setShopType(classify.getClassifyName());
            }else {
                operationOrderResponse.setShopType("");
            }

            return operationOrderResponse;
        }).collect(Collectors.toList());
        return operationOrderResponses;
    }

    @Override
    public PageInfo selectOperationOrderListByPageInfo(OperationOrder operationOrder) {
        List<OperationOrder> operationOrders = operationOrderMapper.selectOperationOrderList(operationOrder);
        List<OperationOrderResponse> operationOrderResponses = operationOrders.stream().map(t -> {
            OperationOrderResponse operationOrderResponse = BeanUtil.toBean(t, OperationOrderResponse.class);
            operationOrderResponse.setPayType(PayType.getEnumType(t.getPayType()).getInfo());
            Classify classify = classifyService.getById(t.getShopType());
            if (ObjectUtil.isNotEmpty(classify)){
                operationOrderResponse.setShopType(classify.getClassifyName());
            }else {
                operationOrderResponse.setShopType("");
            }

            

            return operationOrderResponse;
        }).collect(Collectors.toList());
        PageInfo<OperationOrder> page = new PageInfo<>(operationOrders);
        PageInfo<OperationOrderResponse> pageInfo = new PageInfo<>(operationOrderResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(operationOrderResponses);
        return pageInfo;
    }

    /**
     * 新增运营管理-订单管理
     * 
     * @param operationOrder 运营管理-订单管理
     * @return 结果
     */
    @Override
    public int insertOperationOrder(OperationOrder operationOrder)
    {
        operationOrder.setCreateTime(DateUtils.getNowDate());
        operationOrder.setId(IdUtil.getSnowflakeNextId());
        return operationOrderMapper.insertOperationOrder(operationOrder);
    }

    /**
     * 修改运营管理-订单管理
     * 
     * @param operationOrder 运营管理-订单管理
     * @return 结果
     */
    @Override
    public int updateOperationOrder(OperationOrder operationOrder)
    {
        operationOrder.setUpdateTime(DateUtils.getNowDate());
        return operationOrderMapper.updateOperationOrder(operationOrder);
    }

    /**
     * 批量删除运营管理-订单管理
     * 
     * @param ids 需要删除的运营管理-订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationOrderByIds(Long[] ids)
    {
        return operationOrderMapper.deleteOperationOrderByIds(ids);
    }

    /**
     * 删除运营管理-订单管理信息
     * 
     * @param id 运营管理-订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationOrderById(Long id)
    {
        return operationOrderMapper.deleteOperationOrderById(id);
    }
}
