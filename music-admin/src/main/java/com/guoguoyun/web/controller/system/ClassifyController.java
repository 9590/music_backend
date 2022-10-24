package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.ClassifyParams;
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
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.service.IClassifyService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 商城管理-分类管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@RestController
@RequestMapping("/system/classify")
public class ClassifyController extends BaseController
{
    @Autowired
    private IClassifyService classifyService;

    /**
     * 查询商城管理-分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:classify:list')")
    @GetMapping("/list")
    public TableDataInfo list( @Validated(BaseParam.list.class) ClassifyParams classifyParams)
    {
        Classify classify = BeanUtil.toBean(classifyParams, Classify.class);
        startPage();
        List<Classify> list = classifyService.selectClassifyList(classify);
        return getDataTable(list);
    }

    /**
     * 获取商城管理-分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:classify:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(classifyService.selectClassifyById(id));
    }

    /**
     * 新增商城管理-分类管理
     */
    @PreAuthorize("@ss.hasPermi('system:classify:add')")
    @Log(title = "商城管理-分类管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) ClassifyParams classifyParams)
    {
        Classify classify = BeanUtil.toBean(classifyParams, Classify.class);
        classify.setUpdateTime(DateUtils.getNowDate());
        classify.setCreateBy(getUsername());
        classify.setUpdateBy(getUsername());
        return toAjax(classifyService.insertClassify(classify));
    }

    /**
     * 修改商城管理-分类管理
     */
    @PreAuthorize("@ss.hasPermi('system:classify:edit')")
    @Log(title = "商城管理-分类管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated(BaseParam.edit.class) ClassifyParams classifyParams)
    {
        Classify classify = BeanUtil.toBean(classifyParams, Classify.class);
        classify.setUpdateTime(DateUtils.getNowDate());
        classify.setUpdateBy(getUsername());
        return toAjax(classifyService.updateClassify(classify));
    }

    /**
     * 删除商城管理-分类管理
     */
    @PreAuthorize("@ss.hasPermi('system:classify:remove')")
    @Log(title = "商城管理-分类管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(classifyService.deleteClassifyByIds(ids));
    }

    @GetMapping("/selectList")
    public AjaxResult selectList( ClassifyParams classifyParams){
        Classify classify = BeanUtil.toBean(classifyParams, Classify.class);
        List<Classify> classifies = classifyService.selectClassifyList(classify);
        return AjaxResult.success(classifies);
    }

    @GetMapping("/treeListPage")
    public TableDataInfo treeListPage(Classify classify){
        startPage();
        PageInfo pageInfo = classifyService.treeList(true,classify);
        return getDataTable(pageInfo);
    }


    @GetMapping("/treeList")
    public AjaxResult treeList(){

        List<Tree<String>> trees = classifyService.treeList(1);
        return AjaxResult.success(trees);
    }


}
