package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.guoguoyun.system.params.AppUserActorParams;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.response.AppUserResponse;
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
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * app端用户Controller
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@RestController
@RequestMapping("/system/appUser")
public class AppUserController extends BaseController
{
    @Autowired
    private IAppUserService appUserService;

    /**
     * 查询app端用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:appUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppUser appUser)
    {
        startPage();
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        return getDataTable(list);
    }

    /**
     * 获取app端用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:appUser:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appUserService.selectAppUserById(id));
    }

    /**
     * 新增app端用户
     */
    @PreAuthorize("@ss.hasPermi('system:appUser:add')")
    @Log(title = "app端用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.insertAppUser(appUser));
    }

    /**
     * 修改app端用户
     */
    @PreAuthorize("@ss.hasPermi('system:appUser:edit')")
    @Log(title = "app端用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody AppUser appUser)
    {
        appUser.setUpdateBy(getUsername());
        return toAjax(appUserService.updateAppUser(appUser));
    }

    /**
     * 删除app端用户
     */
    @PreAuthorize("@ss.hasPermi('system:appUser:remove')")
    @Log(title = "app端用户", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(appUserService.deleteAppUserByIds(ids));
    }


    /**
     * 添加艺人
     * @return
     */
    @PostMapping("/saveActor")
    public AjaxResult saveActor(@RequestBody @Validated(BaseParam.saveActor.class) AppUserActorParams appUserActorParams){
        appUserService.saveActor(appUserActorParams);
        return AjaxResult.success();
    }


    /**
     * 修改艺人
     * @return
     */
    @PostMapping("/updateActor")
    public AjaxResult updateActor(@RequestBody @Validated(BaseParam.edit.class) AppUserActorParams appUserActorParams){
        appUserService.updateActor(appUserActorParams);
        return AjaxResult.success();
    }


    /**
     * 查看艺术家
     * @param id
     * @return
     */
    @GetMapping(value = "/getActorInfo/{id}")
    public AjaxResult getActorInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appUserService.getOneActorUser(id));
    }
}
