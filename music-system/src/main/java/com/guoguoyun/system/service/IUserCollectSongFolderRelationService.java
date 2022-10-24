package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.UserCollectSongFolderRelation;

/**
 * 用户乐曲收藏夹与曲目关系Service接口
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
public interface IUserCollectSongFolderRelationService extends IService<UserCollectSongFolderRelation>
{
    /**
     * 查询用户乐曲收藏夹与曲目关系
     * 
     * @param id 用户乐曲收藏夹与曲目关系主键
     * @return 用户乐曲收藏夹与曲目关系
     */
    public UserCollectSongFolderRelation selectUserCollectSongFolderRelationById(Long id);

    /**
     * 查询用户乐曲收藏夹与曲目关系列表
     * 
     * @param userCollectSongFolderRelation 用户乐曲收藏夹与曲目关系
     * @return 用户乐曲收藏夹与曲目关系集合
     */
    public List<UserCollectSongFolderRelation> selectUserCollectSongFolderRelationList(UserCollectSongFolderRelation userCollectSongFolderRelation);

    /**
     * 新增用户乐曲收藏夹与曲目关系
     * 
     * @param userCollectSongFolderRelation 用户乐曲收藏夹与曲目关系
     * @return 结果
     */
    public int insertUserCollectSongFolderRelation(UserCollectSongFolderRelation userCollectSongFolderRelation);

    /**
     * 修改用户乐曲收藏夹与曲目关系
     * 
     * @param userCollectSongFolderRelation 用户乐曲收藏夹与曲目关系
     * @return 结果
     */
    public int updateUserCollectSongFolderRelation(UserCollectSongFolderRelation userCollectSongFolderRelation);

    /**
     * 批量删除用户乐曲收藏夹与曲目关系
     * 
     * @param ids 需要删除的用户乐曲收藏夹与曲目关系主键集合
     * @return 结果
     */
    public int deleteUserCollectSongFolderRelationByIds(Long[] ids);

    /**
     * 删除用户乐曲收藏夹与曲目关系信息
     * 
     * @param id 用户乐曲收藏夹与曲目关系主键
     * @return 结果
     */
    public int deleteUserCollectSongFolderRelationById(Long id);
}
