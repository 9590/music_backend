package com.guoguoyun.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.core.domain.entity.SysDictType;
import com.guoguoyun.common.core.domain.entity.SysUser;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.response.OperationOrderExeclResponse;
import com.guoguoyun.system.response.OperationOrderResponse;
import com.guoguoyun.system.service.IClassifyService;
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
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.service.IOperationOrderService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 运营管理-订单管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
@RestController
@RequestMapping("/system/order")
public class OperationOrderController extends BaseController
{
    @Autowired
    private IOperationOrderService operationOrderService;

    @Autowired
    private IClassifyService classifyService;

    /**
     * 查询运营管理-订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(OperationOrder operationOrder)
    {

        if(ObjectUtil.isNotEmpty(operationOrder.getStartTime()) && ObjectUtil.isNotEmpty(operationOrder.getEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(operationOrder.getStartTime()));
            hashMap.put("endCreateTime", sdf.format(operationOrder.getEndTime()));
            operationOrder.setParams(hashMap);
        }
        startPage();
        PageInfo pageInfo = operationOrderService.selectOperationOrderListByPageInfo(operationOrder);
        return getDataTable(pageInfo);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response,OperationOrder operationOrder){
        if(ObjectUtil.isNotEmpty(operationOrder.getStartTime()) && ObjectUtil.isNotEmpty(operationOrder.getEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(operationOrder.getStartTime()));
            hashMap.put("endCreateTime", sdf.format(operationOrder.getEndTime()));
            operationOrder.setParams(hashMap);
        }
        List<OperationOrderResponse> list = operationOrderService.selectOperationOrderList(operationOrder);
        List<OperationOrderExeclResponse> collect = list.stream().map(t -> BeanUtil.toBean(t, OperationOrderExeclResponse.class)).collect(Collectors.toList());
        ExcelUtil<OperationOrderExeclResponse> util = new ExcelUtil<>(OperationOrderExeclResponse.class);
        util.exportExcel(response,collect,"订单信息");
    }

    @GetMapping("/typeList")
    public AjaxResult typeList(){
        List<Tree<String>> trees = classifyService.treeList(2);
        return AjaxResult.success(trees);
    }
}
