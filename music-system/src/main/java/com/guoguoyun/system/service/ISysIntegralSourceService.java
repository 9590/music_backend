package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.SysIntegralSource;
import com.guoguoyun.system.params.IntegralAndLevelParams;

/**
 * 系统管理-积分来源Service接口
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
public interface ISysIntegralSourceService extends IService<SysIntegralSource>
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
     * @return 系统管理-积分来源集合
     */
    public IntegralAndLevelParams selectSysIntegralSourceList();

    /**
     * 新增系统管理-积分来源
     * 
     * @param sysIntegralSource 系统管理-积分来源
     * @return 结果
     */
    public int insertSysIntegralSource(SysIntegralSource sysIntegralSource);
    public void insertSysIntegralSource(IntegralAndLevelParams integralAndLevelParams);

    /**
     * 修改系统管理-积分来源
     * 
     * @param sysIntegralSource 系统管理-积分来源
     * @return 结果
     */
    public int updateSysIntegralSource(SysIntegralSource sysIntegralSource);

    /**
     * 批量删除系统管理-积分来源
     * 
     * @param ids 需要删除的系统管理-积分来源主键集合
     * @return 结果
     */
    public int deleteSysIntegralSourceByIds(Long[] ids);

    /**
     * 删除系统管理-积分来源信息
     * 
     * @param id 系统管理-积分来源主键
     * @return 结果
     */
    public int deleteSysIntegralSourceById(Long id);
}
