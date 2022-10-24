package com.guoguoyun.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.domain.OperationUserCoupon;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.OperationCouponParams;
import com.guoguoyun.system.params.OperationUserCouponParams;
import com.guoguoyun.system.response.OperationCouponResponse;
import com.guoguoyun.system.response.OperationOrderExeclResponse;
import com.guoguoyun.system.response.OperationUserCouponExeclResponse;
import com.guoguoyun.system.response.OperationUserCouponResponse;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.system.service.IOperationUserCouponService;
import org.aspectj.weaver.loadtime.Aj;
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
import com.guoguoyun.system.domain.OperationCoupon;
import com.guoguoyun.system.service.IOperationCouponService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 运营管理-优惠卷管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@RestController
@RequestMapping("/system/coupon")
public class OperationCouponController extends BaseController
{
    @Autowired
    private IOperationCouponService operationCouponService;


    @Autowired
    private IOperationUserCouponService operationUserCouponService;

    @Autowired
    private IAppUserService appUserService;

    /**
     * 查询运营管理-优惠卷管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(OperationCoupon operationCoupon)
    {
        if(ObjectUtil.isNotEmpty(operationCoupon.getStartTime()) && ObjectUtil.isNotEmpty(operationCoupon.getEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(operationCoupon.getStartTime()));
            hashMap.put("endCreateTime", sdf.format(operationCoupon.getEndTime()));
            operationCoupon.setParams(hashMap);
        }
        startPage();
        PageInfo pageInfo = operationCouponService.selectOperationCouponListByPageInfo(operationCoupon);
        return getDataTable(pageInfo);
    }

    /**
     * 获取运营管理-优惠卷管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(operationCouponService.selectOperationCouponById(id));
    }

    /**
     * 新增运营管理-优惠卷管理
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:add')")
    @Log(title = "运营管理-优惠卷管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) OperationCouponParams operationCouponParams)
    {
        OperationCoupon operationCoupon = BeanUtil.toBean(operationCouponParams, OperationCoupon.class);
        operationCoupon.setUpdateTime(DateUtils.getNowDate());
        operationCoupon.setCreateBy(getUsername());
        operationCoupon.setUpdateBy(getUsername());
        return toAjax(operationCouponService.insertOperationCoupon(operationCoupon));
    }

    /**
     * 修改运营管理-优惠卷管理
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:edit')")
    @Log(title = "运营管理-优惠卷管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated(BaseParam.edit.class) OperationCouponParams operationCouponParams)
    {
        OperationCoupon operationCoupon = BeanUtil.toBean(operationCouponParams, OperationCoupon.class);
        operationCoupon.setUpdateTime(DateUtils.getNowDate());
        operationCoupon.setUpdateBy(getUsername());
        return toAjax(operationCouponService.updateOperationCoupon(operationCoupon));
    }

    /**
     * 删除运营管理-优惠卷管理
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:remove')")
    @Log(title = "运营管理-优惠卷管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(operationCouponService.deleteOperationCouponByIds(ids));
    }

    @GetMapping("/selectList")
    public AjaxResult selectList(OperationCoupon operationCoupon){
        return AjaxResult.success(operationCouponService.selectOperationCouponList(operationCoupon));
    }

    @PostMapping("/addUserCoupon")
    public AjaxResult addUserCoupon(@RequestBody @Validated({BaseParam.add.class}) OperationUserCouponParams operationUserCouponParams){
        operationCouponService.addUserCoupon(operationUserCouponParams);
        return AjaxResult.success();
    }

    @GetMapping("/operationUserCouponList")
    public TableDataInfo operationUserCouponList(OperationUserCoupon operationUserCoupon){

        if(ObjectUtil.isNotEmpty(operationUserCoupon.getStartsTime()) && ObjectUtil.isNotEmpty(operationUserCoupon.getEndsTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(operationUserCoupon.getStartsTime()));
            hashMap.put("endCreateTime", sdf.format(operationUserCoupon.getEndsTime()));
            operationUserCoupon.setParams(hashMap);
        }
        if(ObjectUtil.isNotEmpty(operationUserCoupon.getUseStartTime()) && ObjectUtil.isNotEmpty(operationUserCoupon.getUseEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginUseCreateTime", sdf.format(operationUserCoupon.getUseStartTime()));
            hashMap.put("endUseCreateTime", sdf.format(operationUserCoupon.getUseEndTime()));
            operationUserCoupon.setParams(hashMap);
        }

        startPage();
        PageInfo pageInfo = operationUserCouponService.selectOperationUserCouponListByPageInfo(operationUserCoupon);
        return getDataTable(pageInfo);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response,OperationUserCoupon operationUserCoupon){
        if(ObjectUtil.isNotEmpty(operationUserCoupon.getStartsTime()) && ObjectUtil.isNotEmpty(operationUserCoupon.getEndsTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(operationUserCoupon.getStartsTime()));
            hashMap.put("endCreateTime", sdf.format(operationUserCoupon.getEndsTime()));
            operationUserCoupon.setParams(hashMap);
        }
        if(ObjectUtil.isNotEmpty(operationUserCoupon.getUseStartTime()) && ObjectUtil.isNotEmpty(operationUserCoupon.getUseEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginUseCreateTime", sdf.format(operationUserCoupon.getUseStartTime()));
            hashMap.put("endUseCreateTime", sdf.format(operationUserCoupon.getUseEndTime()));
            operationUserCoupon.setParams(hashMap);
        }
        List<OperationUserCouponExeclResponse> operationUserCouponExeclResponses = operationUserCouponService.selectOperationUserCouponList(operationUserCoupon).stream().map(t -> BeanUtil.toBean(t, OperationUserCouponExeclResponse.class)).collect(Collectors.toList());
        ExcelUtil<OperationUserCouponExeclResponse> util = new ExcelUtil<>(OperationUserCouponExeclResponse.class);
        util.exportExcel(response,operationUserCouponExeclResponses,"优惠券发放信息");

    }

    @GetMapping("/ordinaryUserList")
    public AjaxResult ordinaryUserList(){
        QueryWrapper<AppUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AppUser::getUserType, MusicCode.USER_TYPE_ORDINARY);
        List<AppUser> appUsers = appUserService.list(wrapper);
        return AjaxResult.success(appUsers);
    }
}
