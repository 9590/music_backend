package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserCollectSongFolderRelationMapper;
import com.guoguoyun.system.domain.UserCollectSongFolderRelation;
import com.guoguoyun.system.service.IUserCollectSongFolderRelationService;

/**
 * 用户乐曲收藏夹与曲目关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
@Service
public class UserCollectSongFolderRelationServiceImpl extends ServiceImpl<UserCollectSongFolderRelationMapper, UserCollectSongFolderRelation> implements IUserCollectSongFolderRelationService
{
    @Autowired
    private UserCollectSongFolderRelationMapper userCollectSongFolderRelationMapper;

    /**
     * 查询用户乐曲收藏夹与曲目关系
     * 
     * @param id 用户乐曲收藏夹与曲目关系主键
     * @return 用户乐曲收藏夹与曲目关系
     */
    @Override
    public UserCollectSongFolderRelation selectUserCollectSongFolderRelationById(Long id)
    {
        return userCollectSongFolderRelationMapper.selectUserCollectSongFolderRelationById(id);
    }

    /**
     * 查询用户乐曲收藏夹与曲目关系列表
     * 
     * @param userCollectSongFolderRelation 用户乐曲收藏夹与曲目关系
     * @return 用户乐曲收藏夹与曲目关系
     */
    @Override
    public List<UserCollectSongFolderRelation> selectUserCollectSongFolderRelationList(UserCollectSongFolderRelation userCollectSongFolderRelation)
    {
        return userCollectSongFolderRelationMapper.selectUserCollectSongFolderRelationList(userCollectSongFolderRelation);
    }

    /**
     * 新增用户乐曲收藏夹与曲目关系
     * 
     * @param userCollectSongFolderRelation 用户乐曲收藏夹与曲目关系
     * @return 结果
     */
    @Override
    public int insertUserCollectSongFolderRelation(UserCollectSongFolderRelation userCollectSongFolderRelation)
    {
        userCollectSongFolderRelation.setCreateTime(DateUtils.getNowDate());
        userCollectSongFolderRelation.setId(IdUtil.getSnowflakeNextId());
        return userCollectSongFolderRelationMapper.insertUserCollectSongFolderRelation(userCollectSongFolderRelation);
    }

    /**
     * 修改用户乐曲收藏夹与曲目关系
     * 
     * @param userCollectSongFolderRelation 用户乐曲收藏夹与曲目关系
     * @return 结果
     */
    @Override
    public int updateUserCollectSongFolderRelation(UserCollectSongFolderRelation userCollectSongFolderRelation)
    {
        userCollectSongFolderRelation.setUpdateTime(DateUtils.getNowDate());
        return userCollectSongFolderRelationMapper.updateUserCollectSongFolderRelation(userCollectSongFolderRelation);
    }

    /**
     * 批量删除用户乐曲收藏夹与曲目关系
     * 
     * @param ids 需要删除的用户乐曲收藏夹与曲目关系主键
     * @return 结果
     */
    @Override
    public int deleteUserCollectSongFolderRelationByIds(Long[] ids)
    {
        return userCollectSongFolderRelationMapper.deleteUserCollectSongFolderRelationByIds(ids);
    }

    /**
     * 删除用户乐曲收藏夹与曲目关系信息
     * 
     * @param id 用户乐曲收藏夹与曲目关系主键
     * @return 结果
     */
    @Override
    public int deleteUserCollectSongFolderRelationById(Long id)
    {
        return userCollectSongFolderRelationMapper.deleteUserCollectSongFolderRelationById(id);
    }
}
