package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.OperationOrder;
import org.springframework.stereotype.Repository;

/**
 * 运营管理-订单管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
@Repository
public interface OperationOrderMapper extends BaseMapper<OperationOrder>
{
    /**
     * 查询运营管理-订单管理
     * 
     * @param id 运营管理-订单管理主键
     * @return 运营管理-订单管理
     */
    public OperationOrder selectOperationOrderById(Long id);

    /**
     * 查询运营管理-订单管理列表
     * 
     * @param operationOrder 运营管理-订单管理
     * @return 运营管理-订单管理集合
     */
    public List<OperationOrder> selectOperationOrderList(OperationOrder operationOrder);

    /**
     * 新增运营管理-订单管理
     * 
     * @param operationOrder 运营管理-订单管理
     * @return 结果
     */
    public int insertOperationOrder(OperationOrder operationOrder);

    /**
     * 修改运营管理-订单管理
     * 
     * @param operationOrder 运营管理-订单管理
     * @return 结果
     */
    public int updateOperationOrder(OperationOrder operationOrder);

    /**
     * 删除运营管理-订单管理
     * 
     * @param id 运营管理-订单管理主键
     * @return 结果
     */
    public int deleteOperationOrderById(Long id);

    /**
     * 批量删除运营管理-订单管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationOrderByIds(Long[] ids);
}
