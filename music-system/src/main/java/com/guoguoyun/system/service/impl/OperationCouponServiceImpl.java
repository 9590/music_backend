package com.guoguoyun.system.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.enums.CouponState;

import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.domain.OperationUserCoupon;
import com.guoguoyun.system.params.OperationUserCouponParams;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
import com.guoguoyun.system.response.OperationCouponResponse;
import com.guoguoyun.system.service.IOperationUserCouponService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.OperationCouponMapper;
import com.guoguoyun.system.domain.OperationCoupon;
import com.guoguoyun.system.service.IOperationCouponService;

/**
 * 运营管理-优惠卷管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Service
public class OperationCouponServiceImpl extends ServiceImpl<OperationCouponMapper, OperationCoupon> implements IOperationCouponService
{
    @Autowired
    private OperationCouponMapper operationCouponMapper;

    @Autowired
    private IOperationUserCouponService operationUserCouponService;

    /**
     * 查询运营管理-优惠卷管理
     * 
     * @param id 运营管理-优惠卷管理主键
     * @return 运营管理-优惠卷管理
     */
    @Override
    public OperationCoupon selectOperationCouponById(Long id)
    {
        return operationCouponMapper.selectOperationCouponById(id);
    }

    /**
     * 查询运营管理-优惠卷管理列表
     * 
     * @param operationCoupon 运营管理-优惠卷管理
     * @return 运营管理-优惠卷管理
     */
    @Override
    public List<OperationCouponResponse> selectOperationCouponList(OperationCoupon operationCoupon)
    {
        operationCoupon.setCouponState(1);
        List<OperationCoupon> operationCoupons = operationCouponMapper.selectOperationCouponList(operationCoupon);
        List<OperationCouponResponse> operationCouponResponses = operationCoupons.stream().map(t -> {
            OperationCouponResponse operationCouponResponse = BeanUtil.toBean(t, OperationCouponResponse.class);
            operationCouponResponse.setCouponStateName(CouponState.getEnumType(t.getCouponState()).getInfo());
            return operationCouponResponse;
        }).collect(Collectors.toList());
        return operationCouponResponses;
    }

    @Override
    public PageInfo selectOperationCouponListByPageInfo(OperationCoupon operationCoupon) {
        List<OperationCoupon> operationCoupons = operationCouponMapper.selectOperationCouponList(operationCoupon);
        List<OperationCouponResponse> operationCouponResponses = operationCoupons.stream().map(t -> {
            OperationCouponResponse operationCouponResponse = BeanUtil.toBean(t, OperationCouponResponse.class);
            operationCouponResponse.setCouponStateName(CouponState.getEnumType(t.getCouponState()).getInfo());
            return operationCouponResponse;
        }).collect(Collectors.toList());
        PageInfo<OperationCoupon> page = new PageInfo<>(operationCoupons);
        PageInfo<OperationCouponResponse> pageInfo = new PageInfo<>(operationCouponResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(operationCouponResponses);
        return pageInfo;
    }

    /**
     * 新增运营管理-优惠卷管理
     * 
     * @param operationCoupon 运营管理-优惠卷管理
     * @return 结果
     */
    @Override
    public int insertOperationCoupon(OperationCoupon operationCoupon)
    {
        operationCoupon.setCreateTime(DateUtils.getNowDate());
        operationCoupon.setId(IdUtil.getSnowflakeNextId());
        return operationCouponMapper.insertOperationCoupon(operationCoupon);
    }

    /**
     * 修改运营管理-优惠卷管理
     * 
     * @param operationCoupon 运营管理-优惠卷管理
     * @return 结果
     */
    @Override
    public int updateOperationCoupon(OperationCoupon operationCoupon)
    {
        operationCoupon.setUpdateTime(DateUtils.getNowDate());
        return operationCouponMapper.updateOperationCoupon(operationCoupon);
    }

    /**
     * 批量删除运营管理-优惠卷管理
     * 
     * @param ids 需要删除的运营管理-优惠卷管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationCouponByIds(Long[] ids)
    {
        return operationCouponMapper.deleteOperationCouponByIds(ids);
    }

    /**
     * 删除运营管理-优惠卷管理信息
     * 
     * @param id 运营管理-优惠卷管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationCouponById(Long id)
    {
        return operationCouponMapper.deleteOperationCouponById(id);
    }

    @Override
    public void addUserCoupon(OperationUserCouponParams operationUserCouponParams) {
        OperationUserCoupon operationUserCoupon = new OperationUserCoupon();
        operationUserCoupon.setCouponId(operationUserCouponParams.getCouponId());
        operationUserCoupon.setCreateTime(DateUtils.getNowDate());
        operationUserCoupon.setUpdateTime(DateUtils.getNowDate());
        operationUserCoupon.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        operationUserCoupon.setUpdateBy(SecurityUtils.getLoginUser().getUsername());

        if (operationUserCouponParams.getIsLongTime() == MusicCode.NO_LONG_TIME){
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE,operationUserCouponParams.getPeriodOfValidity());
            operationUserCoupon.setEndTime(calendar.getTime());
            operationUserCoupon.setPeriodOfValidity(operationUserCouponParams.getPeriodOfValidity());
        }else {
            operationUserCoupon.setIsLongTime(MusicCode.YES_LONG_TIME);
        }
        operationUserCouponParams.getUserIds().forEach(t ->{
            operationUserCoupon.setId(IdUtil.getSnowflakeNextId());
            operationUserCoupon.setUserId(t);
            operationUserCouponService.save(operationUserCoupon);
        });
    }
}
