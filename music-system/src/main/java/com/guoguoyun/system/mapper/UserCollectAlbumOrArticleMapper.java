package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.UserCollectAlbumOrArticle;
import org.springframework.stereotype.Repository;

/**
 * 用户收藏文章或专辑Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Repository
public interface UserCollectAlbumOrArticleMapper extends BaseMapper<UserCollectAlbumOrArticle>
{
    /**
     * 查询用户收藏文章或专辑
     * 
     * @param id 用户收藏文章或专辑主键
     * @return 用户收藏文章或专辑
     */
    public UserCollectAlbumOrArticle selectUserCollectAlbumOrArticleById(Long id);

    /**
     * 查询用户收藏文章或专辑列表
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 用户收藏文章或专辑集合
     */
    public List<UserCollectAlbumOrArticle> selectUserCollectAlbumOrArticleList(UserCollectAlbumOrArticle userCollectAlbumOrArticle);

    /**
     * 新增用户收藏文章或专辑
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 结果
     */
    public int insertUserCollectAlbumOrArticle(UserCollectAlbumOrArticle userCollectAlbumOrArticle);

    /**
     * 修改用户收藏文章或专辑
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 结果
     */
    public int updateUserCollectAlbumOrArticle(UserCollectAlbumOrArticle userCollectAlbumOrArticle);

    /**
     * 删除用户收藏文章或专辑
     * 
     * @param id 用户收藏文章或专辑主键
     * @return 结果
     */
    public int deleteUserCollectAlbumOrArticleById(Long id);

    /**
     * 批量删除用户收藏文章或专辑
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCollectAlbumOrArticleByIds(Long[] ids);
}
