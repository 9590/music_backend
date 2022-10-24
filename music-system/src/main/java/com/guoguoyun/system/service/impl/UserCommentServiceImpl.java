package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserCommentMapper;
import com.guoguoyun.system.domain.UserComment;
import com.guoguoyun.system.service.IUserCommentService;

/**
 * 用户评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-10
 */
@Service
public class UserCommentServiceImpl extends ServiceImpl<UserCommentMapper, UserComment> implements IUserCommentService
{
    @Autowired
    private UserCommentMapper userCommentMapper;

    /**
     * 查询用户评论
     * 
     * @param id 用户评论主键
     * @return 用户评论
     */
    @Override
    public UserComment selectUserCommentById(Long id)
    {
        return userCommentMapper.selectUserCommentById(id);
    }

    /**
     * 查询用户评论列表
     * 
     * @param userComment 用户评论
     * @return 用户评论
     */
    @Override
    public List<UserComment> selectUserCommentList(UserComment userComment)
    {
        return userCommentMapper.selectUserCommentList(userComment);
    }

    /**
     * 新增用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    @Override
    public int insertUserComment(UserComment userComment)
    {
        userComment.setCreateTime(DateUtils.getNowDate());
        userComment.setId(IdUtil.getSnowflakeNextId());
        return userCommentMapper.insertUserComment(userComment);
    }

    /**
     * 修改用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    @Override
    public int updateUserComment(UserComment userComment)
    {
        userComment.setUpdateTime(DateUtils.getNowDate());
        return userCommentMapper.updateUserComment(userComment);
    }

    /**
     * 批量删除用户评论
     * 
     * @param ids 需要删除的用户评论主键
     * @return 结果
     */
    @Override
    public int deleteUserCommentByIds(Long[] ids)
    {
        return userCommentMapper.deleteUserCommentByIds(ids);
    }

    /**
     * 删除用户评论信息
     * 
     * @param id 用户评论主键
     * @return 结果
     */
    @Override
    public int deleteUserCommentById(Long id)
    {
        return userCommentMapper.deleteUserCommentById(id);
    }
}
