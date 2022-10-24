package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserCollectSongFolderMapper;
import com.guoguoyun.system.domain.UserCollectSongFolder;
import com.guoguoyun.system.service.IUserCollectSongFolderService;

/**
 * 用户乐曲收藏夹Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
@Service
public class UserCollectSongFolderServiceImpl extends ServiceImpl<UserCollectSongFolderMapper, UserCollectSongFolder> implements IUserCollectSongFolderService
{
    @Autowired
    private UserCollectSongFolderMapper userCollectSongFolderMapper;

    /**
     * 查询用户乐曲收藏夹
     * 
     * @param id 用户乐曲收藏夹主键
     * @return 用户乐曲收藏夹
     */
    @Override
    public UserCollectSongFolder selectUserCollectSongFolderById(Long id)
    {
        return userCollectSongFolderMapper.selectUserCollectSongFolderById(id);
    }

    /**
     * 查询用户乐曲收藏夹列表
     * 
     * @param userCollectSongFolder 用户乐曲收藏夹
     * @return 用户乐曲收藏夹
     */
    @Override
    public List<UserCollectSongFolder> selectUserCollectSongFolderList(UserCollectSongFolder userCollectSongFolder)
    {
        return userCollectSongFolderMapper.selectUserCollectSongFolderList(userCollectSongFolder);
    }

    /**
     * 新增用户乐曲收藏夹
     * 
     * @param userCollectSongFolder 用户乐曲收藏夹
     * @return 结果
     */
    @Override
    public int insertUserCollectSongFolder(UserCollectSongFolder userCollectSongFolder)
    {
        userCollectSongFolder.setCreateTime(DateUtils.getNowDate());
        userCollectSongFolder.setId(IdUtil.getSnowflakeNextId());
        return userCollectSongFolderMapper.insertUserCollectSongFolder(userCollectSongFolder);
    }

    /**
     * 修改用户乐曲收藏夹
     * 
     * @param userCollectSongFolder 用户乐曲收藏夹
     * @return 结果
     */
    @Override
    public int updateUserCollectSongFolder(UserCollectSongFolder userCollectSongFolder)
    {
        userCollectSongFolder.setUpdateTime(DateUtils.getNowDate());
        return userCollectSongFolderMapper.updateUserCollectSongFolder(userCollectSongFolder);
    }

    /**
     * 批量删除用户乐曲收藏夹
     * 
     * @param ids 需要删除的用户乐曲收藏夹主键
     * @return 结果
     */
    @Override
    public int deleteUserCollectSongFolderByIds(Long[] ids)
    {
        return userCollectSongFolderMapper.deleteUserCollectSongFolderByIds(ids);
    }

    /**
     * 删除用户乐曲收藏夹信息
     * 
     * @param id 用户乐曲收藏夹主键
     * @return 结果
     */
    @Override
    public int deleteUserCollectSongFolderById(Long id)
    {
        return userCollectSongFolderMapper.deleteUserCollectSongFolderById(id);
    }
}
