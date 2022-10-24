package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.guoguoyun.common.utils.DateUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guoguoyun.common.annotation.Log;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.enums.BusinessType;
import com.guoguoyun.system.domain.SysLevelRule;
import com.guoguoyun.system.service.ISysLevelRuleService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 系统管理-等级规则Controller
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@RestController
@RequestMapping("/system/levelRule")
public class SysLevelRuleController extends BaseController
{
    @Autowired
    private ISysLevelRuleService sysLevelRuleService;

    /**
     * 查询系统管理-等级规则列表
     */
    @PreAuthorize("@ss.hasPermi('system:levelRule:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLevelRule sysLevelRule)
    {
        startPage();
        List<SysLevelRule> list = sysLevelRuleService.selectSysLevelRuleList(sysLevelRule);
        return getDataTable(list);
    }

    /**
     * 获取系统管理-等级规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:levelRule:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysLevelRuleService.selectSysLevelRuleById(id));
    }

    /**
     * 新增系统管理-等级规则
     */
    @PreAuthorize("@ss.hasPermi('system:levelRule:add')")
    @Log(title = "系统管理-等级规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysLevelRule sysLevelRule)
    {

        sysLevelRule.setUpdateTime(DateUtils.getNowDate());
        sysLevelRule.setCreateBy(getUsername());
        sysLevelRule.setUpdateBy(getUsername());
        return toAjax(sysLevelRuleService.insertSysLevelRule(sysLevelRule));
    }

    /**
     * 修改系统管理-等级规则
     */
    @PreAuthorize("@ss.hasPermi('system:levelRule:edit')")
    @Log(title = "系统管理-等级规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysLevelRule sysLevelRule)
    {
        return toAjax(sysLevelRuleService.updateSysLevelRule(sysLevelRule));
    }

    /**
     * 删除系统管理-等级规则
     */
    @PreAuthorize("@ss.hasPermi('system:levelRule:remove')")
    @Log(title = "系统管理-等级规则", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysLevelRuleService.deleteSysLevelRuleByIds(ids));
    }
}
