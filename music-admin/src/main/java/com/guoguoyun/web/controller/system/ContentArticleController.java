package com.guoguoyun.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.ContentArticleParams;
import com.guoguoyun.system.params.ShoppingCommodityParams;
import com.guoguoyun.system.response.ContentArticleResponse;
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
import com.guoguoyun.system.domain.ContentArticle;
import com.guoguoyun.system.service.IContentArticleService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 内容管理-文章管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/system/article")
public class ContentArticleController extends BaseController
{
    @Autowired
    private IContentArticleService contentArticleService;

    /**
     * 查询内容管理-文章管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContentArticle contentArticle)
    {
        if(ObjectUtil.isNotEmpty(contentArticle.getStartTime()) && ObjectUtil.isNotEmpty(contentArticle.getEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(contentArticle.getStartTime()));
            hashMap.put("endCreateTime", sdf.format(contentArticle.getEndTime()));
            contentArticle.setParams(hashMap);
        }
        startPage();
        PageInfo pageInfo = contentArticleService.selectContentArticleList(contentArticle);
        return getDataTable(pageInfo);
    }

    /**
     * 获取内容管理-文章管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:article:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(contentArticleService.selectContentArticleById(id));
    }

    /**
     * 新增内容管理-文章管理
     */
    @PreAuthorize("@ss.hasPermi('system:article:add')")
    @Log(title = "内容管理-文章管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) ContentArticleParams contentArticleParams)
    {

        contentArticleParams.setUpdateTime(DateUtils.getNowDate());
        contentArticleParams.setCreateBy(getUsername());
        contentArticleParams.setUpdateBy(getUsername());
        contentArticleParams.setUserId(getUserId());
        contentArticleService.insertContentArticle(contentArticleParams);
        return AjaxResult.success();
    }

    /**
     * 修改内容管理-文章管理
     */
    @PreAuthorize("@ss.hasPermi('system:article:edit')")
    @Log(title = "内容管理-文章管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated(BaseParam.edit.class) ContentArticleParams contentArticleParams)
    {
        contentArticleParams.setUpdateBy(getUsername());
        contentArticleParams.setUserId(getUserId());
        contentArticleService.updateContentArticle(contentArticleParams);
        return AjaxResult.success();
    }

    /**
     * 删除内容管理-文章管理
     */
    @PreAuthorize("@ss.hasPermi('system:article:remove')")
    @Log(title = "内容管理-文章管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contentArticleService.deleteContentArticleByIds(ids));
    }

    /**
     * 置顶
     */
    @PostMapping("/updateTop/{id}")
    public AjaxResult updateTop(@PathVariable("id") Long id){
        contentArticleService.updateTop(id);
        return AjaxResult.success();
    }
}
