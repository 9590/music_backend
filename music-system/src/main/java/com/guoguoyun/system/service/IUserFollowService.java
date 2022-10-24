package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.UserFollow;

/**
 * 用户关注Service接口
 * 
 * @author ruoyi
 * @date 2022-03-03
 */
public interface IUserFollowService extends IService<UserFollow>
{
    /**
     * 查询用户关注
     * 
     * @param id 用户关注主键
     * @return 用户关注
     */
    public UserFollow selectUserFollowById(Long id);

    /**
     * 查询用户关注列表
     * 
     * @param userFollow 用户关注
     * @return 用户关注集合
     */
    public List<UserFollow> selectUserFollowList(UserFollow userFollow);

    /**
     * 新增用户关注
     * 
     * @param userFollow 用户关注
     * @return 结果
     */
    public int insertUserFollow(UserFollow userFollow);

    /**
     * 修改用户关注
     * 
     * @param userFollow 用户关注
     * @return 结果
     */
    public int updateUserFollow(UserFollow userFollow);

    /**
     * 批量删除用户关注
     * 
     * @param ids 需要删除的用户关注主键集合
     * @return 结果
     */
    public int deleteUserFollowByIds(Long[] ids);

    /**
     * 删除用户关注信息
     * 
     * @param id 用户关注主键
     * @return 结果
     */
    public int deleteUserFollowById(Long id);
}
