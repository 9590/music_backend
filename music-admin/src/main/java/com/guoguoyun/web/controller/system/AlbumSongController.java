package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.params.AlbumExeclParams;
import com.guoguoyun.system.params.AlbumSongExeclParams;
import com.guoguoyun.system.params.AlbumSongParams;
import com.guoguoyun.system.params.BaseParam;
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
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.service.IAlbumSongService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 专辑管理-乐曲管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@RestController
@RequestMapping("/system/song")
public class AlbumSongController extends BaseController
{
    @Autowired
    private IAlbumSongService albumSongService;

    /**
     * 查询专辑管理-乐曲管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:song:list')")
    @GetMapping("/list")
    public TableDataInfo list(AlbumSong albumSong)
    {
        startPage();
        PageInfo list = albumSongService.selectAlbumSongList(albumSong);
        return getDataTable(list);
    }

    /**
     * 获取专辑管理-乐曲管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:song:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(albumSongService.selectAlbumSongById(id));
    }

    /**
     * 新增专辑管理-乐曲管理
     */
    @PreAuthorize("@ss.hasPermi('system:song:add')")
    @Log(title = "专辑管理-乐曲管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) AlbumSongParams albumSongParams)
    {
        AlbumSong albumSong = BeanUtil.toBean(albumSongParams, AlbumSong.class);
        albumSong.setUpdateTime(DateUtils.getNowDate());
        albumSong.setCreateBy(getUsername());
        albumSong.setUpdateBy(getUsername());
        return toAjax(albumSongService.insertAlbumSong(albumSong));
    }

    /**
     * 修改专辑管理-乐曲管理
     */
    @PreAuthorize("@ss.hasPermi('system:song:edit')")
    @Log(title = "专辑管理-乐曲管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated(BaseParam.edit.class) AlbumSongParams albumSongParams)
    {
        AlbumSong albumSong = BeanUtil.toBean(albumSongParams, AlbumSong.class);
        albumSong.setUpdateBy(getUsername());
        return toAjax(albumSongService.updateAlbumSong(albumSong));
    }

    /**
     * 删除专辑管理-乐曲管理
     */
    @PreAuthorize("@ss.hasPermi('system:song:remove')")
    @Log(title = "专辑管理-乐曲管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(albumSongService.deleteAlbumSongByIds(ids));
    }


    /**
     * 曲目不分页列表
     * @return
     */
    @GetMapping("/songList")
    public AjaxResult songList(AlbumSong albumSong){
        QueryWrapper<AlbumSong> albumSongQueryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(albumSong.getSongName())){
            albumSongQueryWrapper.lambda().like(AlbumSong::getSongName,albumSong.getSongName());
        }
        albumSongQueryWrapper.lambda().eq(AlbumSong::getDeleteType,0);
        albumSongQueryWrapper.lambda().orderByDesc(AlbumSong::getCreateTime);
        List<AlbumSong> list = albumSongService.list(albumSongQueryWrapper);
        return AjaxResult.success(list);
    }


    /**
     * 批量导入
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<AlbumSongExeclParams> util = new ExcelUtil<AlbumSongExeclParams>(AlbumSongExeclParams.class);
        List<AlbumSongExeclParams> songList = util.importExcel(file.getInputStream());
        albumSongService.importData(songList);
        return AjaxResult.success();
    }
}
