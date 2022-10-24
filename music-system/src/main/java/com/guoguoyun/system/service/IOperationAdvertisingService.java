package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.response.OperationAdvertisingResponse;

/**
 * 运行管理-广告管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
public interface IOperationAdvertisingService extends IService<OperationAdvertising>
{
    /**
     * 查询运行管理-广告管理
     * 
     * @param id 运行管理-广告管理主键
     * @return 运行管理-广告管理
     */
    public OperationAdvertising selectOperationAdvertisingById(Long id);

    /**
     * 查询运行管理-广告管理列表
     * 
     * @param operationAdvertising 运行管理-广告管理
     * @return 运行管理-广告管理集合
     */
    public PageInfo selectOperationAdvertisingList(OperationAdvertising operationAdvertising);

    /**
     * 新增运行管理-广告管理
     * 
     * @param operationAdvertising 运行管理-广告管理
     * @return 结果
     */
    public int insertOperationAdvertising(OperationAdvertising operationAdvertising);

    /**
     * 修改运行管理-广告管理
     * 
     * @param operationAdvertising 运行管理-广告管理
     * @return 结果
     */
    public int updateOperationAdvertising(OperationAdvertising operationAdvertising);

    /**
     * 批量删除运行管理-广告管理
     * 
     * @param ids 需要删除的运行管理-广告管理主键集合
     * @return 结果
     */
    public int deleteOperationAdvertisingByIds(Long[] ids);

    /**
     * 删除运行管理-广告管理信息
     * 
     * @param id 运行管理-广告管理主键
     * @return 结果
     */
    public int deleteOperationAdvertisingById(Long id);
}
