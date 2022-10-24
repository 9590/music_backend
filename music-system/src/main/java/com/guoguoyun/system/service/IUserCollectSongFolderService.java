package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.UserCollectSongFolder;

/**
 * 用户乐曲收藏夹Service接口
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
public interface IUserCollectSongFolderService extends IService<UserCollectSongFolder>
{
    /**
     * 查询用户乐曲收藏夹
     * 
     * @param id 用户乐曲收藏夹主键
     * @return 用户乐曲收藏夹
     */
    public UserCollectSongFolder selectUserCollectSongFolderById(Long id);

    /**
     * 查询用户乐曲收藏夹列表
     * 
     * @param userCollectSongFolder 用户乐曲收藏夹
     * @return 用户乐曲收藏夹集合
     */
    public List<UserCollectSongFolder> selectUserCollectSongFolderList(UserCollectSongFolder userCollectSongFolder);

    /**
     * 新增用户乐曲收藏夹
     * 
     * @param userCollectSongFolder 用户乐曲收藏夹
     * @return 结果
     */
    public int insertUserCollectSongFolder(UserCollectSongFolder userCollectSongFolder);

    /**
     * 修改用户乐曲收藏夹
     * 
     * @param userCollectSongFolder 用户乐曲收藏夹
     * @return 结果
     */
    public int updateUserCollectSongFolder(UserCollectSongFolder userCollectSongFolder);

    /**
     * 批量删除用户乐曲收藏夹
     * 
     * @param ids 需要删除的用户乐曲收藏夹主键集合
     * @return 结果
     */
    public int deleteUserCollectSongFolderByIds(Long[] ids);

    /**
     * 删除用户乐曲收藏夹信息
     * 
     * @param id 用户乐曲收藏夹主键
     * @return 结果
     */
    public int deleteUserCollectSongFolderById(Long id);
}
