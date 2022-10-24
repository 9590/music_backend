package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.UserComment;
import org.springframework.stereotype.Repository;

/**
 * 用户评论Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-10
 */
@Repository
public interface UserCommentMapper extends BaseMapper<UserComment>
{
    /**
     * 查询用户评论
     * 
     * @param id 用户评论主键
     * @return 用户评论
     */
    public UserComment selectUserCommentById(Long id);

    /**
     * 查询用户评论列表
     * 
     * @param userComment 用户评论
     * @return 用户评论集合
     */
    public List<UserComment> selectUserCommentList(UserComment userComment);

    /**
     * 新增用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    public int insertUserComment(UserComment userComment);

    /**
     * 修改用户评论
     * 
     * @param userComment 用户评论
     * @return 结果
     */
    public int updateUserComment(UserComment userComment);

    /**
     * 删除用户评论
     * 
     * @param id 用户评论主键
     * @return 结果
     */
    public int deleteUserCommentById(Long id);

    /**
     * 批量删除用户评论
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCommentByIds(Long[] ids);
}
