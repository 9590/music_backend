package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.ContentArticleTagMapper;
import com.guoguoyun.system.domain.ContentArticleTag;
import com.guoguoyun.system.service.IContentArticleTagService;

/**
 * 内容管理-文章标签管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class ContentArticleTagServiceImpl extends ServiceImpl<ContentArticleTagMapper, ContentArticleTag> implements IContentArticleTagService
{
    @Autowired
    private ContentArticleTagMapper contentArticleTagMapper;

    /**
     * 查询内容管理-文章标签管理
     * 
     * @param id 内容管理-文章标签管理主键
     * @return 内容管理-文章标签管理
     */
    @Override
    public ContentArticleTag selectContentArticleTagById(Long id)
    {
        return contentArticleTagMapper.selectContentArticleTagById(id);
    }

    /**
     * 查询内容管理-文章标签管理列表
     * 
     * @param contentArticleTag 内容管理-文章标签管理
     * @return 内容管理-文章标签管理
     */
    @Override
    public List<ContentArticleTag> selectContentArticleTagList(ContentArticleTag contentArticleTag)
    {
        return contentArticleTagMapper.selectContentArticleTagList(contentArticleTag);
    }

    /**
     * 新增内容管理-文章标签管理
     * 
     * @param contentArticleTag 内容管理-文章标签管理
     * @return 结果
     */
    @Override
    public int insertContentArticleTag(ContentArticleTag contentArticleTag)
    {
        contentArticleTag.setCreateTime(DateUtils.getNowDate());
        contentArticleTag.setId(IdUtil.getSnowflakeNextId());
        contentArticleTag.setUpdateTime(DateUtils.getNowDate());
        contentArticleTag.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        contentArticleTag.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return contentArticleTagMapper.insertContentArticleTag(contentArticleTag);
    }

    /**
     * 修改内容管理-文章标签管理
     * 
     * @param contentArticleTag 内容管理-文章标签管理
     * @return 结果
     */
    @Override
    public int updateContentArticleTag(ContentArticleTag contentArticleTag)
    {
        contentArticleTag.setUpdateTime(DateUtils.getNowDate());
        return contentArticleTagMapper.updateContentArticleTag(contentArticleTag);
    }

    /**
     * 批量删除内容管理-文章标签管理
     * 
     * @param ids 需要删除的内容管理-文章标签管理主键
     * @return 结果
     */
    @Override
    public int deleteContentArticleTagByIds(Long[] ids)
    {
        return contentArticleTagMapper.deleteContentArticleTagByIds(ids);
    }

    /**
     * 删除内容管理-文章标签管理信息
     * 
     * @param id 内容管理-文章标签管理主键
     * @return 结果
     */
    @Override
    public int deleteContentArticleTagById(Long id)
    {
        return contentArticleTagMapper.deleteContentArticleTagById(id);
    }
}
