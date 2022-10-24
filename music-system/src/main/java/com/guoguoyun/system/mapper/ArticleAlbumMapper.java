package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.ArticleAlbum;
import org.springframework.stereotype.Repository;

/**
 * 文章相关专辑Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-17
 */
@Repository
public interface ArticleAlbumMapper extends BaseMapper<ArticleAlbum>
{
    /**
     * 查询文章相关专辑
     * 
     * @param id 文章相关专辑主键
     * @return 文章相关专辑
     */
    public ArticleAlbum selectArticleAlbumById(Long id);

    /**
     * 查询文章相关专辑列表
     * 
     * @param articleAlbum 文章相关专辑
     * @return 文章相关专辑集合
     */
    public List<ArticleAlbum> selectArticleAlbumList(ArticleAlbum articleAlbum);

    /**
     * 新增文章相关专辑
     * 
     * @param articleAlbum 文章相关专辑
     * @return 结果
     */
    public int insertArticleAlbum(ArticleAlbum articleAlbum);

    /**
     * 修改文章相关专辑
     * 
     * @param articleAlbum 文章相关专辑
     * @return 结果
     */
    public int updateArticleAlbum(ArticleAlbum articleAlbum);

    /**
     * 删除文章相关专辑
     * 
     * @param id 文章相关专辑主键
     * @return 结果
     */
    public int deleteArticleAlbumById(Long id);

    /**
     * 批量删除文章相关专辑
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleAlbumByIds(Long[] ids);
}
