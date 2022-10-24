package com.guoguoyun.system.params;

import com.guoguoyun.system.domain.SysIntegralSource;
import com.guoguoyun.system.domain.SysLevelRule;

import javax.validation.constraints.NotNull;
import java.util.List;

public class IntegralAndLevelParams extends BaseParam{


    @NotNull(message = "积分规则不能为空", groups = {BaseParam.edit.class,add.class})
    private List<SysIntegralSource> integralSources;


    @NotNull(message = "等级规则不能为空", groups = {BaseParam.edit.class,add.class})
    private List<SysLevelRule> levelRules;

    public List<SysIntegralSource> getIntegralSources() {
        return integralSources;
    }

    public void setIntegralSources(List<SysIntegralSource> integralSources) {
        this.integralSources = integralSources;
    }

    public List<SysLevelRule> getLevelRules() {
        return levelRules;
    }

    public void setLevelRules(List<SysLevelRule> levelRules) {
        this.levelRules = levelRules;
    }
}
