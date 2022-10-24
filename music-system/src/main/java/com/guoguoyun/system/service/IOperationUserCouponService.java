package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.OperationUserCoupon;
import com.guoguoyun.system.response.OperationUserCouponResponse;

/**
 * 运营管理-优惠卷发放管理Service接口
 *
 * @author ruoyi
 * @date 2022-02-15
 */
public interface IOperationUserCouponService extends IService<OperationUserCoupon>
{
    /**
     * 查询运营管理-优惠卷发放管理
     *
     * @param id 运营管理-优惠卷发放管理主键
     * @return 运营管理-优惠卷发放管理
     */
    public OperationUserCoupon selectOperationUserCouponById(Long id);

    /**
     * 查询运营管理-优惠卷发放管理列表
     *
     * @param operationUserCoupon 运营管理-优惠卷发放管理
     * @return 运营管理-优惠卷发放管理集合
     */
    public List<OperationUserCouponResponse> selectOperationUserCouponList(OperationUserCoupon operationUserCoupon);
    public PageInfo selectOperationUserCouponListByPageInfo(OperationUserCoupon operationUserCoupon);

    /**
     * 新增运营管理-优惠卷发放管理
     *
     * @param operationUserCoupon 运营管理-优惠卷发放管理
     * @return 结果
     */
    public int insertOperationUserCoupon(OperationUserCoupon operationUserCoupon);

    /**
     * 修改运营管理-优惠卷发放管理
     *
     * @param operationUserCoupon 运营管理-优惠卷发放管理
     * @return 结果
     */
    public int updateOperationUserCoupon(OperationUserCoupon operationUserCoupon);

    /**
     * 批量删除运营管理-优惠卷发放管理
     *
     * @param ids 需要删除的运营管理-优惠卷发放管理主键集合
     * @return 结果
     */
    public int deleteOperationUserCouponByIds(Long[] ids);

    /**
     * 删除运营管理-优惠卷发放管理信息
     *
     * @param id 运营管理-优惠卷发放管理主键
     * @return 结果
     */
    public int deleteOperationUserCouponById(Long id);
}
