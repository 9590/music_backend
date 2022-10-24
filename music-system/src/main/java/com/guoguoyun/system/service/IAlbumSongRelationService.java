package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.AlbumSongRelation;

/**
 * 乐曲和专辑关系Service接口
 * 
 * @author ruoyi
 * @date 2022-02-18
 */
public interface IAlbumSongRelationService extends IService<AlbumSongRelation>
{
    /**
     * 查询乐曲和专辑关系
     * 
     * @param id 乐曲和专辑关系主键
     * @return 乐曲和专辑关系
     */
    public AlbumSongRelation selectAlbumSongRelationById(Long id);

    /**
     * 查询乐曲和专辑关系列表
     * 
     * @param albumSongRelation 乐曲和专辑关系
     * @return 乐曲和专辑关系集合
     */
    public List<AlbumSongRelation> selectAlbumSongRelationList(AlbumSongRelation albumSongRelation);

    /**
     * 新增乐曲和专辑关系
     * 
     * @param albumSongRelation 乐曲和专辑关系
     * @return 结果
     */
    public int insertAlbumSongRelation(AlbumSongRelation albumSongRelation);

    /**
     * 修改乐曲和专辑关系
     * 
     * @param albumSongRelation 乐曲和专辑关系
     * @return 结果
     */
    public int updateAlbumSongRelation(AlbumSongRelation albumSongRelation);

    /**
     * 批量删除乐曲和专辑关系
     * 
     * @param ids 需要删除的乐曲和专辑关系主键集合
     * @return 结果
     */
    public int deleteAlbumSongRelationByIds(Long[] ids);

    /**
     * 删除乐曲和专辑关系信息
     * 
     * @param id 乐曲和专辑关系主键
     * @return 结果
     */
    public int deleteAlbumSongRelationById(Long id);
}
