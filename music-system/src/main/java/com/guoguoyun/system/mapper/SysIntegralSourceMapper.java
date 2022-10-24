package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.SysIntegralSource;
import org.springframework.stereotype.Repository;

/**
 * 系统管理-积分来源Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Repository
public interface SysIntegralSourceMapper extends BaseMapper<SysIntegralSource>
{
    /**
     * 查询系统管理-积分来源
     * 
     * @param id 系统管理-积分来源主键
     * @return 系统管理-积分来源
     */
    public SysIntegralSource selectSysIntegralSourceById(Long id);

    /**
     * 查询系统管理-积分来源列表
     * 
     * @param sysIntegralSource 系统管理-积分来源
     * @return 系统管理-积分来源集合
     */
    public List<SysIntegralSource> selectSysIntegralSourceList(SysIntegralSource sysIntegralSource);

    /**
     * 新增系统管理-积分来源
     * 
     * @param sysIntegralSource 系统管理-积分来源
     * @return 结果
     */
    public int insertSysIntegralSource(SysIntegralSource sysIntegralSource);

    /**
     * 修改系统管理-积分来源
     * 
     * @param sysIntegralSource 系统管理-积分来源
     * @return 结果
     */
    public int updateSysIntegralSource(SysIntegralSource sysIntegralSource);

    /**
     * 删除系统管理-积分来源
     * 
     * @param id 系统管理-积分来源主键
     * @return 结果
     */
    public int deleteSysIntegralSourceById(Long id);

    /**
     * 批量删除系统管理-积分来源
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysIntegralSourceByIds(Long[] ids);
}
