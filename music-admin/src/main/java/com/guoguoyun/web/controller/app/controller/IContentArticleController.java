package com.guoguoyun.web.controller.app.controller;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.domain.ContentArticle;
import com.guoguoyun.system.params.ContentArticleParams;
import com.guoguoyun.system.params.app.UserContributeAppLikeParams;
import com.guoguoyun.system.response.ContentArticleResponse;
import com.guoguoyun.system.response.app.AppUserFollowListResponse;
import com.guoguoyun.system.service.IContentArticleService;
import com.guoguoyun.web.controller.app.service.AppArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/app/contentArticle")
public class IContentArticleController extends BaseController {

    @Autowired
    private AppArticleService appArticleService;

    @Autowired
    private TokenService tokenService;

    /**
     * 首页文章
     * @return
     */
    @GetMapping("/articleIndex")
    public TableDataInfo articleIndex(ContentArticle contentArticle){
        startPage();
        List<ContentArticle> articleList = appArticleService.articleIndex(contentArticle);
        return getDataTable(articleList);
    }


    @GetMapping("/articleIndexNew")
    public TableDataInfo articleIndexNew(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "50") Integer pageSize){
        Page<ContentArticle> contentArticlePage = appArticleService.articleIndexNew(pageNum, pageSize);
        List<List<ContentArticle>> split = ListUtil.split(contentArticlePage.getRecords(), 5);
        long total = contentArticlePage.getTotal();
        return getDataTable(split,total);
    }

    /**
     * 文章分类
     * @return
     */
    @GetMapping("/articleClassifyS")
    public AjaxResult articleClassifyS(){
        List<Classify> articleClassifyS = appArticleService.articleClassifyS();
        return AjaxResult.success(articleClassifyS);
    }


    /**
     * 文章详情
     * @param
     * @return
     */
    @GetMapping("/articleDetail")
    public AjaxResult articleDetail(ContentArticleParams contentArticleParams){
        ContentArticle contentArticle = appArticleService.articleDetail(contentArticleParams);
        return AjaxResult.success(contentArticle);
    }

    /**
     * 我的关注 文章列表
     * @return
     */
    @GetMapping("/myFollowArticle")
    public TableDataInfo myFollowArticleList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        Long userId = tokenService.getLoginUser().getUserId();
        List<AppUserFollowListResponse> list = appArticleService.myFollowArticleList(userId);
        int i = (pageNum - 1) * pageSize;
        int i1 = (pageSize * pageNum);
        if (list.size() != 0){
            List<AppUserFollowListResponse> resultstatisticsVos = new ArrayList<>();
            if (i1>list.size()){
                resultstatisticsVos = list.subList(i,list.size());
            }else {
                resultstatisticsVos = list.subList(i, i1);
            }
            return getDataTable(resultstatisticsVos,list.size());
        }else {
            return getDataTable(list,list.size());
        }

    }


}
