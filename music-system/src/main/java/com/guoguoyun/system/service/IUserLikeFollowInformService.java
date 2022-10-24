package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.UserLikeFollowInform;

/**
 * 用户点赞关注通知Service接口
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
public interface IUserLikeFollowInformService extends IService<UserLikeFollowInform>
{
    /**
     * 查询用户点赞关注通知
     * 
     * @param id 用户点赞关注通知主键
     * @return 用户点赞关注通知
     */
    public UserLikeFollowInform selectUserLikeFollowInformById(Long id);

    /**
     * 查询用户点赞关注通知列表
     * 
     * @param userLikeFollowInform 用户点赞关注通知
     * @return 用户点赞关注通知集合
     */
    public List<UserLikeFollowInform> selectUserLikeFollowInformList(UserLikeFollowInform userLikeFollowInform);

    /**
     * 新增用户点赞关注通知
     * 
     * @param userLikeFollowInform 用户点赞关注通知
     * @return 结果
     */
    public int insertUserLikeFollowInform(UserLikeFollowInform userLikeFollowInform);

    /**
     * 修改用户点赞关注通知
     * 
     * @param userLikeFollowInform 用户点赞关注通知
     * @return 结果
     */
    public int updateUserLikeFollowInform(UserLikeFollowInform userLikeFollowInform);

    /**
     * 批量删除用户点赞关注通知
     * 
     * @param ids 需要删除的用户点赞关注通知主键集合
     * @return 结果
     */
    public int deleteUserLikeFollowInformByIds(Long[] ids);

    /**
     * 删除用户点赞关注通知信息
     * 
     * @param id 用户点赞关注通知主键
     * @return 结果
     */
    public int deleteUserLikeFollowInformById(Long id);
}
