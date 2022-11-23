package com.guoguoyun.web.controller.system;

import java.util.List;

import com.guoguoyun.system.domain.vo.AddAlbumArtVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.guoguoyun.common.annotation.Log;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.enums.BusinessType;
import com.guoguoyun.system.domain.AlbumAndArtist;
import com.guoguoyun.system.service.IAlbumAndArtistService;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 专辑、艺人关联Controller
 *
 * @author xx
 * @date 2022-11-18
 */
@RestController
@RequestMapping("/system/albumAndArtist")
public class AlbumAndArtistController extends BaseController
{
    @Autowired
    private IAlbumAndArtistService albumAndArtistService;

    /**
     * 查询专辑、艺人关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:artist:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlbumAndArtist albumAndArtist)
    {
        startPage();
        List<AlbumAndArtist> list = albumAndArtistService.selectAlbumAndArtistList(albumAndArtist);
        return getDataTable(list);
    }

    /**
     * 获取专辑、艺人关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:artist:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(albumAndArtistService.selectAlbumAndArtistById(id));
    }

    /**
     * 修改专辑、艺人关联
     */
    @PreAuthorize("@ss.hasPermi('system:artist:edit')")
    @Log(title = "专辑、艺人关联", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody AlbumAndArtist albumAndArtist)
    {
        return toAjax(albumAndArtistService.updateAlbumAndArtist(albumAndArtist));
    }

    /**
     * 删除专辑、艺人关联
     */
    @PreAuthorize("@ss.hasPermi('system:artist:remove')")
    @Log(title = "专辑、艺人关联", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(albumAndArtistService.deleteAlbumAndArtistByIds(ids));
    }


    /**
     * 新增专辑、艺人关联
     */

    @PostMapping("/add")
    public AjaxResult add(@RequestBody AddAlbumArtVo addAlbumArtVo)
    {
        return toAjax(albumAndArtistService.insertAlbumAndArtist(addAlbumArtVo));
    }

    /**
     * 删除专辑、艺人关联
     */

    @PostMapping("/removeByAlbumIdAndArtId")
    public AjaxResult removeByAlbumIdAndArtId(@RequestBody AddAlbumArtVo addAlbumArtVo)
    {
        return toAjax(albumAndArtistService.deleteAlbumAndArtistId(addAlbumArtVo));
    }

    /**
     * 获取专辑、艺人关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:artist:query')")
    @GetMapping(value = "/getInfoByAlbumId/{albumId}")
    public AjaxResult getInfoByAlbumId(@PathVariable("albumId") Long albumId)
    {
        return AjaxResult.success(albumAndArtistService.getInfoByAlbumId(albumId));
    }



}
