package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.OperationCoupon;
import com.guoguoyun.system.params.OperationUserCouponParams;
import com.guoguoyun.system.response.OperationCouponResponse;

/**
 * 运营管理-优惠卷管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
public interface IOperationCouponService extends IService<OperationCoupon>
{
    /**
     * 查询运营管理-优惠卷管理
     * 
     * @param id 运营管理-优惠卷管理主键
     * @return 运营管理-优惠卷管理
     */
    public OperationCoupon selectOperationCouponById(Long id);

    /**
     * 查询运营管理-优惠卷管理列表
     * 
     * @param operationCoupon 运营管理-优惠卷管理
     * @return 运营管理-优惠卷管理集合
     */
    public List<OperationCouponResponse> selectOperationCouponList(OperationCoupon operationCoupon);
    public PageInfo selectOperationCouponListByPageInfo(OperationCoupon operationCoupon);

    /**
     * 新增运营管理-优惠卷管理
     * 
     * @param operationCoupon 运营管理-优惠卷管理
     * @return 结果
     */
    public int insertOperationCoupon(OperationCoupon operationCoupon);

    /**
     * 修改运营管理-优惠卷管理
     * 
     * @param operationCoupon 运营管理-优惠卷管理
     * @return 结果
     */
    public int updateOperationCoupon(OperationCoupon operationCoupon);

    /**
     * 批量删除运营管理-优惠卷管理
     * 
     * @param ids 需要删除的运营管理-优惠卷管理主键集合
     * @return 结果
     */
    public int deleteOperationCouponByIds(Long[] ids);

    /**
     * 删除运营管理-优惠卷管理信息
     * 
     * @param id 运营管理-优惠卷管理主键
     * @return 结果
     */
    public int deleteOperationCouponById(Long id);

    void addUserCoupon(OperationUserCouponParams operationUserCouponParams);
}
