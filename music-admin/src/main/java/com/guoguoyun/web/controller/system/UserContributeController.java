package com.guoguoyun.web.controller.system;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
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
import com.guoguoyun.system.domain.UserContribute;
import com.guoguoyun.system.service.IUserContributeService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 投稿Controller
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
@RestController
@RequestMapping("/system/userContribute")
public class UserContributeController extends BaseController
{
    @Autowired
    private IUserContributeService userContributeService;

    /**
     * 查询投稿列表
     */
    @PreAuthorize("@ss.hasPermi('system:userContribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserContribute userContribute)
    {
        startPage();
        PageInfo list = userContributeService.selectUserContributeList(userContribute);
        return getDataTable(list);
    }

    /**
     * 获取投稿详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userContribute:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userContributeService.selectUserContributeById(id));
    }


    /**
     * 修改投稿
     */
    @PreAuthorize("@ss.hasPermi('system:userContribute:edit')")
    @Log(title = "投稿", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody UserContribute userContribute)
    {

        return toAjax(userContributeService.updateUserContribute(userContribute));
    }


    @PostMapping("/checkState")
    public AjaxResult checkState(@RequestBody UserContribute userContribute){
        userContribute.setCheckTime(new Date());
        userContributeService.updateUserContribute(userContribute);
        return AjaxResult.success();
    }

}
