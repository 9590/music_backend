package com.guoguoyun.web.controller.app.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.SerializeUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.enums.CheckState;
import com.guoguoyun.common.utils.PageUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import com.guoguoyun.system.params.app.ArtistContributeAppParams;
import com.guoguoyun.system.params.app.UserContributeAppLikeParams;
import com.guoguoyun.system.params.app.UserContributeAppParams;
import com.guoguoyun.system.service.*;
import com.guoguoyun.web.controller.app.service.AppUserCommentService;
import com.guoguoyun.web.controller.app.service.UserContributeAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserContributeAppServiceImpl implements UserContributeAppService {


    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IUserContributeService userContributeService;

    @Autowired
    private IUserContributeAlbumService userContributeAlbumService;

    @Autowired
    private IUserContributeRelationService userContributeRelationService;

    @Autowired
    private IUserLikeFollowInformService userLikeFollowInformService;

    @Autowired
    private IContentDynamicstateService contentDynamicstateService;

    @Autowired
    private AppUserCommentService appUserCommentService;

    @Autowired
    private IUserCommentService userCommentService;

    @Autowired
    private IUserFollowService userFollowService;

    @Autowired
    private IAppUserService userService;

    @Autowired
    private IAlbumUserService albumUserService;

    @Autowired
    private IUserCollectAlbumOrArticleService userCollectAlbumOrArticleService;


    /**
     * 专辑列表
     *
     * @return
     */
    @Override
    public List<Album> searchAlbum() {
        QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
        albumQueryWrapper.lambda().orderByDesc(Album::getIssueTime);
        List<Album> albums = albumService.list(albumQueryWrapper);
        return albums;
    }

    @Override
    public void insertContribute(UserContributeAppParams userContributeAppParams) {
        UserContribute userContribute = BeanUtil.toBean(userContributeAppParams, UserContribute.class);
        userContribute.setCreateTime(new Date());
        if (ObjectUtil.isNotEmpty(userContributeAppParams.getImgUrls())) {
            String string = JSONArray.toJSON(userContributeAppParams.getImgUrls()).toString();
            userContribute.setImgUrls(string);
        }
        userContributeService.insertUserContribute(userContribute);
        if (ObjectUtil.isNotEmpty(userContributeAppParams.getAlbumIds())) {
            userContributeAppParams.getAlbumIds().forEach(t -> {
                userContributeAlbumService.insertUserContributeAlbum(userContribute.getId(), t);
            });
        }


    }

    /**
     * 用户投稿列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserContribute> userContributeList(Long userId) {
        QueryWrapper<UserContribute> userContributeQueryWrapper = new QueryWrapper<>();
        userContributeQueryWrapper.lambda().eq(UserContribute::getUserId, userId);
        userContributeQueryWrapper.lambda().orderByDesc(UserContribute::getCreateTime);
        List<UserContribute> userContributes = userContributeService.list(userContributeQueryWrapper);
        return userContributes;
    }

    @Override
    public Map<String, Object> likeArticle(UserContributeAppLikeParams userContributeAppLikeParams) {
        Map<String, Object> map = new HashMap<>();
        if (userContributeAppLikeParams.getType() == MusicCode.LIKE_YES_STATE) {
            UserContributeRelation userContributeRelation = new UserContributeRelation();
            userContributeRelation.setType(3);
            userContributeRelation.setState(MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelation.setContributeId(userContributeAppLikeParams.getContributeId());
            userContributeRelation.setUserId(userContributeAppLikeParams.getUserId());
            userContributeRelationService.save(userContributeRelation);

            map.put("likeStatus", "1");
        } else {
            QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, userContributeAppLikeParams.getContributeId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userContributeAppLikeParams.getUserId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 3);
            userContributeRelationService.remove(userContributeRelationQueryWrapper);
            map.put("likeStatus", "2");
        }

        QueryWrapper<UserContributeRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserContributeRelation::getContributeId, userContributeAppLikeParams.getContributeId());
        wrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
        wrapper.lambda().eq(UserContributeRelation::getType, 3);
        List<UserContributeRelation> list = userContributeRelationService.list(wrapper);
        map.put("likeNum", list.size());
        return map;
    }


    /**
     * 点赞投稿
     */
    @Override
    public Map<String, Object> likeContribute(UserContributeAppLikeParams userContributeAppLikeParams) {
        Map<String, Object> map = new HashMap<>();
        if (userContributeAppLikeParams.getType() == MusicCode.LIKE_YES_STATE) {
            UserContributeRelation userContributeRelation = new UserContributeRelation();
            userContributeRelation.setType(1);
            userContributeRelation.setState(MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelation.setContributeId(userContributeAppLikeParams.getContributeId());
            userContributeRelation.setUserId(userContributeAppLikeParams.getUserId());
            userContributeRelationService.save(userContributeRelation);

            UserContribute userContributeServiceById = userContributeService.getById(userContributeAppLikeParams.getContributeId());
            UserLikeFollowInform userLikeFollowInform = new UserLikeFollowInform();
            userLikeFollowInform.setFromId(userContributeServiceById.getUserId());
            userLikeFollowInform.setUserId(userContributeAppLikeParams.getUserId());
            userLikeFollowInform.setType(1);
            userLikeFollowInform.setState(1);
            userLikeFollowInformService.insertUserLikeFollowInform(userLikeFollowInform);
            map.put("likeStatus", "1");
        } else {
            QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, userContributeAppLikeParams.getContributeId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userContributeAppLikeParams.getUserId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 1);
            userContributeRelationService.remove(userContributeRelationQueryWrapper);
            map.put("likeStatus", "2");
        }

        QueryWrapper<UserContributeRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserContributeRelation::getContributeId, userContributeAppLikeParams.getContributeId());
        wrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
        wrapper.lambda().eq(UserContributeRelation::getType, 1);
        List<UserContributeRelation> list = userContributeRelationService.list(wrapper);
        map.put("likeNum", list.size());
        return map;
    }

    /**
     * 动态点赞
     *
     * @param userContributeAppLikeParams
     */
    @Override
    public Map<String, Object> likeDynamicState(UserContributeAppLikeParams userContributeAppLikeParams) {
        Map<String, Object> map = new HashMap<>();
        if (userContributeAppLikeParams.getType() == MusicCode.LIKE_YES_STATE) {
            UserContributeRelation userContributeRelation = new UserContributeRelation();
            userContributeRelation.setType(2);
            userContributeRelation.setState(MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelation.setContributeId(userContributeAppLikeParams.getContributeId());
            userContributeRelation.setUserId(userContributeAppLikeParams.getUserId());
            userContributeRelationService.save(userContributeRelation);

            ContentDynamicstate dynamicstateServiceById = contentDynamicstateService.getById(userContributeAppLikeParams.getContributeId());
            UserLikeFollowInform userLikeFollowInform = new UserLikeFollowInform();
            userLikeFollowInform.setFromId(dynamicstateServiceById.getUserId());
            userLikeFollowInform.setUserId(userContributeAppLikeParams.getUserId());
            userLikeFollowInform.setType(1);
            userLikeFollowInform.setState(2);
            userLikeFollowInformService.insertUserLikeFollowInform(userLikeFollowInform);
            map.put("likeStatus", "1");
        } else {
            QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, userContributeAppLikeParams.getContributeId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userContributeAppLikeParams.getUserId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelationService.remove(userContributeRelationQueryWrapper);
            map.put("likeStatus", "2");
        }
        QueryWrapper<UserContributeRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserContributeRelation::getContributeId, userContributeAppLikeParams.getContributeId());
        wrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
        wrapper.lambda().eq(UserContributeRelation::getType, 2);
        List<UserContributeRelation> list = userContributeRelationService.list(wrapper);
        map.put("likeNum", list.size());
        return map;
    }

    @Override
    public void issueDynamicState(ArtistContributeAppParams artistContributeAppParams) {
        ContentDynamicstate contentDynamicstate = BeanUtil.toBean(artistContributeAppParams, ContentDynamicstate.class);
        contentDynamicstate.setIssueType(MusicCode.CONTENT_FRONT);
        if (ObjectUtil.isNotEmpty(artistContributeAppParams.getImgUrls())) {
            String string = JSONArray.toJSON(artistContributeAppParams.getImgUrls()).toString();
            contentDynamicstate.setImgUrls(string);
        }
        contentDynamicstateService.insertContentDynamicstate(contentDynamicstate);
    }

    @Override
    public List<UserContribute> contributeList(Long userId) {
        QueryWrapper<UserContribute> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(UserContribute::getCreateTime);
        wrapper.lambda().eq(UserContribute::getCheckState, CheckState.PASS.getCode());
        List<UserContribute> list = userContributeService.list(wrapper);

        list.forEach(t -> {
            //点赞数
            QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, t.getId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 1);
            t.setLikeNum(userContributeRelationService.list(userContributeRelationQueryWrapper).size());

            AppUser user = userService.getById(t.getUserId());
            if (ObjectUtil.isNotEmpty(userId)) {
                //用户点赞状态
                userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userId);
                List<UserContributeRelation> relationList = userContributeRelationService.list(userContributeRelationQueryWrapper);
                if (relationList.size() != 0) {
                    t.setLikeStatus(1);
                } else {
                    t.setLikeStatus(2);
                }
                /* 收藏状态 */
                QueryWrapper<UserCollectAlbumOrArticle> wrapper1 = new QueryWrapper<>();
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getUserId,userId);
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getPassivityId,t.getId());
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getType,4);
                List<UserCollectAlbumOrArticle> list1 = userCollectAlbumOrArticleService.list(wrapper1);
                if (list1.size() != 0){
                    t.setCollectStatus(1);
                }else {
                    t.setCollectStatus(2);
                }

                //关注状态
                QueryWrapper<UserFollow> userFollowQueryWrapper = new QueryWrapper<>();
                userFollowQueryWrapper.lambda().eq(UserFollow::getUserId, userId);
                userFollowQueryWrapper.lambda().eq(UserFollow::getFromId, t.getUserId());
                List<UserFollow> userFollowList = userFollowService.list(userFollowQueryWrapper);
                if (userFollowList.size() != 0) {
                    user.setFollowStatus(1);
                } else {
                    user.setFollowStatus(2);
                }
            } else {
                user.setFollowStatus(2);
                t.setLikeStatus(2);
                t.setCollectStatus(2);
            }

            //评论数
            QueryWrapper<UserComment> userCommentQueryWrapper = new QueryWrapper<>();
            userCommentQueryWrapper.lambda().eq(UserComment::getType, MusicCode.DETAIL_TYPE_CONTRIBUTE);
            List<UserComment> commentList = userCommentService.list(userCommentQueryWrapper);
            t.setCommentCount(commentList.size());
            //转换图片
            if (ObjectUtil.isNotEmpty(t.getImgUrls())) {
                List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(t.getImgUrls(), ImgUrlVo.class);
                t.setImgUrlList(imgUrlVos);
            }
            //专辑
            QueryWrapper<UserContributeAlbum> contributeAlbumQueryWrapper = new QueryWrapper<>();
            contributeAlbumQueryWrapper.lambda().eq(UserContributeAlbum::getContributeId, t.getId());
            List<Long> albumIds = userContributeAlbumService.list(contributeAlbumQueryWrapper).stream().map(x -> x.getAlbumId()).collect(Collectors.toList());
            if (albumIds.size() != 0){
                QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
                albumQueryWrapper.lambda().in(Album::getId, albumIds);
                albumQueryWrapper.lambda().eq(Album::getDeleteType, 0);
                List<Album> albumList = albumService.list(albumQueryWrapper);
                t.setAlbumList(albumList);
            }


            t.setUser(user);



        });


        return list;
    }

    /**
     * 动态或投稿详情
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public AjaxResult forumDetail(Long id, Integer type, Long userId) {
        List<Tree<String>> commentTree = appUserCommentService.commentTree(id, type);
        QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
        userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, id);
        userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
        if (type == MusicCode.DETAIL_TYPE_CONTRIBUTE) {
            UserContribute contribute = userContributeService.getById(id);
            contribute.setReadNum(contribute.getReadNum()+1);
            userContributeService.updateUserContribute(contribute);
            AppUser user = userService.getById(contribute.getUserId());
            /* 评论 */
            contribute.setCommentList(commentTree);
            /* 评论数 */
            QueryWrapper<UserComment> userCommentQueryWrapper = new QueryWrapper<>();
            userCommentQueryWrapper.lambda().eq(UserComment::getType, MusicCode.DETAIL_TYPE_CONTRIBUTE);
            List<UserComment> commentList = userCommentService.list(userCommentQueryWrapper);
            contribute.setCommentCount(commentList.size());

            /* 点赞数 */
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 1);
            contribute.setLikeNum(userContributeRelationService.list(userContributeRelationQueryWrapper).size());
            /* 点赞状态 */
            if (ObjectUtil.isNotEmpty(userId)){
                userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userId);
                List<UserContributeRelation> relationList = userContributeRelationService.list(userContributeRelationQueryWrapper);
                if (relationList.size() != 0) {
                    contribute.setLikeStatus(1);
                } else {
                    contribute.setLikeStatus(2);
                }

                QueryWrapper<UserFollow> userFollowQueryWrapper = new QueryWrapper<>();
                userFollowQueryWrapper.lambda().eq(UserFollow::getUserId, userId);
                userFollowQueryWrapper.lambda().eq(UserFollow::getFromId, contribute.getUserId());
                List<UserFollow> userFollowList = userFollowService.list(userFollowQueryWrapper);
                if (userFollowList.size() != 0) {
                    user.setFollowStatus(1);
                } else {
                    user.setFollowStatus(2);
                }

                /* 收藏状态 */
                QueryWrapper<UserCollectAlbumOrArticle> wrapper1 = new QueryWrapper<>();
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getUserId,userId);
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getPassivityId,contribute.getId());
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getType,4);
                List<UserCollectAlbumOrArticle> list1 = userCollectAlbumOrArticleService.list(wrapper1);
                if (list1.size() != 0){
                    contribute.setCollectStatus(1);
                }else {
                    contribute.setCollectStatus(2);
                }
            }else {
                user.setFollowStatus(2);
                contribute.setLikeStatus(2);
                contribute.setCollectStatus(2);
            }

            /* 专辑 */
            QueryWrapper<UserContributeAlbum> contributeAlbumQueryWrapper = new QueryWrapper<>();
            contributeAlbumQueryWrapper.lambda().eq(UserContributeAlbum::getContributeId, contribute.getId());
            List<Long> albumIds = userContributeAlbumService.list(contributeAlbumQueryWrapper).stream().map(x -> x.getAlbumId()).collect(Collectors.toList());
            QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
            albumQueryWrapper.lambda().in(Album::getId, albumIds);
            albumQueryWrapper.lambda().eq(Album::getDeleteType, 0);
            List<Album> albumList = albumService.list(albumQueryWrapper);
            contribute.setAlbumList(albumList);
            /* 关注状态 */


            /* 转换图片 */
            if (ObjectUtil.isNotEmpty(contribute.getImgUrls())) {
                List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(contribute.getImgUrls(), ImgUrlVo.class);
                contribute.setImgUrlList(imgUrlVos);
            }
            contribute.setUser(user);



            return AjaxResult.success(contribute);
        } else {
            ContentDynamicstate dynamicstate = contentDynamicstateService.getById(id);
            AppUser user = userService.getById(dynamicstate.getUserId());
            /* 评论树 */
            dynamicstate.setCommentList(commentTree);

            /* 点赞数 */
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 2);
            dynamicstate.setLikeNum(userContributeRelationService.list(userContributeRelationQueryWrapper).size());

            if (ObjectUtil.isNotEmpty(userId)){
                /* 点赞状态 */
                userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userId);
                List<UserContributeRelation> relationList = userContributeRelationService.list(userContributeRelationQueryWrapper);
                if (relationList.size() != 0) {
                    dynamicstate.setLikeStatus(1);
                } else {
                    dynamicstate.setLikeStatus(2);
                }


                /* 关注状态 */
                QueryWrapper<UserFollow> userFollowQueryWrapper = new QueryWrapper<>();
                userFollowQueryWrapper.lambda().eq(UserFollow::getUserId, userId);
                userFollowQueryWrapper.lambda().eq(UserFollow::getFromId, dynamicstate.getUserId());
                List<UserFollow> userFollowList = userFollowService.list(userFollowQueryWrapper);
                if (userFollowList.size() != 0) {
                    user.setFollowStatus(1);
                } else {
                    user.setFollowStatus(2);
                }

                /* 收藏状态 */
                QueryWrapper<UserCollectAlbumOrArticle> wrapper1 = new QueryWrapper<>();
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getUserId,userId);
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getPassivityId,dynamicstate.getId());
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getType,5);
                List<UserCollectAlbumOrArticle> list1 = userCollectAlbumOrArticleService.list(wrapper1);
                if (list1.size() != 0){
                    dynamicstate.setCollectStatus(1);
                }else {
                    dynamicstate.setCollectStatus(2);
                }
            }else {
                user.setFollowStatus(2);
                dynamicstate.setLikeStatus(2);
                dynamicstate.setCollectStatus(2);
            }




            /* 转换图片和视频 */
            List<ImgUrlVo> list = new ArrayList<>();
            List<ImgUrlVo> videoUrl = new ArrayList<>();
            if (ObjectUtil.isNotEmpty(dynamicstate.getImgUrls())) {
                List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(dynamicstate.getImgUrls(), ImgUrlVo.class);
                list.addAll(imgUrlVos);
            }
            if (ObjectUtil.isNotEmpty(dynamicstate.getVideoUrl())) {
                List<ImgUrlVo> videoUrls = JSONArray.parseArray(dynamicstate.getVideoUrl(), ImgUrlVo.class);
                videoUrl.addAll(videoUrls);
            }
            dynamicstate.setImgUrlList(list);
            dynamicstate.setVideoUrlList(videoUrl);
            dynamicstate.setUser(user);

            /* 评论数 */
            QueryWrapper<UserComment> userCommentQueryWrapper = new QueryWrapper<>();
            userCommentQueryWrapper.lambda().eq(UserComment::getType, MusicCode.DETAIL_TYPE_DYNAMICSTATE);
            List<UserComment> commentList = userCommentService.list(userCommentQueryWrapper);
            dynamicstate.setCommentCount(commentList.size());

            /* 收藏状态 */
            return AjaxResult.success(dynamicstate);
        }
    }


    /**
     * 艺人列表
     *
     * @return
     */
    @Override
    public List<AppUser> artistList() {

        QueryWrapper<AppUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.lambda().eq(AppUser::getUserType, MusicCode.USER_TYPE_ARTIST);
        userQueryWrapper.lambda().orderByAsc(AppUser::getUserName);
        List<AppUser> list = userService.list(userQueryWrapper);
        return list;
    }

    /**
     * 艺人详情
     *
     * @param id
     * @return
     */
    @Override
    public AppUser artistDetail(Long id, Long userId) {
        AppUser appUser = userService.getById(id);
        if (ObjectUtil.isNotEmpty(userId)) {
            QueryWrapper<UserFollow> userFollowQueryWrapper = new QueryWrapper<>();
            userFollowQueryWrapper.lambda().eq(UserFollow::getUserId, userId);
            userFollowQueryWrapper.lambda().eq(UserFollow::getFromId, appUser.getId());
            List<UserFollow> userFollowList = userFollowService.list(userFollowQueryWrapper);
            if (userFollowList.size() != 0) {
                appUser.setFollowStatus(1);
            } else {
                appUser.setFollowStatus(2);
            }
        } else {
            appUser.setFollowStatus(2);
        }
        return appUser;
    }

    /**
     * 艺人动态
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    public List<ContentDynamicstate> artistDynamicstate(Long id, Long userId) {
        QueryWrapper<ContentDynamicstate> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ContentDynamicstate::getUserId, id);
        List<ContentDynamicstate> dynamicstateList = contentDynamicstateService.list(wrapper);
        dynamicstateList.forEach(t -> {
            /* 点赞数 */
            QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, t.getId());
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 2);
            t.setLikeNum(userContributeRelationService.list(userContributeRelationQueryWrapper).size());

            /*点赞状态*/
            if (ObjectUtil.isNotEmpty(userId)) {
                userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userId);
                List<UserContributeRelation> relationList = userContributeRelationService.list(userContributeRelationQueryWrapper);
                if (relationList.size() != 0) {
                    t.setLikeStatus(1);
                } else {
                    t.setLikeStatus(2);
                }

                /* 收藏状态 */
                QueryWrapper<UserCollectAlbumOrArticle> wrapper1 = new QueryWrapper<>();
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getUserId,userId);
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getPassivityId,t.getId());
                wrapper1.lambda().eq(UserCollectAlbumOrArticle::getType,5);
                List<UserCollectAlbumOrArticle> list1 = userCollectAlbumOrArticleService.list(wrapper1);
                if (list1.size() != 0){
                    t.setCollectStatus(1);
                }else {
                    t.setCollectStatus(2);
                }
            } else {
                t.setLikeStatus(2);
                t.setCollectStatus(2);
            }

            /* 转换图片和视频 */
            List<ImgUrlVo> list = new ArrayList<>();
            List<ImgUrlVo> videoUrl = new ArrayList<>();
            if (ObjectUtil.isNotEmpty(t.getImgUrls())) {
                List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(t.getImgUrls(), ImgUrlVo.class);
                list.addAll(imgUrlVos);
            }
            if (ObjectUtil.isNotEmpty(t.getVideoUrl())) {
                List<ImgUrlVo> videoUrls = JSONArray.parseArray(t.getVideoUrl(), ImgUrlVo.class);
                videoUrl.addAll(videoUrls);
            }
            t.setImgUrlList(list);
            t.setVideoUrlList(videoUrl);



            /* 评论数 */
            QueryWrapper<UserComment> userCommentQueryWrapper = new QueryWrapper<>();
            userCommentQueryWrapper.lambda().eq(UserComment::getType, MusicCode.DETAIL_TYPE_DYNAMICSTATE);
            List<UserComment> commentList = userCommentService.list(userCommentQueryWrapper);
            t.setCommentCount(commentList.size());

            /* 收藏状态 */
        });
        return dynamicstateList;
    }

    /**
     * 艺人专辑
     *
     * @param userId
     * @return
     */
    @Override
    public List<Album> artistAlbum(Long userId) {
        QueryWrapper<AlbumUser> albumUserQueryWrapper = new QueryWrapper<>();
        albumUserQueryWrapper.lambda().eq(AlbumUser::getUserId, userId);
        albumUserQueryWrapper.lambda().orderByDesc(AlbumUser::getUpdateTime);
        List<AlbumUser> albumUserList = albumUserService.list(albumUserQueryWrapper);

        List<Long> longList = albumUserList.stream().map(t -> t.getAlbumId()).collect(Collectors.toList());

        QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
        albumQueryWrapper.lambda().in(Album::getId, longList);
        albumQueryWrapper.lambda().eq(Album::getDeleteType,0);
        List<Album> albumList = albumService.list(albumQueryWrapper);
        return albumList;
    }
}
