package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.UserCollectSongFolder;
import org.springframework.stereotype.Repository;

/**
 * 用户乐曲收藏夹Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
@Repository
public interface UserCollectSongFolderMapper extends BaseMapper<UserCollectSongFolder>
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
     * 删除用户乐曲收藏夹
     * 
     * @param id 用户乐曲收藏夹主键
     * @return 结果
     */
    public int deleteUserCollectSongFolderById(Long id);

    /**
     * 批量删除用户乐曲收藏夹
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCollectSongFolderByIds(Long[] ids);
}
