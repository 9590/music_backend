package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.ContentArticleTag;
import org.springframework.stereotype.Repository;

/**
 * 内容管理-文章标签管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Repository
public interface ContentArticleTagMapper extends BaseMapper<ContentArticleTag>
{
    /**
     * 查询内容管理-文章标签管理
     * 
     * @param id 内容管理-文章标签管理主键
     * @return 内容管理-文章标签管理
     */
    public ContentArticleTag selectContentArticleTagById(Long id);

    /**
     * 查询内容管理-文章标签管理列表
     * 
     * @param contentArticleTag 内容管理-文章标签管理
     * @return 内容管理-文章标签管理集合
     */
    public List<ContentArticleTag> selectContentArticleTagList(ContentArticleTag contentArticleTag);

    /**
     * 新增内容管理-文章标签管理
     * 
     * @param contentArticleTag 内容管理-文章标签管理
     * @return 结果
     */
    public int insertContentArticleTag(ContentArticleTag contentArticleTag);

    /**
     * 修改内容管理-文章标签管理
     * 
     * @param contentArticleTag 内容管理-文章标签管理
     * @return 结果
     */
    public int updateContentArticleTag(ContentArticleTag contentArticleTag);

    /**
     * 删除内容管理-文章标签管理
     * 
     * @param id 内容管理-文章标签管理主键
     * @return 结果
     */
    public int deleteContentArticleTagById(Long id);

    /**
     * 批量删除内容管理-文章标签管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContentArticleTagByIds(Long[] ids);
}
