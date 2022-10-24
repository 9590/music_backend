package com.guoguoyun.web.controller.app.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.UserLikeFollowInform;
import com.guoguoyun.system.params.app.UserFollowParams;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.system.service.IUserLikeFollowInformService;
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
import com.guoguoyun.system.domain.UserFollow;
import com.guoguoyun.system.service.IUserFollowService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 用户关注Controller
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
@RestController
@RequestMapping("/app/follow")
public class UserFollowController extends BaseController
{
    @Autowired
    private IUserFollowService userFollowService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUserLikeFollowInformService userLikeFollowInformService;

    @Autowired
    private IAppUserService userService;

    /**
     * 关注/取消关注
     * @param userFollowParams
     * @return
     */
    @PostMapping("/userFollow")
    public AjaxResult userFollow(@RequestBody UserFollowParams userFollowParams){
        Long userId = tokenService.getLoginUser().getUserId();
        if (userFollowParams.getType() == MusicCode.FOLLOW_YES_STATE){

            QueryWrapper<UserFollow> userFollowQueryWrapper = new QueryWrapper<>();
            userFollowQueryWrapper.lambda().eq(UserFollow::getUserId,userId);
            userFollowQueryWrapper.lambda().eq(UserFollow::getFromId,userFollowParams.getFromId());
            List<UserFollow> userFollowList = userFollowService.list(userFollowQueryWrapper);
            if (userFollowList.size() != 0){
                throw new SecurityException("不可重复关注");
            }


            UserFollow userFollow = new UserFollow();
            userFollow.setFromId(userFollowParams.getFromId());
            userFollow.setUserId(userId);

            AppUser byId = userService.getById(userFollowParams.getFromId());
            userFollow.setUserType(byId.getUserType());

            userFollowService.insertUserFollow(userFollow);

            UserLikeFollowInform userLikeFollowInform = new UserLikeFollowInform();
            userLikeFollowInform.setFromId(userFollowParams.getFromId());
            userLikeFollowInform.setUserId(userId);
            userLikeFollowInform.setType(2);
            userLikeFollowInformService.insertUserLikeFollowInform(userLikeFollowInform);
            return AjaxResult.success(userFollowParams.getType());
        }else {
            QueryWrapper<UserFollow> userFollowQueryWrapper = new QueryWrapper<>();
            userFollowQueryWrapper.lambda().eq(UserFollow::getUserId,userId);
            userFollowQueryWrapper.lambda().eq(UserFollow::getFromId,userFollowParams.getFromId());
            userFollowService.remove(userFollowQueryWrapper);
            return AjaxResult.success(userFollowParams.getType());
        }

    }


}
