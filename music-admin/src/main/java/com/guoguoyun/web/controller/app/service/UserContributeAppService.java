package com.guoguoyun.web.controller.app.service;

import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.ContentDynamicstate;
import com.guoguoyun.system.domain.UserContribute;
import com.guoguoyun.system.params.app.ArtistContributeAppParams;
import com.guoguoyun.system.params.app.UserContributeAppLikeParams;
import com.guoguoyun.system.params.app.UserContributeAppParams;

import java.util.List;
import java.util.Map;

public interface UserContributeAppService {


    /**
     * 专辑列表
     * @return
     */
    public List<Album> searchAlbum();

    /**
     * 用户投稿
     * @param userContributeAppParams
     */
    public void insertContribute(UserContributeAppParams userContributeAppParams);


    /**
     * 用户投稿列表
     * @param userId
     * @return
     */
    public List<UserContribute> userContributeList(Long userId);

    /**
     * 文章点赞
     */
    public Map<String, Object> likeArticle(UserContributeAppLikeParams userContributeAppLikeParams);

    /**
     * 点赞
     */
    public Map<String, Object> likeContribute(UserContributeAppLikeParams userContributeAppLikeParams);

    /**
     * 动态点赞
     * @param userContributeAppLikeParams
     */
    public Map<String, Object> likeDynamicState(UserContributeAppLikeParams userContributeAppLikeParams);


    /**
     * 艺人发布动态
     * @param artistContributeAppParams
     */
    public void issueDynamicState(ArtistContributeAppParams artistContributeAppParams);

    /**
     * 乐迷投稿列表
     * @return
     */
    public List<UserContribute> contributeList(Long userId);

    /**
     * 投稿或动态详情
     * @param id
     * @param type
     * @return
     */
    public AjaxResult forumDetail(Long id , Integer type,Long userId);

    /**
     * 艺人列表
     * @return
     */
    public List<AppUser> artistList();

    /**
     * 艺人详情
     * @param id
     * @return
     */
    public AppUser artistDetail(Long id,Long userId);

    /**
     * 艺人动态
     * @param id
     * @param userId
     * @return
     */
    public List<ContentDynamicstate> artistDynamicstate(Long id,Long userId);

    /**
     * 艺人专辑
     * @return
     */
    public List<Album> artistAlbum(Long userId);

}
