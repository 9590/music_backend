package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AlbumSongRelationMapper;
import com.guoguoyun.system.domain.AlbumSongRelation;
import com.guoguoyun.system.service.IAlbumSongRelationService;

/**
 * 乐曲和专辑关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
@Service
public class AlbumSongRelationServiceImpl extends ServiceImpl<AlbumSongRelationMapper, AlbumSongRelation> implements IAlbumSongRelationService
{
    @Autowired
    private AlbumSongRelationMapper albumSongRelationMapper;

    /**
     * 查询乐曲和专辑关系
     * 
     * @param id 乐曲和专辑关系主键
     * @return 乐曲和专辑关系
     */
    @Override
    public AlbumSongRelation selectAlbumSongRelationById(Long id)
    {
        return albumSongRelationMapper.selectAlbumSongRelationById(id);
    }

    /**
     * 查询乐曲和专辑关系列表
     * 
     * @param albumSongRelation 乐曲和专辑关系
     * @return 乐曲和专辑关系
     */
    @Override
    public List<AlbumSongRelation> selectAlbumSongRelationList(AlbumSongRelation albumSongRelation)
    {
        return albumSongRelationMapper.selectAlbumSongRelationList(albumSongRelation);
    }

    /**
     * 新增乐曲和专辑关系
     * 
     * @param albumSongRelation 乐曲和专辑关系
     * @return 结果
     */
    @Override
    public int insertAlbumSongRelation(AlbumSongRelation albumSongRelation)
    {
        albumSongRelation.setCreateTime(DateUtils.getNowDate());
        albumSongRelation.setUpdateTime(DateUtils.getNowDate());
        albumSongRelation.setId(IdUtil.getSnowflakeNextId());
        albumSongRelation.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        albumSongRelation.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return albumSongRelationMapper.insertAlbumSongRelation(albumSongRelation);
    }

    /**
     * 修改乐曲和专辑关系
     * 
     * @param albumSongRelation 乐曲和专辑关系
     * @return 结果
     */
    @Override
    public int updateAlbumSongRelation(AlbumSongRelation albumSongRelation)
    {
        albumSongRelation.setUpdateTime(DateUtils.getNowDate());
        albumSongRelation.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return albumSongRelationMapper.updateAlbumSongRelation(albumSongRelation);
    }

    /**
     * 批量删除乐曲和专辑关系
     * 
     * @param ids 需要删除的乐曲和专辑关系主键
     * @return 结果
     */
    @Override
    public int deleteAlbumSongRelationByIds(Long[] ids)
    {
        return albumSongRelationMapper.deleteAlbumSongRelationByIds(ids);
    }

    /**
     * 删除乐曲和专辑关系信息
     * 
     * @param id 乐曲和专辑关系主键
     * @return 结果
     */
    @Override
    public int deleteAlbumSongRelationById(Long id)
    {
        return albumSongRelationMapper.deleteAlbumSongRelationById(id);
    }
}
