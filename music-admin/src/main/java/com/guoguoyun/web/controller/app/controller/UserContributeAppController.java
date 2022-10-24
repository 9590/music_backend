package com.guoguoyun.web.controller.app.controller;

import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.domain.entity.SysUser;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.common.utils.PageUtils;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.ContentDynamicstate;
import com.guoguoyun.system.domain.UserContribute;
import com.guoguoyun.system.params.app.ArtistContributeAppParams;
import com.guoguoyun.system.params.app.UserContributeAppLikeParams;
import com.guoguoyun.system.params.app.UserContributeAppParams;
import com.guoguoyun.system.params.app.UserFollowParams;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.system.service.ISysUserService;
import com.guoguoyun.web.controller.app.service.UserContributeAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/app/userContribute")
public class UserContributeAppController extends BaseController {


    @Autowired
    private UserContributeAppService userContributeAppService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IAppUserService userService;


    /**
     * 专辑列表
     * @return
     */
    @GetMapping("/searchAlbum")
    public AjaxResult searchAlbum(){
        List<Album> albums = userContributeAppService.searchAlbum();
        return AjaxResult.success(albums);
    }

    /**
     * 用户发布投稿
     * @param userContributeAppParams
     * @return
     */
    @PostMapping("/insertContribute")
    public AjaxResult insertContribute(@RequestBody UserContributeAppParams userContributeAppParams){
        Long userId = tokenService.getLoginUser().getUserId();
        userContributeAppParams.setUserId(userId);
        userContributeAppService.insertContribute(userContributeAppParams);
        return AjaxResult.success();
    }


    /**
     * 用户投稿列表
     * @return
     */
    @GetMapping("/userContributeList")
    public AjaxResult userContributeList(){
        Long userId = tokenService.getLoginUser().getUserId();
        List<UserContribute> userContributes = userContributeAppService.userContributeList(userId);
        return AjaxResult.success(userContributes);
    }


    /**
     * 文章点赞
     * @return
     */
    @PostMapping("/articleLike")
    public AjaxResult articleLike(@RequestBody UserContributeAppLikeParams userContributeAppLikeParams){
        Long userId = tokenService.getLoginUser().getUserId();
        userContributeAppLikeParams.setUserId(userId);
        Map<String, Object> stringObjectMap = userContributeAppService.likeArticle(userContributeAppLikeParams);
        return AjaxResult.success(stringObjectMap);
    }

    /**
     * 投稿点赞
     */
    @PostMapping("/likeContribute")
    public AjaxResult likeContribute(@RequestBody UserContributeAppLikeParams userContributeAppLikeParams){
        Long userId = tokenService.getLoginUser().getUserId();
        userContributeAppLikeParams.setUserId(userId);
        Map<String, Object> stringObjectMap = userContributeAppService.likeContribute(userContributeAppLikeParams);
        return AjaxResult.success(stringObjectMap);
    }

    /**
     * 动态点赞
     */
    @PostMapping("/likeDynamicState")
    public AjaxResult likeDynamicState(@RequestBody UserContributeAppLikeParams userContributeAppLikeParams){
        Long userId = tokenService.getLoginUser().getUserId();
        userContributeAppLikeParams.setUserId(userId);
        Map<String, Object> stringObjectMap = userContributeAppService.likeDynamicState(userContributeAppLikeParams);
        return AjaxResult.success(stringObjectMap);
    }


    /**
     * 艺人发布动态
     */
    @PostMapping("/issueDynamicState")
    public AjaxResult issueDynamicState(@RequestBody ArtistContributeAppParams artistContributeAppParams){
        Long userId = tokenService.getLoginUser().getUserId();
        AppUser byId = userService.getById(userId);
        if (byId.getUserType() == MusicCode.USER_TYPE_ORDINARY){
            throw new SecurityException("该账号不是艺人");
        }
        artistContributeAppParams.setUserId(userId);
        userContributeAppService.issueDynamicState(artistContributeAppParams);
        return AjaxResult.success();
    }


    /**
     * 乐迷投稿列表
     */
    @GetMapping("/contributeList")
    public TableDataInfo contributeList(Long userId){
        startPage();
        List<UserContribute> userContributes = userContributeAppService.contributeList(userId);
        return getDataTable(userContributes);
    }


    /**
     * 投稿详情
     * @param id
     * @return
     */
    @GetMapping("/contributeDetail/{id}")
    public AjaxResult contributeDetail(@PathVariable Long id){
        return userContributeAppService.forumDetail(id,MusicCode.DETAIL_TYPE_CONTRIBUTE,tokenService.getLoginUser().getUserId());
    }

    /**
     * 动态详情
     * @param id
     * @return
     */
    @GetMapping("/dynamicstateDetail/{id}")
    public AjaxResult dynamicstateDetail(@PathVariable Long id){
        return userContributeAppService.forumDetail(id,MusicCode.DETAIL_TYPE_DYNAMICSTATE,tokenService.getLoginUser().getUserId());
    }

    /**
     * 艺人列表
     * @return
     */
    @GetMapping("/artistList")
    public TableDataInfo artistList(){
        startPage();
        List<AppUser> appUsers = userContributeAppService.artistList();
        return getDataTable(appUsers);
    }

    /**
     * 艺人详情
     * @return
     */
    @GetMapping("/artistDetail")
    public AjaxResult artistDetail(UserFollowParams userFollowParams){
        AppUser appUser = userContributeAppService.artistDetail(userFollowParams.getId(), userFollowParams.getUserId());
        return AjaxResult.success(appUser);
    }

    /**
     * 艺人动态
     */
    @GetMapping("/artistDynamicstate")
    public TableDataInfo artistDynamicstate(UserFollowParams userFollowParams){
        startPage();
        List<ContentDynamicstate> contentDynamicstates = userContributeAppService.artistDynamicstate(userFollowParams.getId(), userFollowParams.getUserId());
        return getDataTable(contentDynamicstates);
    }

    /**
     * 艺人专辑
     * @return
     */
    @GetMapping("/artistAlbum")
    public TableDataInfo artistAlbum(UserFollowParams userFollowParams){
        startPage();
        List<Album> albumList = userContributeAppService.artistAlbum(userFollowParams.getUserId());
        return getDataTable(albumList);
    }
}
