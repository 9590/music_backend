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
import com.guoguoyun.system.domain.AlbumUser;
import com.guoguoyun.system.service.IAlbumUserService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 专辑参与艺人Controller
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@RestController
@RequestMapping("/system/albumUser")
public class AlbumUserController extends BaseController
{
    @Autowired
    private IAlbumUserService albumUserService;

    /**
     * 查询专辑参与艺人列表
     */
    @PreAuthorize("@ss.hasPermi('system:albumUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlbumUser albumUser)
    {
        startPage();
        List<AlbumUser> list = albumUserService.selectAlbumUserList(albumUser);
        return getDataTable(list);
    }

    /**
     * 获取专辑参与艺人详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:albumUser:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(albumUserService.selectAlbumUserById(id));
    }

    /**
     * 新增专辑参与艺人
     */
    @PreAuthorize("@ss.hasPermi('system:albumUser:add')")
    @Log(title = "专辑参与艺人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody AlbumUser albumUser)
    {
        return toAjax(albumUserService.insertAlbumUser(albumUser));
    }

    /**
     * 修改专辑参与艺人
     */
    @PreAuthorize("@ss.hasPermi('system:albumUser:edit')")
    @Log(title = "专辑参与艺人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody AlbumUser albumUser)
    {
        return toAjax(albumUserService.updateAlbumUser(albumUser));
    }

    /**
     * 删除专辑参与艺人
     */
    @PreAuthorize("@ss.hasPermi('system:albumUser:remove')")
    @Log(title = "专辑参与艺人", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(albumUserService.deleteAlbumUserByIds(ids));
    }
}
