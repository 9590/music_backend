package com.guoguoyun.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.OperationIndex;
import com.guoguoyun.system.params.OperationIndexParams;

/**
 * 运营管理-首页管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-23
 */
public interface IOperationIndexService extends IService<OperationIndex>
{
    /**
     * 查询运营管理-首页管理
     * 
     * @param id 运营管理-首页管理主键
     * @return 运营管理-首页管理
     */
    public OperationIndex selectOperationIndexById(Long id);

    /**
     * 查询运营管理-首页管理列表
     * 
     * @param operationIndex 运营管理-首页管理
     * @return 运营管理-首页管理集合
     */
    public List<OperationIndex> selectOperationIndexList(OperationIndex operationIndex);
    public Map<String,Object> selectOperationIndexList();

    /**
     * 新增运营管理-首页管理
     * 
     * @param operationIndex 运营管理-首页管理
     * @return 结果
     */
    public int insertOperationIndex(OperationIndex operationIndex);
    public void insertOperationIndex(OperationIndexParams operationIndexParams);

    /**
     * 修改运营管理-首页管理
     * 
     * @param operationIndex 运营管理-首页管理
     * @return 结果
     */
    public int updateOperationIndex(OperationIndex operationIndex);

    /**
     * 批量删除运营管理-首页管理
     * 
     * @param ids 需要删除的运营管理-首页管理主键集合
     * @return 结果
     */
    public int deleteOperationIndexByIds(Long[] ids);

    /**
     * 删除运营管理-首页管理信息
     * 
     * @param id 运营管理-首页管理主键
     * @return 结果
     */
    public int deleteOperationIndexById(Long id);
}
