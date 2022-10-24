package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.guoguoyun.system.domain.AppVersion;
import com.guoguoyun.system.service.IAppVersionService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * app版本Controller
 * 
 * @author ruoyi
 * @date 2022-02-24
 */
@RestController
@RequestMapping("/system/appVersion")
public class AppVersionController extends BaseController
{
    @Autowired
    private IAppVersionService appVersionService;


    /**
     * 新增app版本
     */
    @PreAuthorize("@ss.hasPermi('system:appVersion:add')")
    @Log(title = "app版本", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody AppVersion appVersion)
    {
        appVersion.setCreateBy(getUsername());
        return toAjax(appVersionService.insertAppVersion(appVersion));
    }

    @GetMapping("/getInfo")
    public AjaxResult getInfo(){
        return AjaxResult.success(appVersionService.selectAppVersionById());
    }
}
