package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AlbumUserMapper;
import com.guoguoyun.system.domain.AlbumUser;
import com.guoguoyun.system.service.IAlbumUserService;

/**
 * 专辑参与艺人Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@Service
public class AlbumUserServiceImpl extends ServiceImpl<AlbumUserMapper, AlbumUser> implements IAlbumUserService
{
    @Autowired
    private AlbumUserMapper albumUserMapper;

    /**
     * 查询专辑参与艺人
     * 
     * @param id 专辑参与艺人主键
     * @return 专辑参与艺人
     */
    @Override
    public AlbumUser selectAlbumUserById(Long id)
    {
        return albumUserMapper.selectAlbumUserById(id);
    }

    /**
     * 查询专辑参与艺人列表
     * 
     * @param albumUser 专辑参与艺人
     * @return 专辑参与艺人
     */
    @Override
    public List<AlbumUser> selectAlbumUserList(AlbumUser albumUser)
    {
        return albumUserMapper.selectAlbumUserList(albumUser);
    }

    /**
     * 新增专辑参与艺人
     * 
     * @param albumUser 专辑参与艺人
     * @return 结果
     */
    @Override
    public int insertAlbumUser(AlbumUser albumUser)
    {
        albumUser.setCreateTime(DateUtils.getNowDate());
        albumUser.setId(IdUtil.getSnowflakeNextId());
        albumUser.setUpdateTime(DateUtils.getNowDate());
        albumUser.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        albumUser.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return albumUserMapper.insertAlbumUser(albumUser);
    }

    /**
     * 修改专辑参与艺人
     * 
     * @param albumUser 专辑参与艺人
     * @return 结果
     */
    @Override
    public int updateAlbumUser(AlbumUser albumUser)
    {
        albumUser.setUpdateTime(DateUtils.getNowDate());
        return albumUserMapper.updateAlbumUser(albumUser);
    }

    /**
     * 批量删除专辑参与艺人
     * 
     * @param ids 需要删除的专辑参与艺人主键
     * @return 结果
     */
    @Override
    public int deleteAlbumUserByIds(Long[] ids)
    {
        return albumUserMapper.deleteAlbumUserByIds(ids);
    }

    /**
     * 删除专辑参与艺人信息
     * 
     * @param id 专辑参与艺人主键
     * @return 结果
     */
    @Override
    public int deleteAlbumUserById(Long id)
    {
        return albumUserMapper.deleteAlbumUserById(id);
    }
}
