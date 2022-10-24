package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.OperationAdvertisingParams;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
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
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.service.IOperationAdvertisingService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 运行管理-广告管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@RestController
@RequestMapping("/system/advertising")
public class OperationAdvertisingController extends BaseController
{
    @Autowired
    private IOperationAdvertisingService operationAdvertisingService;

    /**
     * 查询运行管理-广告管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:advertising:list')")
    @GetMapping("/list")
    public TableDataInfo list(OperationAdvertising operationAdvertising)
    {
        startPage();
        PageInfo pageInfo = operationAdvertisingService.selectOperationAdvertisingList(operationAdvertising);
        return getDataTable(pageInfo);
    }

    /**
     * 获取运行管理-广告管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:advertising:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(operationAdvertisingService.selectOperationAdvertisingById(id));
    }

    /**
     * 新增运行管理-广告管理
     */
    @PreAuthorize("@ss.hasPermi('system:advertising:add')")
    @Log(title = "运行管理-广告管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) OperationAdvertisingParams operationAdvertisingParams)
    {
        OperationAdvertising operationAdvertising = BeanUtil.toBean(operationAdvertisingParams, OperationAdvertising.class);
        operationAdvertising.setUpdateTime(DateUtils.getNowDate());
        operationAdvertising.setCreateBy(getUsername());
        operationAdvertising.setUpdateBy(getUsername());
        return toAjax(operationAdvertisingService.insertOperationAdvertising(operationAdvertising));
    }

    /**
     * 修改运行管理-广告管理
     */
    @PreAuthorize("@ss.hasPermi('system:advertising:edit')")
    @Log(title = "运行管理-广告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated(BaseParam.edit.class) OperationAdvertisingParams operationAdvertisingParams)
    {
        OperationAdvertising operationAdvertising = BeanUtil.toBean(operationAdvertisingParams, OperationAdvertising.class);
        operationAdvertising.setUpdateBy(getUsername());
        return toAjax(operationAdvertisingService.updateOperationAdvertising(operationAdvertising));
    }

    /**
     * 删除运行管理-广告管理
     */
    @PreAuthorize("@ss.hasPermi('system:advertising:remove')")
    @Log(title = "运行管理-广告管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(operationAdvertisingService.deleteOperationAdvertisingByIds(ids));
    }
}
