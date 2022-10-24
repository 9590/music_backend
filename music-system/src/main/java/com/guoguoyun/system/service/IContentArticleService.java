package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.ContentArticle;
import com.guoguoyun.system.params.ContentArticleParams;
import com.guoguoyun.system.response.ContentArticleResponse;

/**
 * 内容管理-文章管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
public interface IContentArticleService extends IService<ContentArticle>
{
    /**
     * 查询内容管理-文章管理
     * 
     * @param id 内容管理-文章管理主键
     * @return 内容管理-文章管理
     */
    public ContentArticleResponse selectContentArticleById(Long id);

    /**
     * 查询内容管理-文章管理列表
     * 
     * @param contentArticle 内容管理-文章管理
     * @return 内容管理-文章管理集合
     */
    public PageInfo selectContentArticleList(ContentArticle contentArticle);

    /**
     * 新增内容管理-文章管理
     * 
     * @param contentArticle 内容管理-文章管理
     * @return 结果
     */
    public int insertContentArticle(ContentArticle contentArticle);
    public void insertContentArticle(ContentArticleParams contentArticleParams);

    /**
     * 修改内容管理-文章管理
     * 
     * @param contentArticle 内容管理-文章管理
     * @return 结果
     */
    public int updateContentArticle(ContentArticle contentArticle);
    public void updateContentArticle(ContentArticleParams contentArticleParams);

    /**
     * 批量删除内容管理-文章管理
     * 
     * @param ids 需要删除的内容管理-文章管理主键集合
     * @return 结果
     */
    public int deleteContentArticleByIds(Long[] ids);

    /**
     * 删除内容管理-文章管理信息
     * 
     * @param id 内容管理-文章管理主键
     * @return 结果
     */
    public int deleteContentArticleById(Long id);


    /**
     * 置顶/取消置顶
     * @param id
     */
    public int updateTop(Long id);
}
