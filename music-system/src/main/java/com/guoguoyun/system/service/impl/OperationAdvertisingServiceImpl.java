package com.guoguoyun.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.HttpStatus;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.common.enums.AdvShowStatus;
import com.guoguoyun.common.enums.AdvertisingLocation;
import com.guoguoyun.common.utils.DateUtils;

import com.guoguoyun.system.response.OperationAdvertisingResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.OperationAdvertisingMapper;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.service.IOperationAdvertisingService;

/**
 * 运行管理-广告管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Service
public class OperationAdvertisingServiceImpl extends ServiceImpl<OperationAdvertisingMapper, OperationAdvertising> implements IOperationAdvertisingService
{
    @Autowired
    private OperationAdvertisingMapper operationAdvertisingMapper;

    /**
     * 查询运行管理-广告管理
     * 
     * @param id 运行管理-广告管理主键
     * @return 运行管理-广告管理
     */
    @Override
    public OperationAdvertising selectOperationAdvertisingById(Long id)
    {
        return operationAdvertisingMapper.selectOperationAdvertisingById(id);
    }

    /**
     * 查询运行管理-广告管理列表
     * 
     * @param operationAdvertising 运行管理-广告管理
     * @return 运行管理-广告管理
     */
    @Override
    public PageInfo selectOperationAdvertisingList(OperationAdvertising operationAdvertising)
    {
        System.out.println(operationAdvertising.getParams());
        List<OperationAdvertising> operationAdvertisings = operationAdvertisingMapper.selectOperationAdvertisingList(operationAdvertising);
        List<OperationAdvertisingResponse> operationAdvertisingResponseList = operationAdvertisings.stream().map(t -> {
            OperationAdvertisingResponse operationAdvertisingResponse = BeanUtil.toBean(t, OperationAdvertisingResponse.class);
            operationAdvertisingResponse.setAdvShow(AdvShowStatus.getEnumType(t.getAdvShow()).getInfo());
            operationAdvertisingResponse.setAdvLocation(AdvertisingLocation.getEnumType(t.getAdvLocation()).getInfo());
            return operationAdvertisingResponse;
        }).collect(Collectors.toList());

        PageInfo<OperationAdvertising> page = new PageInfo<>(operationAdvertisings);
        PageInfo<OperationAdvertisingResponse > pageInfo = new PageInfo<>(operationAdvertisingResponseList);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(operationAdvertisingResponseList);
        return pageInfo;
    }

    /**
     * 新增运行管理-广告管理
     * 
     * @param operationAdvertising 运行管理-广告管理
     * @return 结果
     */
    @Override
    public int insertOperationAdvertising(OperationAdvertising operationAdvertising)
    {
        operationAdvertising.setCreateTime(DateUtils.getNowDate());
        operationAdvertising.setId(IdUtil.getSnowflakeNextId());
        return operationAdvertisingMapper.insertOperationAdvertising(operationAdvertising);
    }

    /**
     * 修改运行管理-广告管理
     * 
     * @param operationAdvertising 运行管理-广告管理
     * @return 结果
     */
    @Override
    public int updateOperationAdvertising(OperationAdvertising operationAdvertising)
    {
        operationAdvertising.setUpdateTime(DateUtils.getNowDate());
        return operationAdvertisingMapper.updateOperationAdvertising(operationAdvertising);
    }

    /**
     * 批量删除运行管理-广告管理
     * 
     * @param ids 需要删除的运行管理-广告管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationAdvertisingByIds(Long[] ids)
    {
        return operationAdvertisingMapper.deleteOperationAdvertisingByIds(ids);
    }

    /**
     * 删除运行管理-广告管理信息
     * 
     * @param id 运行管理-广告管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationAdvertisingById(Long id)
    {
        return operationAdvertisingMapper.deleteOperationAdvertisingById(id);
    }
}
