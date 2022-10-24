package com.guoguoyun.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.ContentDynamicstateParams;
import com.guoguoyun.system.response.ContentDynamicstateResponse;
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
import com.guoguoyun.system.domain.ContentDynamicstate;
import com.guoguoyun.system.service.IContentDynamicstateService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 内容管理-动态管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@RestController
@RequestMapping("/system/dynamicstate")
public class ContentDynamicstateController extends BaseController
{
    @Autowired
    private IContentDynamicstateService contentDynamicstateService;

    /**
     * 查询内容管理-动态管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:dynamicstate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContentDynamicstate contentDynamicstate)
    {

        if(ObjectUtil.isNotEmpty(contentDynamicstate.getStartTime()) && ObjectUtil.isNotEmpty(contentDynamicstate.getEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(contentDynamicstate.getStartTime()));
            hashMap.put("endCreateTime", sdf.format(contentDynamicstate.getEndTime()));
            contentDynamicstate.setParams(hashMap);
        }
        startPage();
        PageInfo pageInfo = contentDynamicstateService.selectContentDynamicstateList(contentDynamicstate);
        return getDataTable(pageInfo);
    }

    /**
     * 获取内容管理-动态管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dynamicstate:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(contentDynamicstateService.selectContentDynamicstateById(id));
    }

    /**
     * 新增内容管理-动态管理
     */
    @PreAuthorize("@ss.hasPermi('system:dynamicstate:add')")
    @Log(title = "内容管理-动态管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) ContentDynamicstateParams contentDynamicstateParams)
    {
        ContentDynamicstate contentDynamicstate = BeanUtil.toBean(contentDynamicstateParams, ContentDynamicstate.class);
        contentDynamicstate.setUpdateTime(DateUtils.getNowDate());
        contentDynamicstate.setCreateBy(getUsername());
        contentDynamicstate.setUpdateBy(getUsername());
        contentDynamicstate.setIssueType(MusicCode.CONTENT_QUEEN);
        if (ObjectUtil.isNotEmpty(contentDynamicstateParams.getImgUrls()) && contentDynamicstateParams.getImgUrls().size() != 0){
            String imgUrls = JSONArray.toJSON(contentDynamicstateParams.getImgUrls()).toString();
            contentDynamicstate.setImgUrls(imgUrls);
        }
        if (ObjectUtil.isNotEmpty(contentDynamicstateParams.getVideoUrl()) && contentDynamicstateParams.getVideoUrl().size() != 0){
            String videoUrls = JSONArray.toJSON(contentDynamicstateParams.getVideoUrl()).toString();
            contentDynamicstate.setVideoUrl(videoUrls);
        }

        return toAjax(contentDynamicstateService.insertContentDynamicstate(contentDynamicstate));
    }

    /**
     * 修改内容管理-动态管理
     */
    @PreAuthorize("@ss.hasPermi('system:dynamicstate:edit')")
    @Log(title = "内容管理-动态管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated(BaseParam.edit.class) ContentDynamicstateParams contentDynamicstateParams)
    {
        ContentDynamicstate contentDynamicstate = BeanUtil.toBean(contentDynamicstateParams, ContentDynamicstate.class);
        contentDynamicstate.setUpdateTime(DateUtils.getNowDate());
        contentDynamicstate.setUpdateBy(getUsername());
        if (ObjectUtil.isNotEmpty(contentDynamicstateParams.getImgUrls()) && contentDynamicstateParams.getImgUrls().size() != 0){
            String imgUrls = JSONArray.toJSON(contentDynamicstateParams.getImgUrls()).toString();
            contentDynamicstate.setImgUrls(imgUrls);
        }
        if (ObjectUtil.isNotEmpty(contentDynamicstateParams.getVideoUrl()) && contentDynamicstateParams.getVideoUrl().size() != 0){
            String videoUrls = JSONArray.toJSON(contentDynamicstateParams.getVideoUrl()).toString();
            contentDynamicstate.setVideoUrl(videoUrls);
        }
        return toAjax(contentDynamicstateService.updateContentDynamicstate(contentDynamicstate));
    }

    /**
     * 删除内容管理-动态管理
     */
    @PreAuthorize("@ss.hasPermi('system:dynamicstate:remove')")
    @Log(title = "内容管理-动态管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contentDynamicstateService.deleteContentDynamicstateByIds(ids));
    }

    @GetMapping("/addUserList")
    public AjaxResult addUserList(String remate){
        return AjaxResult.success(contentDynamicstateService.addUserList(remate));
    }
}
