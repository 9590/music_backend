package com.guoguoyun.system.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import com.guoguoyun.system.domain.SysLevelRule;
import com.guoguoyun.system.params.IntegralAndLevelParams;
import com.guoguoyun.system.service.ISysLevelRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.SysIntegralSourceMapper;
import com.guoguoyun.system.domain.SysIntegralSource;
import com.guoguoyun.system.service.ISysIntegralSourceService;

/**
 * 系统管理-积分来源Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Service
public class SysIntegralSourceServiceImpl extends ServiceImpl<SysIntegralSourceMapper, SysIntegralSource> implements ISysIntegralSourceService
{
    @Autowired
    private SysIntegralSourceMapper sysIntegralSourceMapper;

    @Autowired
    private ISysLevelRuleService sysLevelRuleService;

    /**
     * 查询系统管理-积分来源
     * 
     * @param id 系统管理-积分来源主键
     * @return 系统管理-积分来源
     */
    @Override
    public SysIntegralSource selectSysIntegralSourceById(Long id)
    {
        return sysIntegralSourceMapper.selectSysIntegralSourceById(id);
    }

    /**
     * 查询系统管理-积分来源列表
     *
     * @return 系统管理-积分来源
     */
    @Override
    public IntegralAndLevelParams selectSysIntegralSourceList()
    {
        QueryWrapper<SysIntegralSource> sysIntegralSourceQueryWrapper = new QueryWrapper<>();
        sysIntegralSourceQueryWrapper.lambda().orderByAsc(SysIntegralSource::getType);
        List<SysIntegralSource> sysIntegralSources = this.list(sysIntegralSourceQueryWrapper);

        QueryWrapper<SysLevelRule> sysLevelRuleQueryWrapper = new QueryWrapper<>();
        sysLevelRuleQueryWrapper.lambda().orderByAsc(SysLevelRule::getLevelNum);
        List<SysLevelRule> sysLevelRules = sysLevelRuleService.list(sysLevelRuleQueryWrapper);
        IntegralAndLevelParams integralAndLevelParams = new IntegralAndLevelParams();
        integralAndLevelParams.setIntegralSources(sysIntegralSources);
        integralAndLevelParams.setLevelRules(sysLevelRules);
        return integralAndLevelParams;
    }

    /**
     * 新增系统管理-积分来源
     * 
     * @param sysIntegralSource 系统管理-积分来源
     * @return 结果
     */
    @Override
    public int insertSysIntegralSource(SysIntegralSource sysIntegralSource)
    {
        sysIntegralSource.setCreateTime(DateUtils.getNowDate());
        sysIntegralSource.setId(IdUtil.getSnowflakeNextId());
        return sysIntegralSourceMapper.insertSysIntegralSource(sysIntegralSource);
    }

    @Override
    public void insertSysIntegralSource(IntegralAndLevelParams integralAndLevelParams) {

        //添加或修改积分规则
        integralAndLevelParams.getIntegralSources().forEach(t -> {
            t.setUpdateTime(DateUtils.getNowDate());
            t.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
            if (ObjectUtil.isEmpty(t.getId())){
                t.setCreateTime(DateUtils.getNowDate());
                t.setId(IdUtil.getSnowflakeNextId());
                t.setCreateBy(SecurityUtils.getLoginUser().getUsername());
                this.save(t);
            }else {
                this.updateById(t);
            }
        });

        //添加或修改等级规则
        integralAndLevelParams.getLevelRules().forEach(t -> {
            t.setUpdateTime(DateUtils.getNowDate());
            t.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
            if (ObjectUtil.isEmpty(t.getId())){
                t.setCreateTime(DateUtils.getNowDate());
                t.setId(IdUtil.getSnowflakeNextId());
                t.setCreateBy(SecurityUtils.getLoginUser().getUsername());
                sysLevelRuleService.save(t);
            }else {
                sysLevelRuleService.updateById(t);
            }
        });
    }

    /**
     * 修改系统管理-积分来源
     * 
     * @param sysIntegralSource 系统管理-积分来源
     * @return 结果
     */
    @Override
    public int updateSysIntegralSource(SysIntegralSource sysIntegralSource)
    {
        sysIntegralSource.setUpdateTime(DateUtils.getNowDate());
        return sysIntegralSourceMapper.updateSysIntegralSource(sysIntegralSource);
    }

    /**
     * 批量删除系统管理-积分来源
     * 
     * @param ids 需要删除的系统管理-积分来源主键
     * @return 结果
     */
    @Override
    public int deleteSysIntegralSourceByIds(Long[] ids)
    {
        return sysIntegralSourceMapper.deleteSysIntegralSourceByIds(ids);
    }

    /**
     * 删除系统管理-积分来源信息
     * 
     * @param id 系统管理-积分来源主键
     * @return 结果
     */
    @Override
    public int deleteSysIntegralSourceById(Long id)
    {
        return sysIntegralSourceMapper.deleteSysIntegralSourceById(id);
    }
}
