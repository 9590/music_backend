package com.guoguoyun.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.enums.UseState;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.OperationCoupon;
import com.guoguoyun.system.response.OperationUserCouponResponse;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.system.service.IOperationCouponService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.OperationUserCouponMapper;
import com.guoguoyun.system.domain.OperationUserCoupon;
import com.guoguoyun.system.service.IOperationUserCouponService;

/**
 * 运营管理-优惠卷发放管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-15
 */
@Service
public class OperationUserCouponServiceImpl extends ServiceImpl<OperationUserCouponMapper, OperationUserCoupon> implements IOperationUserCouponService
{
    @Autowired
    private OperationUserCouponMapper operationUserCouponMapper;

    @Autowired
    private IOperationCouponService operationCouponService;

    @Autowired
    private IAppUserService appUserService;

    /**
     * 查询运营管理-优惠卷发放管理
     *
     * @param id 运营管理-优惠卷发放管理主键
     * @return 运营管理-优惠卷发放管理
     */
    @Override
    public OperationUserCoupon selectOperationUserCouponById(Long id)
    {
        return operationUserCouponMapper.selectOperationUserCouponById(id);
    }

    /**
     * 查询运营管理-优惠卷发放管理列表
     *
     * @param operationUserCoupon 运营管理-优惠卷发放管理
     * @return 运营管理-优惠卷发放管理
     */
    @Override
    public List<OperationUserCouponResponse> selectOperationUserCouponList(OperationUserCoupon operationUserCoupon)
    {
        List<OperationUserCoupon> operationUserCoupons = operationUserCouponMapper.selectOperationUserCouponList(operationUserCoupon);
        List<OperationUserCouponResponse> operationUserCouponResponses = operationUserCoupons.stream().map(t -> {
            OperationUserCouponResponse operationUserCouponResponse = BeanUtil.toBean(t, OperationUserCouponResponse.class);
            OperationCoupon operationCoupon = operationCouponService.getById(t.getCouponId());
            AppUser appUser = appUserService.selectAppUserById(t.getUserId());
            if (ObjectUtil.isNotEmpty(appUser)){
                operationUserCouponResponse.setUserName(appUser.getUserName());
            }
            operationUserCouponResponse.setCouponName(operationCoupon.getCouponName());
            operationUserCouponResponse.setCouponDeduction(operationCoupon.getCouponDeduction());
            operationUserCouponResponse.setUseState(UseState.getEnumType(t.getUseState()).getInfo());
            if (t.getIsLongTime() == MusicCode.NO_LONG_TIME) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                operationUserCouponResponse.setEndTime(sdf.format(t.getEndTime()));
            } else {
                operationUserCouponResponse.setEndTime("长期有效");
            }


            return operationUserCouponResponse;
        }).collect(Collectors.toList());
        return operationUserCouponResponses;
    }

    @Override
    public PageInfo selectOperationUserCouponListByPageInfo(OperationUserCoupon operationUserCoupon) {
        List<OperationUserCoupon> operationUserCoupons = operationUserCouponMapper.selectOperationUserCouponList(operationUserCoupon);
        List<OperationUserCouponResponse> operationUserCouponResponses = operationUserCoupons.stream().map(t -> {
            OperationUserCouponResponse operationUserCouponResponse = BeanUtil.toBean(t, OperationUserCouponResponse.class);
            OperationCoupon operationCoupon = operationCouponService.getById(t.getCouponId());
            AppUser appUser = appUserService.selectAppUserById(t.getUserId());
            if (ObjectUtil.isNotEmpty(appUser)){
                operationUserCouponResponse.setUserName(appUser.getUserName());
            }
            operationUserCouponResponse.setCouponName(operationCoupon.getCouponName());
            operationUserCouponResponse.setCouponDeduction(operationCoupon.getCouponDeduction());
            operationUserCouponResponse.setUseState(UseState.getEnumType(t.getUseState()).getInfo());
            if (t.getIsLongTime() == MusicCode.NO_LONG_TIME) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                operationUserCouponResponse.setEndTime(sdf.format(t.getEndTime()));
            } else {
                operationUserCouponResponse.setEndTime("长期有效");
            }


            return operationUserCouponResponse;
        }).collect(Collectors.toList());
        PageInfo<OperationUserCoupon> page = new PageInfo<>(operationUserCoupons);
        PageInfo<OperationUserCouponResponse> pageInfo = new PageInfo<>(operationUserCouponResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(operationUserCouponResponses);
        return pageInfo;
    }

    /**
     * 新增运营管理-优惠卷发放管理
     *
     * @param operationUserCoupon 运营管理-优惠卷发放管理
     * @return 结果
     */
    @Override
    public int insertOperationUserCoupon(OperationUserCoupon operationUserCoupon)
    {
        operationUserCoupon.setCreateTime(DateUtils.getNowDate());
        operationUserCoupon.setId(IdUtil.getSnowflakeNextId());
        return operationUserCouponMapper.insertOperationUserCoupon(operationUserCoupon);
    }

    /**
     * 修改运营管理-优惠卷发放管理
     *
     * @param operationUserCoupon 运营管理-优惠卷发放管理
     * @return 结果
     */
    @Override
    public int updateOperationUserCoupon(OperationUserCoupon operationUserCoupon)
    {
        operationUserCoupon.setUpdateTime(DateUtils.getNowDate());
        return operationUserCouponMapper.updateOperationUserCoupon(operationUserCoupon);
    }

    /**
     * 批量删除运营管理-优惠卷发放管理
     *
     * @param ids 需要删除的运营管理-优惠卷发放管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationUserCouponByIds(Long[] ids)
    {
        return operationUserCouponMapper.deleteOperationUserCouponByIds(ids);
    }

    /**
     * 删除运营管理-优惠卷发放管理信息
     *
     * @param id 运营管理-优惠卷发放管理主键
     * @return 结果
     */
    @Override
    public int deleteOperationUserCouponById(Long id)
    {
        return operationUserCouponMapper.deleteOperationUserCouponById(id);
    }
}
