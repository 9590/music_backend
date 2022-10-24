package com.guoguoyun.web.controller.app.controller;

import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.domain.UserComment;
import com.guoguoyun.system.service.IUserCommentService;
import com.guoguoyun.web.controller.app.service.AppUserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/UserComment")
public class UserCommentController extends BaseController {

    @Autowired
    private AppUserCommentService appUserCommentService;

    @Autowired
    private TokenService tokenService;

    /**
     * 评论
     * @return
     */
    @PostMapping("/addComment")
    public AjaxResult addComment(@RequestBody UserComment userComment){
        Long userId = tokenService.getLoginUser().getUserId();
        userComment.setUserId(userId);
        appUserCommentService.addComment(userComment);
        return AjaxResult.success();
    }
}
