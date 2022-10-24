package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserSearchLogMapper;
import com.guoguoyun.system.domain.UserSearchLog;
import com.guoguoyun.system.service.IUserSearchLogService;

/**
 * 搜索记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@Service
public class UserSearchLogServiceImpl extends ServiceImpl<UserSearchLogMapper, UserSearchLog> implements IUserSearchLogService
{
    @Autowired
    private UserSearchLogMapper userSearchLogMapper;

    /**
     * 查询搜索记录
     * 
     * @param id 搜索记录主键
     * @return 搜索记录
     */
    @Override
    public UserSearchLog selectUserSearchLogById(Long id)
    {
        return userSearchLogMapper.selectUserSearchLogById(id);
    }

    /**
     * 查询搜索记录列表
     * 
     * @param userSearchLog 搜索记录
     * @return 搜索记录
     */
    @Override
    public List<UserSearchLog> selectUserSearchLogList(UserSearchLog userSearchLog)
    {
        return userSearchLogMapper.selectUserSearchLogList(userSearchLog);
    }

    @Override
    public List<UserSearchLog> selectUserSearchLogListGroup() {
        return userSearchLogMapper.selectUserSearchLogListGroup();
    }

    /**
     * 新增搜索记录
     * 
     * @param userSearchLog 搜索记录
     * @return 结果
     */
    @Override
    public int insertUserSearchLog(UserSearchLog userSearchLog)
    {
        userSearchLog.setCreateTime(DateUtils.getNowDate());
        userSearchLog.setId(IdUtil.getSnowflakeNextId());
        return userSearchLogMapper.insertUserSearchLog(userSearchLog);
    }

    /**
     * 修改搜索记录
     * 
     * @param userSearchLog 搜索记录
     * @return 结果
     */
    @Override
    public int updateUserSearchLog(UserSearchLog userSearchLog)
    {
        userSearchLog.setUpdateTime(DateUtils.getNowDate());
        return userSearchLogMapper.updateUserSearchLog(userSearchLog);
    }

    /**
     * 批量删除搜索记录
     * 
     * @param ids 需要删除的搜索记录主键
     * @return 结果
     */
    @Override
    public int deleteUserSearchLogByIds(Long[] ids)
    {
        return userSearchLogMapper.deleteUserSearchLogByIds(ids);
    }

    /**
     * 删除搜索记录信息
     * 
     * @param id 搜索记录主键
     * @return 结果
     */
    @Override
    public int deleteUserSearchLogById(Long id)
    {
        return userSearchLogMapper.deleteUserSearchLogById(id);
    }
}
