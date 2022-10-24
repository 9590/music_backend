package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.response.OperationOrderResponse;

/**
 * 运营管理-订单管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
public interface IOperationOrderService extends IService<OperationOrder>
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
    public List<OperationOrderResponse> selectOperationOrderList(OperationOrder operationOrder);
    public PageInfo selectOperationOrderListByPageInfo(OperationOrder operationOrder);

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
     * 批量删除运营管理-订单管理
     * 
     * @param ids 需要删除的运营管理-订单管理主键集合
     * @return 结果
     */
    public int deleteOperationOrderByIds(Long[] ids);

    /**
     * 删除运营管理-订单管理信息
     * 
     * @param id 运营管理-订单管理主键
     * @return 结果
     */
    public int deleteOperationOrderById(Long id);
}
