package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserLikeFollowInformMapper;
import com.guoguoyun.system.domain.UserLikeFollowInform;
import com.guoguoyun.system.service.IUserLikeFollowInformService;

/**
 * 用户点赞关注通知Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
@Service
public class UserLikeFollowInformServiceImpl extends ServiceImpl<UserLikeFollowInformMapper, UserLikeFollowInform> implements IUserLikeFollowInformService
{
    @Autowired
    private UserLikeFollowInformMapper userLikeFollowInformMapper;

    /**
     * 查询用户点赞关注通知
     * 
     * @param id 用户点赞关注通知主键
     * @return 用户点赞关注通知
     */
    @Override
    public UserLikeFollowInform selectUserLikeFollowInformById(Long id)
    {
        return userLikeFollowInformMapper.selectUserLikeFollowInformById(id);
    }

    /**
     * 查询用户点赞关注通知列表
     * 
     * @param userLikeFollowInform 用户点赞关注通知
     * @return 用户点赞关注通知
     */
    @Override
    public List<UserLikeFollowInform> selectUserLikeFollowInformList(UserLikeFollowInform userLikeFollowInform)
    {
        return userLikeFollowInformMapper.selectUserLikeFollowInformList(userLikeFollowInform);
    }

    /**
     * 新增用户点赞关注通知
     * 
     * @param userLikeFollowInform 用户点赞关注通知
     * @return 结果
     */
    @Override
    public int insertUserLikeFollowInform(UserLikeFollowInform userLikeFollowInform)
    {
        userLikeFollowInform.setCreateTime(DateUtils.getNowDate());
        userLikeFollowInform.setId(IdUtil.getSnowflakeNextId());
        return userLikeFollowInformMapper.insertUserLikeFollowInform(userLikeFollowInform);
    }

    /**
     * 修改用户点赞关注通知
     * 
     * @param userLikeFollowInform 用户点赞关注通知
     * @return 结果
     */
    @Override
    public int updateUserLikeFollowInform(UserLikeFollowInform userLikeFollowInform)
    {
        userLikeFollowInform.setUpdateTime(DateUtils.getNowDate());
        return userLikeFollowInformMapper.updateUserLikeFollowInform(userLikeFollowInform);
    }

    /**
     * 批量删除用户点赞关注通知
     * 
     * @param ids 需要删除的用户点赞关注通知主键
     * @return 结果
     */
    @Override
    public int deleteUserLikeFollowInformByIds(Long[] ids)
    {
        return userLikeFollowInformMapper.deleteUserLikeFollowInformByIds(ids);
    }

    /**
     * 删除用户点赞关注通知信息
     * 
     * @param id 用户点赞关注通知主键
     * @return 结果
     */
    @Override
    public int deleteUserLikeFollowInformById(Long id)
    {
        return userLikeFollowInformMapper.deleteUserLikeFollowInformById(id);
    }
}
