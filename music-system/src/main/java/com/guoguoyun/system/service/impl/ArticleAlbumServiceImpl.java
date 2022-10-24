package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.ArticleAlbumMapper;
import com.guoguoyun.system.domain.ArticleAlbum;
import com.guoguoyun.system.service.IArticleAlbumService;

/**
 * 文章相关专辑Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-17
 */
@Service
public class ArticleAlbumServiceImpl extends ServiceImpl<ArticleAlbumMapper, ArticleAlbum> implements IArticleAlbumService
{
    @Autowired
    private ArticleAlbumMapper articleAlbumMapper;

    /**
     * 查询文章相关专辑
     * 
     * @param id 文章相关专辑主键
     * @return 文章相关专辑
     */
    @Override
    public ArticleAlbum selectArticleAlbumById(Long id)
    {
        return articleAlbumMapper.selectArticleAlbumById(id);
    }

    /**
     * 查询文章相关专辑列表
     * 
     * @param articleAlbum 文章相关专辑
     * @return 文章相关专辑
     */
    @Override
    public List<ArticleAlbum> selectArticleAlbumList(ArticleAlbum articleAlbum)
    {
        return articleAlbumMapper.selectArticleAlbumList(articleAlbum);
    }

    /**
     * 新增文章相关专辑
     * 
     * @param articleAlbum 文章相关专辑
     * @return 结果
     */
    @Override
    public int insertArticleAlbum(ArticleAlbum articleAlbum)
    {
        articleAlbum.setCreateTime(DateUtils.getNowDate());
        articleAlbum.setId(IdUtil.getSnowflakeNextId());
        articleAlbum.setUpdateTime(DateUtils.getNowDate());
        articleAlbum.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        articleAlbum.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return articleAlbumMapper.insertArticleAlbum(articleAlbum);
    }

    /**
     * 修改文章相关专辑
     * 
     * @param articleAlbum 文章相关专辑
     * @return 结果
     */
    @Override
    public int updateArticleAlbum(ArticleAlbum articleAlbum)
    {
        articleAlbum.setUpdateTime(DateUtils.getNowDate());
        return articleAlbumMapper.updateArticleAlbum(articleAlbum);
    }

    /**
     * 批量删除文章相关专辑
     * 
     * @param ids 需要删除的文章相关专辑主键
     * @return 结果
     */
    @Override
    public int deleteArticleAlbumByIds(Long[] ids)
    {
        return articleAlbumMapper.deleteArticleAlbumByIds(ids);
    }

    /**
     * 删除文章相关专辑信息
     * 
     * @param id 文章相关专辑主键
     * @return 结果
     */
    @Override
    public int deleteArticleAlbumById(Long id)
    {
        return articleAlbumMapper.deleteArticleAlbumById(id);
    }
}
