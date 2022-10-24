package com.guoguoyun.web.controller.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.domain.model.LoginUser;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.domain.PlayRecord;
import com.guoguoyun.system.domain.vo.AppInfoVo;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.app.UserPasswordParams;
import com.guoguoyun.web.controller.app.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/app/userMessage")
public class UserMessageController extends BaseController {

    @Autowired
    private TokenService tokenService;


    @Autowired
    private UserMessageService userMessageService;


    /**
     * 修改密码
     * @return
     */
    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(@RequestBody @Validated(BaseParam.edit.class)UserPasswordParams userPasswordParams){
        LoginUser loginUser = tokenService.getLoginUser();
        userPasswordParams.setUserId(loginUser.getUserId());
        userMessageService.updatePassword(userPasswordParams);
        return AjaxResult.success();
    }


    /**
     * 隐私政策或联系我们
     * @param type
     * @return
     */
    @GetMapping("/getRoleInfo/{type}")
    public AjaxResult getRoleInfo(@PathVariable("type") Integer type){
        return AjaxResult.success(userMessageService.getRoleInfo(type));
    }

    @GetMapping("/myInfo")
    public AjaxResult myInfo(){
        return AjaxResult.success();
    }


    /**
     * 个人信息
     * @return
     */
    @GetMapping("/userMessageInfo")
    public AjaxResult userMessageInfo(){
        return AjaxResult.success(userMessageService.userMessageInfo(tokenService.getLoginUser().getUserId()));
    }

    /**
     * 修改个人信息
     */
    @PostMapping("/editUserMessage")
    public AjaxResult editUserMessage(AppUser appUser){
        userMessageService.editUserMessage(appUser);
        return AjaxResult.success();
    }

    /**
     * 通知列表
     * @return
     */
    @GetMapping("/informList")
    public AjaxResult informList(){
        Long userId = tokenService.getLoginUser().getUserId();
        List<AppInfoVo> appInfoVos = userMessageService.informList(userId);
        return AjaxResult.success(appInfoVos);
    }


    /**
     * 最近播放
     * @return
     */
    @GetMapping("/userPlayLog/{type}")
    public TableDataInfo userPlayLog(@PathVariable Long type){
        Long userId = tokenService.getLoginUser().getUserId();
        startPage();
        List<PlayRecord> playRecords = userMessageService.userPlayLog(userId, type);
        return getDataTable(playRecords);
    }

    /**
     * 用户订单记录
     * @param type 1为专辑  2为单曲
     * @return
     */
    @GetMapping("/userOrderList/{type}")
    public TableDataInfo userOrderList(@PathVariable Long type,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        Long userId = tokenService.getLoginUser().getUserId();
        Page<OperationOrder> operationOrderPage = userMessageService.userOrderList(userId, type, pageNum, pageSize);
        return getDataTable(operationOrderPage.getRecords(),operationOrderPage.getTotal());
    }
}
