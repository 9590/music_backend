package com.guoguoyun.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.constant.MusicCode;
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
import com.guoguoyun.system.domain.ContactUs;
import com.guoguoyun.system.service.IContactUsService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 联系我们Controller
 * 
 * @author ruoyi
 * @date 2022-02-24
 */
@RestController
@RequestMapping("/system/contactUs")
public class ContactUsController extends BaseController
{
    @Autowired
    private IContactUsService contactUsService;

    /**
     * 通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:contactUs:list')")
    @GetMapping("/informList")
    public TableDataInfo informList(ContactUs contactUs)
    {
        startPage();
        List<ContactUs> list = contactUsService.selectContactUsList(contactUs);
        return getDataTable(list);
    }

    /**
     * 发布通知
     * @param contactUs
     * @return
     */
    @PostMapping("/addInform")
    public AjaxResult addInform(@RequestBody ContactUs contactUs){
        contactUs.setCreateBy(getUsername());
        contactUs.setType(MusicCode.CONTACT_US_TYPE_INFORM);
        return toAjax(contactUsService.insertContactUs(contactUs));
    }


    /**
     * 撤销通知
     * @param id
     * @return
     */
    @PostMapping("/revocationInform/{id}")
    public AjaxResult revocationInform(@PathVariable("id") Long id){
        contactUsService.deleteContactUsById(id);
        return AjaxResult.success();
    }


    /**
     * 发布联系我们
     * @param contactUs
     * @return
     */
    @PostMapping("/addContactUs")
    public AjaxResult addContactUs(@RequestBody ContactUs contactUs){
        if (ObjectUtil.isNotEmpty(contactUs.getId())){
            contactUs.setUpdateBy(getUsername());
            return toAjax(contactUsService.updateContactUs(contactUs));
        }else {
            contactUs.setCreateBy(getUsername());
            contactUs.setType(MusicCode.CONTACT_US_TYPE_CONTACT_US);
            return toAjax(contactUsService.insertContactUs(contactUs));
        }

    }

    /**
     * 查看联系我们
     * @return
     */
    @GetMapping(value = "/getInfoContactUs")
    public AjaxResult getInfoContactUs(){
        QueryWrapper<ContactUs> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ContactUs::getType,MusicCode.CONTACT_US_TYPE_CONTACT_US);
        ContactUs contactUs = contactUsService.getOne(wrapper);
        return AjaxResult.success(contactUs);
    }


    /**
     * 发布隐私规则
     * @param contactUs
     * @return
     */
    @PostMapping("/addPrivacyRole")
    public AjaxResult addPrivacyRole(@RequestBody ContactUs contactUs){
        if (ObjectUtil.isNotEmpty(contactUs.getId())){
            contactUs.setUpdateBy(getUsername());
            return toAjax(contactUsService.updateContactUs(contactUs));
        }else {
            contactUs.setCreateBy(getUsername());
            contactUs.setType(MusicCode.CONTACT_US_TYPE_PRIVACY_ROLE);
            return toAjax(contactUsService.insertContactUs(contactUs));
        }
    }

    /**
     * 查看隐私规则
     * @return
     */
    @GetMapping(value = "/getInfoPrivacyRole")
    public AjaxResult getInfoPrivacyRole(){
        QueryWrapper<ContactUs> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ContactUs::getType,MusicCode.CONTACT_US_TYPE_PRIVACY_ROLE);
        ContactUs contactUs = contactUsService.getOne(wrapper);
        return AjaxResult.success(contactUs);
    }


}
