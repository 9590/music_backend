package com.guoguoyun.web.controller.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import com.guoguoyun.system.mapper.ContentArticleMapper;
import com.guoguoyun.system.params.ContentArticleParams;
import com.guoguoyun.system.response.app.AppUserFollowListResponse;
import com.guoguoyun.system.service.*;
import com.guoguoyun.web.controller.app.service.AppArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppArticleServiceImpl implements AppArticleService {

    @Autowired
    private IContentArticleService contentArticleService;

    @Autowired
    private ContentArticleMapper contentArticleMapper;

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private IUserContributeRelationService userContributeRelationService;

    @Autowired
    private IUserCollectAlbumOrArticleService collectAlbumOrArticleService;

    @Autowired
    private IUserFollowService userFollowService;

    @Autowired
    private IUserContributeService userContributeService;

    @Autowired
    private IContentDynamicstateService contentDynamicstateService;

    @Autowired
    private IUserCommentService userCommentService;


    @Autowired
    private IAppUserService userService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IUserCollectAlbumOrArticleService userCollectAlbumOrArticleService;

    @Autowired
    private IUserContributeAlbumService userContributeAlbumService;

    /**
     * 首页文章
     * @return
     */
    @Override
    public List<ContentArticle> articleIndex(ContentArticle contentArticle) {
//        QueryWrapper<ContentArticle> contentArticleQueryWrapper = new QueryWrapper<>();
//        if (ObjectUtil.isNotEmpty(contentArticle.getArticleType())){
//            contentArticleQueryWrapper.lambda().eq(ContentArticle::getArticleType,contentArticle.getArticleType());
//        }
//        if (ObjectUtil.isNotEmpty(contentArticle.getArticleTag())){
//            contentArticleQueryWrapper.lambda().like(ContentArticle::getArticleTag,contentArticle.getArticleTag());
//        }
//        contentArticleQueryWrapper.lambda().orderByDesc(ContentArticle::getCreateTime);
//        List<ContentArticle> list = contentArticleService.list(contentArticleQueryWrapper);
        List<ContentArticle> articleList = contentArticleMapper.selectContentArticleList(contentArticle);
        articleList = articleList.stream().sorted(Comparator.comparing(ContentArticle::getCreateTime,Comparator.reverseOrder())).collect(Collectors.toList());
        return articleList;
    }

    @Override
    public Page<ContentArticle> articleIndexNew(Integer pageNum,Integer pageSize) {
        QueryWrapper<ContentArticle> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(ContentArticle::getCreateTime);
        Page<ContentArticle> page = new Page<>(pageNum, pageSize);
        Page<ContentArticle> articlePage = contentArticleService.page(page,wrapper);


        return articlePage;
    }

    /**
     * 文章分类
     * @return
     */
    @Override
    public List<Classify> articleClassifyS() {
        QueryWrapper<Classify> classifyQueryWrapper = new QueryWrapper<>();
        classifyQueryWrapper.lambda().eq(Classify::getClassifyType,3);
        classifyQueryWrapper.lambda().orderByDesc(Classify::getCreateTime);
        List<Classify> classifyList = classifyService.list(classifyQueryWrapper);
        return classifyList;
    }

    /**
     * 文章详情
     * @param contentArticleParams
     * @return
     */
    @Override
    public ContentArticle articleDetail(ContentArticleParams contentArticleParams) {
        ContentArticle contentArticle = contentArticleService.getById(contentArticleParams.getId());
        contentArticle.setReadNum(contentArticle.getReadNum()+1);
        contentArticleService.updateContentArticle(contentArticle);

        /* 点赞数 */
        QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
        userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType,3);
        userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState,2);
        userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId,contentArticleParams.getId());
        List<UserContributeRelation> userContributeRelationList = userContributeRelationService.list(userContributeRelationQueryWrapper);
        contentArticle.setLikeNum(Long.valueOf(userContributeRelationList.size()));

        /* 收藏数 */
        QueryWrapper<UserCollectAlbumOrArticle> userCollectAlbumOrArticleQueryWrapper = new QueryWrapper<>();
        userCollectAlbumOrArticleQueryWrapper.lambda().eq(UserCollectAlbumOrArticle::getType,2);
        userCollectAlbumOrArticleQueryWrapper.lambda().eq(UserCollectAlbumOrArticle::getPassivityId,contentArticleParams.getId());
        List<UserCollectAlbumOrArticle> albumOrArticleList = collectAlbumOrArticleService.list(userCollectAlbumOrArticleQueryWrapper);
        contentArticle.setCollectCount(albumOrArticleList.size());



        if (ObjectUtil.isNotEmpty(contentArticleParams.getUserId())){
            userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId,contentArticleParams.getUserId());
            UserContributeRelation contributeRelation = userContributeRelationService.getOne(userContributeRelationQueryWrapper);
            if (ObjectUtil.isNotEmpty(contributeRelation)){
                contentArticle.setLikeStatus(1);
            }else {
                contentArticle.setLikeStatus(2);
            }

            userCollectAlbumOrArticleQueryWrapper.lambda().eq(UserCollectAlbumOrArticle::getUserId,contentArticleParams.getUserId());
            UserCollectAlbumOrArticle orArticle = collectAlbumOrArticleService.getOne(userCollectAlbumOrArticleQueryWrapper);
            if (ObjectUtil.isNotEmpty(orArticle)){
                contentArticle.setCollectStatus(1);
            }else {
                contentArticle.setCollectStatus(2);
            }

        }else {
            contentArticle.setCollectStatus(2);
            contentArticle.setLikeStatus(2);
        }

        if (ObjectUtil.isNotEmpty(contentArticle.getArticleTag())){
            List<String> strings = JSONArray.parseArray(contentArticle.getArticleTag(), String.class);
            contentArticle.setTagS(strings);
        }

        Classify classify = classifyService.getById(contentArticle.getArticleType());
        if (ObjectUtil.isNotEmpty(classify)){
            contentArticle.setArticleTypeName(classify.getClassifyName());
        }

        return contentArticle;
    }

    @Override
    public List<AppUserFollowListResponse> myFollowArticleList(Long userId) {
        List<AppUserFollowListResponse> arrayList = new ArrayList<>();
        QueryWrapper<UserFollow> userFollowQueryWrapper = new QueryWrapper<>();
        userFollowQueryWrapper.lambda().eq(UserFollow::getUserId,userId);
        userFollowQueryWrapper.lambda().orderByDesc(UserFollow::getCreateTime);
        List<UserFollow> userFollowList = userFollowService.list(userFollowQueryWrapper);
        Map<Integer, List<UserFollow>> listMap = userFollowList.stream().collect(Collectors.groupingBy(t -> t.getUserType()));
        listMap.keySet().forEach(t -> {
            List<Long> collect = listMap.get(t).stream().map(x -> x.getFromId()).collect(Collectors.toList());
            if (t == MusicCode.USER_TYPE_ORDINARY){
                QueryWrapper<UserContribute> userContributeQueryWrapper = new QueryWrapper<>();
                userContributeQueryWrapper.lambda().in(UserContribute::getUserId,collect);
                userContributeQueryWrapper.lambda().orderByDesc(UserContribute::getCreateTime);
                List<UserContribute> list = userContributeService.list(userContributeQueryWrapper);
                List<AppUserFollowListResponse> appUserFollowListResponses = list.stream().map(x -> {
                    AppUserFollowListResponse appUserFollowListResponse = BeanUtil.toBean(x, AppUserFollowListResponse.class);
                    appUserFollowListResponse.setType(1);
                    QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
                    userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, appUserFollowListResponse.getId());
                    userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
                    userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 1);
                    appUserFollowListResponse.setLikeNum(userContributeRelationService.list(userContributeRelationQueryWrapper).size());

                    AppUser user = userService.getById(appUserFollowListResponse.getUserId());
                    if (ObjectUtil.isNotEmpty(userId)) {
                        //用户点赞状态
                        userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userId);
                        List<UserContributeRelation> relationList = userContributeRelationService.list(userContributeRelationQueryWrapper);
                        if (relationList.size() != 0) {
                            appUserFollowListResponse.setLikeStatus(1);
                        } else {
                            appUserFollowListResponse.setLikeStatus(2);
                        }
                        /* 收藏状态 */
                        QueryWrapper<UserCollectAlbumOrArticle> wrapper1 = new QueryWrapper<>();
                        wrapper1.lambda().eq(UserCollectAlbumOrArticle::getUserId, userId);
                        wrapper1.lambda().eq(UserCollectAlbumOrArticle::getPassivityId, appUserFollowListResponse.getId());
                        wrapper1.lambda().eq(UserCollectAlbumOrArticle::getType, 4);
                        List<UserCollectAlbumOrArticle> list1 = userCollectAlbumOrArticleService.list(wrapper1);
                        if (list1.size() != 0) {
                            appUserFollowListResponse.setCollectStatus(1);
                        } else {
                            appUserFollowListResponse.setCollectStatus(2);
                        }

                        //关注状态
                        QueryWrapper<UserFollow> userFollowQueryWrapper1 = new QueryWrapper<>();
                        userFollowQueryWrapper.lambda().eq(UserFollow::getUserId, userId);
                        userFollowQueryWrapper.lambda().eq(UserFollow::getFromId, appUserFollowListResponse.getUserId());
                        List<UserFollow> userFollowS = userFollowService.list(userFollowQueryWrapper1);
                        if (userFollowS.size() != 0) {
                            user.setFollowStatus(1);
                        } else {
                            user.setFollowStatus(2);
                        }
                    } else {
                        user.setFollowStatus(2);
                        appUserFollowListResponse.setLikeStatus(2);
                        appUserFollowListResponse.setCollectStatus(2);
                    }

                    //评论数
                    QueryWrapper<UserComment> userCommentQueryWrapper = new QueryWrapper<>();
                    userCommentQueryWrapper.lambda().eq(UserComment::getType, MusicCode.DETAIL_TYPE_CONTRIBUTE);
                    List<UserComment> commentList = userCommentService.list(userCommentQueryWrapper);
                    appUserFollowListResponse.setCommentCount(commentList.size());
                    //转换图片
                    if (ObjectUtil.isNotEmpty(appUserFollowListResponse.getImgUrls())) {
                        List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(appUserFollowListResponse.getImgUrls(), ImgUrlVo.class);
                        appUserFollowListResponse.setImgUrlList(imgUrlVos);
                    }
                    //专辑
                    QueryWrapper<UserContributeAlbum> contributeAlbumQueryWrapper = new QueryWrapper<>();
                    contributeAlbumQueryWrapper.lambda().eq(UserContributeAlbum::getContributeId, appUserFollowListResponse.getId());
                    List<Long> albumIds = userContributeAlbumService.list(contributeAlbumQueryWrapper).stream().map(f -> f.getAlbumId()).collect(Collectors.toList());
                    if (albumIds.size() != 0) {
                        QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
                        albumQueryWrapper.lambda().in(Album::getId, albumIds);
                        albumQueryWrapper.lambda().eq(Album::getDeleteType, 0);
                        List<Album> albumList = albumService.list(albumQueryWrapper);
                        appUserFollowListResponse.setAlbumList(albumList);
                    }


                    appUserFollowListResponse.setUser(user);
                    return appUserFollowListResponse;
                }).collect(Collectors.toList());
                arrayList.addAll(appUserFollowListResponses);
            }else {
                QueryWrapper<ContentDynamicstate> contentDynamicstateQueryWrapper = new QueryWrapper<>();
                contentDynamicstateQueryWrapper.lambda().in(ContentDynamicstate::getUserId,collect);
                contentDynamicstateQueryWrapper.lambda().orderByDesc(ContentDynamicstate::getCreateTime);
                List<ContentDynamicstate> dynamicstateList = contentDynamicstateService.list(contentDynamicstateQueryWrapper);
                List<AppUserFollowListResponse> appUserFollowListResponses = dynamicstateList.stream().map(x -> {
                    AppUserFollowListResponse appUserFollowListResponse = BeanUtil.toBean(x, AppUserFollowListResponse.class);
                    appUserFollowListResponse.setType(2);
                    /* 点赞数 */
                    QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
                    userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId, appUserFollowListResponse.getId());
                    userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getState, MusicCode.USER_CONTRIBUTE_RELATION_LIKE);
                    userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, 2);
                    appUserFollowListResponse.setLikeNum(userContributeRelationService.list(userContributeRelationQueryWrapper).size());

                    AppUser user = userService.getById(appUserFollowListResponse.getUserId());
                    /*点赞状态*/
                    if (ObjectUtil.isNotEmpty(userId)) {
                        userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getUserId, userId);
                        List<UserContributeRelation> relationList = userContributeRelationService.list(userContributeRelationQueryWrapper);
                        if (relationList.size() != 0) {
                            appUserFollowListResponse.setLikeStatus(1);
                        } else {
                            appUserFollowListResponse.setLikeStatus(2);
                        }

                        //关注状态
                        QueryWrapper<UserFollow> userFollowQueryWrapper1 = new QueryWrapper<>();
                        userFollowQueryWrapper.lambda().eq(UserFollow::getUserId, userId);
                        userFollowQueryWrapper.lambda().eq(UserFollow::getFromId, appUserFollowListResponse.getUserId());
                        List<UserFollow> userFollowS = userFollowService.list(userFollowQueryWrapper1);
                        if (userFollowS.size() != 0) {
                            user.setFollowStatus(1);
                        } else {
                            user.setFollowStatus(2);
                        }

                        /* 收藏状态 */
                        QueryWrapper<UserCollectAlbumOrArticle> wrapper1 = new QueryWrapper<>();
                        wrapper1.lambda().eq(UserCollectAlbumOrArticle::getUserId, userId);
                        wrapper1.lambda().eq(UserCollectAlbumOrArticle::getPassivityId, appUserFollowListResponse.getId());
                        wrapper1.lambda().eq(UserCollectAlbumOrArticle::getType, 5);
                        List<UserCollectAlbumOrArticle> list1 = userCollectAlbumOrArticleService.list(wrapper1);
                        if (list1.size() != 0) {
                            appUserFollowListResponse.setCollectStatus(1);
                        } else {
                            appUserFollowListResponse.setCollectStatus(2);
                        }
                    } else {
                        appUserFollowListResponse.setLikeStatus(2);
                        appUserFollowListResponse.setCollectStatus(2);
                        user.setFollowStatus(2);
                    }

                    /* 转换图片和视频 */
                    List<ImgUrlVo> list = new ArrayList<>();
                    List<ImgUrlVo> videoUrl = new ArrayList<>();
                    if (ObjectUtil.isNotEmpty(appUserFollowListResponse.getImgUrls())) {
                        List<ImgUrlVo> imgUrlVos = JSONArray.parseArray(appUserFollowListResponse.getImgUrls(), ImgUrlVo.class);
                        list.addAll(imgUrlVos);
                    }
                    if (ObjectUtil.isNotEmpty(appUserFollowListResponse.getVideoUrl())) {
                        List<ImgUrlVo> videoUrls = JSONArray.parseArray(appUserFollowListResponse.getVideoUrl(), ImgUrlVo.class);
                        videoUrl.addAll(videoUrls);
                    }
                    appUserFollowListResponse.setImgUrlList(list);
                    appUserFollowListResponse.setVideoUrlList(videoUrl);



                    /* 评论数 */
                    QueryWrapper<UserComment> userCommentQueryWrapper = new QueryWrapper<>();
                    userCommentQueryWrapper.lambda().eq(UserComment::getType, MusicCode.DETAIL_TYPE_DYNAMICSTATE);
                    List<UserComment> commentList = userCommentService.list(userCommentQueryWrapper);
                    appUserFollowListResponse.setCommentCount(commentList.size());
                    appUserFollowListResponse.setUser(user);

                    return appUserFollowListResponse;
                }).collect(Collectors.toList());
                arrayList.addAll(appUserFollowListResponses);
            }
        });
        List<AppUserFollowListResponse> responses = arrayList.stream().sorted(Comparator.comparing(AppUserFollowListResponse::getCreateTime, Comparator.reverseOrder())).collect(Collectors.toList());
        return responses;
    }
}
