package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.SysLevelRule;

/**
 * 系统管理-等级规则Service接口
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
public interface ISysLevelRuleService extends IService<SysLevelRule>
{
    /**
     * 查询系统管理-等级规则
     * 
     * @param id 系统管理-等级规则主键
     * @return 系统管理-等级规则
     */
    public SysLevelRule selectSysLevelRuleById(Long id);

    /**
     * 查询系统管理-等级规则列表
     * 
     * @param sysLevelRule 系统管理-等级规则
     * @return 系统管理-等级规则集合
     */
    public List<SysLevelRule> selectSysLevelRuleList(SysLevelRule sysLevelRule);

    /**
     * 新增系统管理-等级规则
     * 
     * @param sysLevelRule 系统管理-等级规则
     * @return 结果
     */
    public int insertSysLevelRule(SysLevelRule sysLevelRule);

    /**
     * 修改系统管理-等级规则
     * 
     * @param sysLevelRule 系统管理-等级规则
     * @return 结果
     */
    public int updateSysLevelRule(SysLevelRule sysLevelRule);

    /**
     * 批量删除系统管理-等级规则
     * 
     * @param ids 需要删除的系统管理-等级规则主键集合
     * @return 结果
     */
    public int deleteSysLevelRuleByIds(Long[] ids);

    /**
     * 删除系统管理-等级规则信息
     * 
     * @param id 系统管理-等级规则主键
     * @return 结果
     */
    public int deleteSysLevelRuleById(Long id);
}
