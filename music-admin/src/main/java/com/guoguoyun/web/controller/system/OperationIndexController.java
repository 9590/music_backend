package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.guoguoyun.system.params.OperationIndexParams;
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
import com.guoguoyun.system.domain.OperationIndex;
import com.guoguoyun.system.service.IOperationIndexService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 运营管理-首页管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-23
 */
@RestController
@RequestMapping("/system/operationIndex")
public class OperationIndexController extends BaseController
{
    @Autowired
    private IOperationIndexService operationIndexService;


    /**
     * 新增运营管理-首页管理
     */
    @PreAuthorize("@ss.hasPermi('system:operationIndex:add')")
    @Log(title = "运营管理-首页管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody OperationIndexParams operationIndexParams)
    {
        operationIndexService.insertOperationIndex(operationIndexParams);
        return AjaxResult.success();
    }

    @GetMapping("/list")
    public AjaxResult list(){
        return AjaxResult.success(operationIndexService.selectOperationIndexList());
    }


}
