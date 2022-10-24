package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.OperationUserCoupon;
import org.springframework.stereotype.Repository;

/**
 * 运营管理-优惠卷发放管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Repository
public interface OperationUserCouponMapper extends BaseMapper<OperationUserCoupon>
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
    public List<OperationUserCoupon> selectOperationUserCouponList(OperationUserCoupon operationUserCoupon);
    public List<OperationUserCoupon> selectOperationUserCouponListLike(OperationUserCoupon operationUserCoupon);

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
     * 删除运营管理-优惠卷发放管理
     * 
     * @param id 运营管理-优惠卷发放管理主键
     * @return 结果
     */
    public int deleteOperationUserCouponById(Long id);

    /**
     * 批量删除运营管理-优惠卷发放管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationUserCouponByIds(Long[] ids);
}
