package com.guoguoyun.web.controller.system;

import java.util.List;


import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.vo.AddAlbumArtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guoguoyun.common.annotation.Log;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.enums.BusinessType;
import com.guoguoyun.system.domain.AlbumArtist;
import com.guoguoyun.system.service.IAlbumArtistService;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 参与艺人Controller
 *
 * @author xx
 * @date 2022-11-18
 */
@RestController
@RequestMapping("/system/artists")
public class AlbumArtistController extends BaseController
{
    @Autowired
    private IAlbumArtistService albumArtistService;

    /**
     * 查询参与艺人列表
     */

    @GetMapping("/list")
    public AjaxResult list(AlbumArtist albumArtist)
    {
        List<AlbumArtist> list = albumArtistService.selectAlbumArtistList(albumArtist);
        return AjaxResult.success(list);
    }

    /**
     * 查询参与艺人列表
     */

    @GetMapping("/listPage")
    public TableDataInfo listPage(AlbumArtist albumArtist)
    {
        startPage();
        List<AlbumArtist> list = albumArtistService.selectAlbumArtistList(albumArtist);
        return getDataTable(list);

    }

    /**
     * 获取参与艺人详细信息
     */

    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(albumArtistService.selectAlbumArtistById(id));
    }

    /**
     * 新增参与艺人
     */

    @Log(title = "参与艺人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody AlbumArtist albumArtist)
    {

        albumArtist.setUpdateTime(DateUtils.getNowDate());
        albumArtist.setCreateBy(getUsername());
        albumArtist.setUpdateBy(getUsername());
        return toAjax(albumArtistService.insertAlbumArtist(albumArtist));
    }

    /**
     * 修改参与艺人
     */
    @Log(title = "参与艺人", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody AlbumArtist albumArtist)
    {
        albumArtist.setUpdateTime(DateUtils.getNowDate());
        albumArtist.setUpdateBy(getUsername());
        return toAjax(albumArtistService.updateAlbumArtist(albumArtist));
    }

    /**
     * 删除参与艺人
     */
    @Log(title = "参与艺人", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(albumArtistService.deleteAlbumArtistByIds(ids));
    }
}
