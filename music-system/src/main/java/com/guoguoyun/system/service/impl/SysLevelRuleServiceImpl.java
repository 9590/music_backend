package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.SysLevelRuleMapper;
import com.guoguoyun.system.domain.SysLevelRule;
import com.guoguoyun.system.service.ISysLevelRuleService;

/**
 * 系统管理-等级规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Service
public class SysLevelRuleServiceImpl extends ServiceImpl<SysLevelRuleMapper, SysLevelRule> implements ISysLevelRuleService
{
    @Autowired
    private SysLevelRuleMapper sysLevelRuleMapper;

    /**
     * 查询系统管理-等级规则
     * 
     * @param id 系统管理-等级规则主键
     * @return 系统管理-等级规则
     */
    @Override
    public SysLevelRule selectSysLevelRuleById(Long id)
    {
        return sysLevelRuleMapper.selectSysLevelRuleById(id);
    }

    /**
     * 查询系统管理-等级规则列表
     * 
     * @param sysLevelRule 系统管理-等级规则
     * @return 系统管理-等级规则
     */
    @Override
    public List<SysLevelRule> selectSysLevelRuleList(SysLevelRule sysLevelRule)
    {
        return sysLevelRuleMapper.selectSysLevelRuleList(sysLevelRule);
    }

    /**
     * 新增系统管理-等级规则
     * 
     * @param sysLevelRule 系统管理-等级规则
     * @return 结果
     */
    @Override
    public int insertSysLevelRule(SysLevelRule sysLevelRule)
    {
        sysLevelRule.setCreateTime(DateUtils.getNowDate());
        sysLevelRule.setId(IdUtil.getSnowflakeNextId());
        return sysLevelRuleMapper.insertSysLevelRule(sysLevelRule);
    }

    /**
     * 修改系统管理-等级规则
     * 
     * @param sysLevelRule 系统管理-等级规则
     * @return 结果
     */
    @Override
    public int updateSysLevelRule(SysLevelRule sysLevelRule)
    {
        sysLevelRule.setUpdateTime(DateUtils.getNowDate());
        return sysLevelRuleMapper.updateSysLevelRule(sysLevelRule);
    }

    /**
     * 批量删除系统管理-等级规则
     * 
     * @param ids 需要删除的系统管理-等级规则主键
     * @return 结果
     */
    @Override
    public int deleteSysLevelRuleByIds(Long[] ids)
    {
        return sysLevelRuleMapper.deleteSysLevelRuleByIds(ids);
    }

    /**
     * 删除系统管理-等级规则信息
     * 
     * @param id 系统管理-等级规则主键
     * @return 结果
     */
    @Override
    public int deleteSysLevelRuleById(Long id)
    {
        return sysLevelRuleMapper.deleteSysLevelRuleById(id);
    }
}
