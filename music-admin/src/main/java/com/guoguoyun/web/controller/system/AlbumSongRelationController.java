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
import com.guoguoyun.system.domain.AlbumSongRelation;
import com.guoguoyun.system.service.IAlbumSongRelationService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 乐曲和专辑关系Controller
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
@RestController
@RequestMapping("/system/relation")
public class AlbumSongRelationController extends BaseController
{
    @Autowired
    private IAlbumSongRelationService albumSongRelationService;

    /**
     * 查询乐曲和专辑关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlbumSongRelation albumSongRelation)
    {
        startPage();
        List<AlbumSongRelation> list = albumSongRelationService.selectAlbumSongRelationList(albumSongRelation);
        return getDataTable(list);
    }

    /**
     * 获取乐曲和专辑关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(albumSongRelationService.selectAlbumSongRelationById(id));
    }

    /**
     * 新增乐曲和专辑关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "乐曲和专辑关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody AlbumSongRelation albumSongRelation)
    {

        return toAjax(albumSongRelationService.insertAlbumSongRelation(albumSongRelation));
    }

    /**
     * 修改乐曲和专辑关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "乐曲和专辑关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody AlbumSongRelation albumSongRelation)
    {
        return toAjax(albumSongRelationService.updateAlbumSongRelation(albumSongRelation));
    }

    /**
     * 删除乐曲和专辑关系
     */
    @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "乐曲和专辑关系", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(albumSongRelationService.deleteAlbumSongRelationByIds(ids));
    }
}
