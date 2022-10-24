package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.ContentArticle;
import org.springframework.stereotype.Repository;

/**
 * 内容管理-文章管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Repository
public interface ContentArticleMapper extends BaseMapper<ContentArticle>
{
    /**
     * 查询内容管理-文章管理
     * 
     * @param id 内容管理-文章管理主键
     * @return 内容管理-文章管理
     */
    public ContentArticle selectContentArticleById(Long id);

    /**
     * 查询内容管理-文章管理列表
     * 
     * @param contentArticle 内容管理-文章管理
     * @return 内容管理-文章管理集合
     */
    public List<ContentArticle> selectContentArticleList(ContentArticle contentArticle);

    /**
     * 新增内容管理-文章管理
     * 
     * @param contentArticle 内容管理-文章管理
     * @return 结果
     */
    public int insertContentArticle(ContentArticle contentArticle);

    /**
     * 修改内容管理-文章管理
     * 
     * @param contentArticle 内容管理-文章管理
     * @return 结果
     */
    public int updateContentArticle(ContentArticle contentArticle);

    /**
     * 删除内容管理-文章管理
     * 
     * @param id 内容管理-文章管理主键
     * @return 结果
     */
    public int deleteContentArticleById(Long id);

    /**
     * 批量删除内容管理-文章管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContentArticleByIds(Long[] ids);


    /**
     * 修改置顶状态
     * @param id
     */
    public int updateTop(Long id);
}
