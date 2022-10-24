package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.OperationAdvertising;
import org.springframework.stereotype.Repository;

/**
 * 运行管理-广告管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Repository
public interface OperationAdvertisingMapper extends BaseMapper<OperationAdvertising>
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
    public List<OperationAdvertising> selectOperationAdvertisingList(OperationAdvertising operationAdvertising);

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
     * 删除运行管理-广告管理
     * 
     * @param id 运行管理-广告管理主键
     * @return 结果
     */
    public int deleteOperationAdvertisingById(Long id);

    /**
     * 批量删除运行管理-广告管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationAdvertisingByIds(Long[] ids);
}
