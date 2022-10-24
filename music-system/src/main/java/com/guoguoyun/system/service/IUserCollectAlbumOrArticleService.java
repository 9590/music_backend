package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.UserCollectAlbumOrArticle;

/**
 * 用户收藏文章或专辑Service接口
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
public interface IUserCollectAlbumOrArticleService extends IService<UserCollectAlbumOrArticle>
{
    /**
     * 查询用户收藏文章或专辑
     * 
     * @param id 用户收藏文章或专辑主键
     * @return 用户收藏文章或专辑
     */
    UserCollectAlbumOrArticle selectUserCollectAlbumOrArticleById(Long id);

    /**
     * 查询用户收藏文章或专辑列表
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 用户收藏文章或专辑集合
     */
     List<UserCollectAlbumOrArticle> selectUserCollectAlbumOrArticleList(UserCollectAlbumOrArticle userCollectAlbumOrArticle);

    /**
     * 新增用户收藏文章或专辑
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 结果
     */
     int insertUserCollectAlbumOrArticle(UserCollectAlbumOrArticle userCollectAlbumOrArticle);

    /**
     * 修改用户收藏文章或专辑
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 结果
     */
     int updateUserCollectAlbumOrArticle(UserCollectAlbumOrArticle userCollectAlbumOrArticle);

    /**
     * 批量删除用户收藏文章或专辑
     * 
     * @param ids 需要删除的用户收藏文章或专辑主键集合
     * @return 结果
     */
    int deleteUserCollectAlbumOrArticleByIds(Long[] ids);

    /**
     * 删除用户收藏文章或专辑信息
     * 
     * @param id 用户收藏文章或专辑主键
     * @return 结果
     */
     int deleteUserCollectAlbumOrArticleById(Long id);
}
