package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserFollowMapper;
import com.guoguoyun.system.domain.UserFollow;
import com.guoguoyun.system.service.IUserFollowService;

/**
 * 用户关注Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements IUserFollowService
{
    @Autowired
    private UserFollowMapper userFollowMapper;

    /**
     * 查询用户关注
     * 
     * @param id 用户关注主键
     * @return 用户关注
     */
    @Override
    public UserFollow selectUserFollowById(Long id)
    {
        return userFollowMapper.selectUserFollowById(id);
    }

    /**
     * 查询用户关注列表
     * 
     * @param userFollow 用户关注
     * @return 用户关注
     */
    @Override
    public List<UserFollow> selectUserFollowList(UserFollow userFollow)
    {
        return userFollowMapper.selectUserFollowList(userFollow);
    }

    /**
     * 新增用户关注
     * 
     * @param userFollow 用户关注
     * @return 结果
     */
    @Override
    public int insertUserFollow(UserFollow userFollow)
    {
        userFollow.setCreateTime(DateUtils.getNowDate());
        userFollow.setId(IdUtil.getSnowflakeNextId());
        return userFollowMapper.insertUserFollow(userFollow);
    }

    /**
     * 修改用户关注
     * 
     * @param userFollow 用户关注
     * @return 结果
     */
    @Override
    public int updateUserFollow(UserFollow userFollow)
    {
        userFollow.setUpdateTime(DateUtils.getNowDate());
        return userFollowMapper.updateUserFollow(userFollow);
    }

    /**
     * 批量删除用户关注
     * 
     * @param ids 需要删除的用户关注主键
     * @return 结果
     */
    @Override
    public int deleteUserFollowByIds(Long[] ids)
    {
        return userFollowMapper.deleteUserFollowByIds(ids);
    }

    /**
     * 删除用户关注信息
     * 
     * @param id 用户关注主键
     * @return 结果
     */
    @Override
    public int deleteUserFollowById(Long id)
    {
        return userFollowMapper.deleteUserFollowById(id);
    }
}
