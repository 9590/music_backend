package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserCollectAlbumOrArticleMapper;
import com.guoguoyun.system.domain.UserCollectAlbumOrArticle;
import com.guoguoyun.system.service.IUserCollectAlbumOrArticleService;

/**
 * 用户收藏文章或专辑Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Service
public class UserCollectAlbumOrArticleServiceImpl extends ServiceImpl<UserCollectAlbumOrArticleMapper, UserCollectAlbumOrArticle> implements IUserCollectAlbumOrArticleService
{
    @Autowired
    private UserCollectAlbumOrArticleMapper userCollectAlbumOrArticleMapper;

    /**
     * 查询用户收藏文章或专辑
     * 
     * @param id 用户收藏文章或专辑主键
     * @return 用户收藏文章或专辑
     */
    @Override
    public UserCollectAlbumOrArticle selectUserCollectAlbumOrArticleById(Long id)
    {
        return userCollectAlbumOrArticleMapper.selectUserCollectAlbumOrArticleById(id);
    }

    /**
     * 查询用户收藏文章或专辑列表
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 用户收藏文章或专辑
     */
    @Override
    public List<UserCollectAlbumOrArticle> selectUserCollectAlbumOrArticleList(UserCollectAlbumOrArticle userCollectAlbumOrArticle)
    {
        return userCollectAlbumOrArticleMapper.selectUserCollectAlbumOrArticleList(userCollectAlbumOrArticle);
    }

    /**
     * 新增用户收藏文章或专辑
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 结果
     */
    @Override
    public int insertUserCollectAlbumOrArticle(UserCollectAlbumOrArticle userCollectAlbumOrArticle)
    {
        userCollectAlbumOrArticle.setCreateTime(DateUtils.getNowDate());
        userCollectAlbumOrArticle.setId(IdUtil.getSnowflakeNextId());
        return userCollectAlbumOrArticleMapper.insertUserCollectAlbumOrArticle(userCollectAlbumOrArticle);
    }

    /**
     * 修改用户收藏文章或专辑
     * 
     * @param userCollectAlbumOrArticle 用户收藏文章或专辑
     * @return 结果
     */
    @Override
    public int updateUserCollectAlbumOrArticle(UserCollectAlbumOrArticle userCollectAlbumOrArticle)
    {
        userCollectAlbumOrArticle.setUpdateTime(DateUtils.getNowDate());
        return userCollectAlbumOrArticleMapper.updateUserCollectAlbumOrArticle(userCollectAlbumOrArticle);
    }

    /**
     * 批量删除用户收藏文章或专辑
     * 
     * @param ids 需要删除的用户收藏文章或专辑主键
     * @return 结果
     */
    @Override
    public int deleteUserCollectAlbumOrArticleByIds(Long[] ids)
    {
        return userCollectAlbumOrArticleMapper.deleteUserCollectAlbumOrArticleByIds(ids);
    }

    /**
     * 删除用户收藏文章或专辑信息
     * 
     * @param id 用户收藏文章或专辑主键
     * @return 结果
     */
    @Override
    public int deleteUserCollectAlbumOrArticleById(Long id)
    {
        return userCollectAlbumOrArticleMapper.deleteUserCollectAlbumOrArticleById(id);
    }
}
