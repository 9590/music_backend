package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.UserSearchLog;
import org.springframework.stereotype.Repository;

/**
 * 搜索记录Mapper接口
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@Repository
public interface UserSearchLogMapper extends BaseMapper<UserSearchLog>
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
     * 删除搜索记录
     * 
     * @param id 搜索记录主键
     * @return 结果
     */
    public int deleteUserSearchLogById(Long id);

    /**
     * 批量删除搜索记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserSearchLogByIds(Long[] ids);
}
