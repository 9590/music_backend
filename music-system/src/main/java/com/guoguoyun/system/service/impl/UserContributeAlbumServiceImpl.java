package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserContributeAlbumMapper;
import com.guoguoyun.system.domain.UserContributeAlbum;
import com.guoguoyun.system.service.IUserContributeAlbumService;

/**
 * 投稿-专辑Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
@Service
public class UserContributeAlbumServiceImpl extends ServiceImpl<UserContributeAlbumMapper, UserContributeAlbum> implements IUserContributeAlbumService
{
    @Autowired
    private UserContributeAlbumMapper userContributeAlbumMapper;

    /**
     * 查询投稿-专辑
     * 
     * @param id 投稿-专辑主键
     * @return 投稿-专辑
     */
    @Override
    public UserContributeAlbum selectUserContributeAlbumById(Long id)
    {
        return userContributeAlbumMapper.selectUserContributeAlbumById(id);
    }

    /**
     * 查询投稿-专辑列表
     * 
     * @param userContributeAlbum 投稿-专辑
     * @return 投稿-专辑
     */
    @Override
    public List<UserContributeAlbum> selectUserContributeAlbumList(UserContributeAlbum userContributeAlbum)
    {
        return userContributeAlbumMapper.selectUserContributeAlbumList(userContributeAlbum);
    }

    /**
     * 新增投稿-专辑
     * 
     * @param userContributeAlbum 投稿-专辑
     * @return 结果
     */
    @Override
    public int insertUserContributeAlbum(UserContributeAlbum userContributeAlbum)
    {
        userContributeAlbum.setCreateTime(DateUtils.getNowDate());
        userContributeAlbum.setId(IdUtil.getSnowflakeNextId());
        return userContributeAlbumMapper.insertUserContributeAlbum(userContributeAlbum);
    }

    @Override
    public void insertUserContributeAlbum(Long contributeId, Long albumId) {
        UserContributeAlbum userContributeAlbum = new UserContributeAlbum();
        userContributeAlbum.setContributeId(contributeId);
        userContributeAlbum.setAlbumId(albumId);
        this.insertUserContributeAlbum(userContributeAlbum);
    }

    /**
     * 修改投稿-专辑
     * 
     * @param userContributeAlbum 投稿-专辑
     * @return 结果
     */
    @Override
    public int updateUserContributeAlbum(UserContributeAlbum userContributeAlbum)
    {
        userContributeAlbum.setUpdateTime(DateUtils.getNowDate());
        return userContributeAlbumMapper.updateUserContributeAlbum(userContributeAlbum);
    }

    /**
     * 批量删除投稿-专辑
     * 
     * @param ids 需要删除的投稿-专辑主键
     * @return 结果
     */
    @Override
    public int deleteUserContributeAlbumByIds(Long[] ids)
    {
        return userContributeAlbumMapper.deleteUserContributeAlbumByIds(ids);
    }

    /**
     * 删除投稿-专辑信息
     * 
     * @param id 投稿-专辑主键
     * @return 结果
     */
    @Override
    public int deleteUserContributeAlbumById(Long id)
    {
        return userContributeAlbumMapper.deleteUserContributeAlbumById(id);
    }
}
