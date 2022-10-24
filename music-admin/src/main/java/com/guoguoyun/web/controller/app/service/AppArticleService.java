package com.guoguoyun.web.controller.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.domain.ContentArticle;
import com.guoguoyun.system.params.ContentArticleParams;
import com.guoguoyun.system.response.app.AppUserFollowListResponse;

import java.util.List;

public interface AppArticleService {

    /**
     * 首页文章
     * @return
     */
    public List<ContentArticle> articleIndex(ContentArticle contentArticle);
    public Page<ContentArticle> articleIndexNew(Integer pageNum, Integer pageSize);

    /**
     * 文章分类
     * @return
     */
    public List<Classify> articleClassifyS();

    /**
     * 文章详情
     * @return
     */
    public ContentArticle articleDetail(ContentArticleParams contentArticleParams);

    /**
     * 关注文章列表
     * @param userId
     */
    public List<AppUserFollowListResponse> myFollowArticleList(Long userId);
}
