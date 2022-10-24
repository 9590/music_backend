package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.UserSearchLog;

/**
 * 搜索记录Service接口
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
public interface IUserSearchLogService extends IService<UserSearchLog>
{
    /**
     * 查询搜索记录
     * 
     * @param id 搜索记录主键
     * @return 搜索记录
     */
    public UserSearchLog selectUserSearchLogById(Long id);

    /**
     * 查询搜索记录列表
     * 
     * @param userSearchLog 搜索记录
     * @return 搜索记录集合
     */
    public List<UserSearchLog> selectUserSearchLogList(UserSearchLog userSearchLog);
    public List<UserSearchLog> selectUserSearchLogListGroup();

    /**
     * 新增搜索记录
     * 
     * @param userSearchLog 搜索记录
     * @return 结果
     */
    public int insertUserSearchLog(UserSearchLog userSearchLog);

    /**
     * 修改搜索记录
     * 
     * @param userSearchLog 搜索记录
     * @return 结果
     */
    public int updateUserSearchLog(UserSearchLog userSearchLog);

    /**
     * 批量删除搜索记录
     * 
     * @param ids 需要删除的搜索记录主键集合
     * @return 结果
     */
    public int deleteUserSearchLogByIds(Long[] ids);

    /**
     * 删除搜索记录信息
     * 
     * @param id 搜索记录主键
     * @return 结果
     */
    public int deleteUserSearchLogById(Long id);
}
