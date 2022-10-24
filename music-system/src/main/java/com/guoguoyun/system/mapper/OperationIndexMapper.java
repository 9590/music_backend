package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.OperationIndex;
import org.springframework.stereotype.Repository;

/**
 * 运营管理-首页管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-23
 */
@Repository
public interface OperationIndexMapper extends BaseMapper<OperationIndex>
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

    /**
     * 新增运营管理-首页管理
     * 
     * @param operationIndex 运营管理-首页管理
     * @return 结果
     */
    public int insertOperationIndex(OperationIndex operationIndex);

    /**
     * 修改运营管理-首页管理
     * 
     * @param operationIndex 运营管理-首页管理
     * @return 结果
     */
    public int updateOperationIndex(OperationIndex operationIndex);

    /**
     * 删除运营管理-首页管理
     * 
     * @param id 运营管理-首页管理主键
     * @return 结果
     */
    public int deleteOperationIndexById(Long id);

    /**
     * 批量删除运营管理-首页管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperationIndexByIds(Long[] ids);
}
