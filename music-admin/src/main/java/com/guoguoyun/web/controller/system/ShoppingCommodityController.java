package com.guoguoyun.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.ShoppingCommodityParams;
import com.guoguoyun.system.response.ShoppingCommodityResponse;
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
import com.guoguoyun.system.domain.ShoppingCommodity;
import com.guoguoyun.system.service.IShoppingCommodityService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 商城管理-商品管理Controller
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@RestController
@RequestMapping("/system/commodity")
public class ShoppingCommodityController extends BaseController
{
    @Autowired
    private IShoppingCommodityService shoppingCommodityService;

    /**
     * 查询商城管理-商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShoppingCommodityParams shoppingCommodityParams)
    {

        ShoppingCommodity shoppingCommodity = BeanUtil.toBean(shoppingCommodityParams, ShoppingCommodity.class);
        if(ObjectUtil.isNotEmpty(shoppingCommodityParams.getStartTime()) && ObjectUtil.isNotEmpty(shoppingCommodityParams.getEndTime())){
            HashMap<String, Object> hashMap = new HashMap<>();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            hashMap.put("beginCreateTime", sdf.format(shoppingCommodityParams.getStartTime()));
            hashMap.put("endCreateTime", sdf.format(shoppingCommodityParams.getEndTime()));
            shoppingCommodity.setParams(hashMap);
        }
        startPage();
        PageInfo pageInfo = shoppingCommodityService.selectShoppingCommodityList(shoppingCommodity);
        return getDataTable(pageInfo);
    }

    /**
     * 获取商城管理-商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:query')")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shoppingCommodityService.selectShoppingCommodityById(id));
    }

    /**
     * 新增商城管理-商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:add')")
    @Log(title = "商城管理-商品管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody @Validated(BaseParam.add.class) ShoppingCommodityParams shoppingCommodityParams)
    {
        ShoppingCommodity shoppingCommodity = BeanUtil.toBean(shoppingCommodityParams, ShoppingCommodity.class);
        shoppingCommodity.setUpdateTime(DateUtils.getNowDate());
        shoppingCommodity.setCreateBy(getUsername());
        shoppingCommodity.setUpdateBy(getUsername());
        String string = JSONArray.toJSON(shoppingCommodityParams.getShopImgs()).toString();
        shoppingCommodity.setShopImg(string);
        return toAjax(shoppingCommodityService.insertShoppingCommodity(shoppingCommodity));
    }

    /**
     * 修改商城管理-商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:edit')")
    @Log(title = "商城管理-商品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit( @RequestBody @Validated(BaseParam.edit.class) ShoppingCommodityParams shoppingCommodityParams)
    {
        ShoppingCommodity shoppingCommodity = BeanUtil.toBean(shoppingCommodityParams, ShoppingCommodity.class);
        shoppingCommodity.setUpdateTime(DateUtils.getNowDate());
        shoppingCommodity.setUpdateBy(getUsername());
        String string = JSONArray.toJSON(shoppingCommodityParams.getShopImgs()).toString();
        shoppingCommodity.setShopImg(string);
        return toAjax(shoppingCommodityService.updateShoppingCommodity(shoppingCommodity));
    }

    /**
     * 删除商城管理-商品管理
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:remove')")
    @Log(title = "商城管理-商品管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shoppingCommodityService.deleteShoppingCommodityByIds(ids));
    }


    /**
     * 置顶
     */
    @PostMapping("/updateTop")
    public AjaxResult updateTop(@RequestBody @Validated(BaseParam.updateTop.class) ShoppingCommodityParams shoppingCommodityParams){
        shoppingCommodityService.updateTop(shoppingCommodityParams);
        return AjaxResult.success();
    }
}
