package com.guoguoyun.system.service;

import com.guoguoyun.system.domain.UserFollow;

import java.util.List;

public interface DynamicService {


    /**
     * 查询用户关注
     *
     * @param id 用户关注主键
     * @return 用户关注
     */
    UserFollow selectUserFollowById(Long id);

    /**
     * 查询用户关注列表
     *
     * @param userFollow 用户关注
     * @return 用户关注集合
     */
     List<UserFollow> selectUserFollowList(UserFollow userFollow);

    /**
     * 新增用户关注
     *
     * @param userFollow 用户关注
     * @return 结果
     */
     int insertUserFollow(UserFollow userFollow);

    /**
     * 修改用户关注
     *
     * @param userFollow 用户关注
     * @return 结果
     */
    int updateUserFollow(UserFollow userFollow);

    /**
     * 删除用户关注
     *
     * @param id 用户关注主键
     * @return 结果
     */
    int deleteUserFollowById(Long id);

    /**
     * 批量删除用户关注
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
     int deleteUserFollowByIds(Long[] ids);

}