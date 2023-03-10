package com.guoguoyun.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.QiniuUtils.UpdateParams;
import com.guoguoyun.system.QiniuUtils.UploadService;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.vo.AddAlbumArtVo;
import com.guoguoyun.system.params.AlbumExeclParams;
import com.guoguoyun.system.params.AlbumParams;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.service.IAppUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.guoguoyun.common.annotation.Log;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.enums.BusinessType;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.service.IAlbumService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * 专辑管理-专辑库Controller
 *
 * @author ruoyi
 * @date 2022-02-11
 */
@RestController
@RequestMapping("/system/album")
public class AlbumController extends BaseController
{
    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IAppUserService appUserService;

    @Resource
    private UploadService uploadService;

    /**
     * 查询专辑管理-专辑库列表
     */
    // postman测试时先注释掉
    @PreAuthorize("@ss.hasPermi('system:album:list')")
    @GetMapping("/list")
    public TableDataInfo list(Album album)
    {
        if(ObjectUtil.isNotEmpty(album.getStartTime()) && ObjectUtil.isNotEmpty(album.getEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(album.getStartTime()));
            hashMap.put("endCreateTime", sdf.format(album.getEndTime()));
            album.setParams(hashMap);
        }
        startPage();
        PageInfo list = albumService.selectAlbumList(album);
        return getDataTable(list);
    }

    /**
     * 获取专辑管理-专辑库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:album:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(albumService.selectAlbumById(id));
    }

    /**
     * 新增专辑管理-专辑库
     */
    @PreAuthorize("@ss.hasPermi('system:album:add')")
    @Log(title = "专辑管理-专辑库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) AlbumParams albumParams,@RequestBody AddAlbumArtVo[] addAlbumArtVos) {
        albumParams.setUpdateTime(DateUtils.getNowDate());
        albumParams.setCreateBy(getUsername());
        albumParams.setUpdateBy(getUsername());
        albumService.insertAlbum(albumParams);
        return AjaxResult.success("新增成功");
    }

    /**
     * 新增专辑管理-图片
     */
    @Log(title = "专辑管理-专辑库", businessType = BusinessType.INSERT)
    @PostMapping("/addImg")
    public AjaxResult addImg(@RequestPart MultipartFile file, UpdateParams updateParams) {
        if (file.isEmpty()){
            return AjaxResult.error("文件为空，上传失败");
        }


        return AjaxResult.success("文件上传成功",uploadService.upload(file,updateParams));
    }

    /**
     * 自动生成专辑编号
     * @return
     */
    @GetMapping("/maxAlbumCode")
    public AjaxResult getMaxWorkId() {

        return  albumService.getMaxAlbumCode();
    }



    /**
     * 修改专辑管理-专辑库
     */
    @PreAuthorize("@ss.hasPermi('system:album:edit')")
    @Log(title = "专辑管理-专辑库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated(BaseParam.edit.class) AlbumParams albumParams)
    {
        albumParams.setUpdateBy(getUsername());
        return toAjax(albumService.updateAlbum(albumParams));
    }

    /**
     * 删除专辑管理-专辑库
     */
    @PreAuthorize("@ss.hasPermi('system:album:remove')")
    @Log(title = "专辑管理-专辑库", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(albumService.deleteAlbumByIds(ids));
    }


    /**
     * 艺人列表
     * @return
     */
    @GetMapping("/appUserList")
    public AjaxResult appUserList(){
        QueryWrapper<AppUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AppUser::getUserType, MusicCode.USER_TYPE_ARTIST);
        List<AppUser> appUsers = appUserService.list(wrapper);
        return AjaxResult.success(appUsers);
    }


    /**
     * 批量导入
     * @param file
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<AlbumExeclParams> util = new ExcelUtil<AlbumExeclParams>(AlbumExeclParams.class);
        List<AlbumExeclParams> albumList = util.importExcel(file.getInputStream());
        albumService.importData(albumList);
        return AjaxResult.success();
    }
}
