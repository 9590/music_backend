package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.OperationCoupon;
import org.springframework.stereotype.Repository;

/**
 * 运营管理-优惠卷管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Repository
public interface OperationCouponMapper extends BaseMapper<OperationCoupon>
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
    public List<OperationCoupon> selectOperationCouponList(OperationCoupon operationCoupon);

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
     * 删除运营管理-优惠卷管理
     * 
     * @param id 运营管理-优惠卷管理主键
     * @return 结果
     */
    public int deleteOperationCouponById(Long id);

    /**
     * 批量删除运营管理-优惠卷管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationCouponByIds(Long[] ids);
}
