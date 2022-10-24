package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.IntegralAndLevelParams;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.guoguoyun.system.domain.SysIntegralSource;
import com.guoguoyun.system.service.ISysIntegralSourceService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 系统管理-积分来源Controller
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@RestController
@RequestMapping("/system/IntegralSource")
public class SysIntegralSourceController extends BaseController
{
    @Autowired
    private ISysIntegralSourceService sysIntegralSourceService;

    /**
     * 查询系统管理-积分来源列表
     */
    @PreAuthorize("@ss.hasPermi('system:IntegralSource:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success(sysIntegralSourceService.selectSysIntegralSourceList());
    }


    /**
     * 新增系统管理-积分来源
     */
    @PreAuthorize("@ss.hasPermi('system:IntegralSource:add')")
    @Log(title = "系统管理-积分来源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) IntegralAndLevelParams integralAndLevelParams)
    {
        sysIntegralSourceService.insertSysIntegralSource(integralAndLevelParams);
        return AjaxResult.success();
    }

}
