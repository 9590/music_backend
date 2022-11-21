package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.AlbumAndArtist;

/**
 * 专辑、艺人关联Service接口
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
public interface IAlbumAndArtistService extends IService<AlbumAndArtist>
{
    /**
     * 查询专辑、艺人关联
     * 
     * @param id 专辑、艺人关联主键
     * @return 专辑、艺人关联
     */
    public AlbumAndArtist selectAlbumAndArtistById(Long id);

    /**
     * 查询专辑、艺人关联列表
     * 
     * @param albumAndArtist 专辑、艺人关联
     * @return 专辑、艺人关联集合
     */
    public List<AlbumAndArtist> selectAlbumAndArtistList(AlbumAndArtist albumAndArtist);

    /**
     * 新增专辑、艺人关联
     * 
     * @param albumAndArtist 专辑、艺人关联
     * @return 结果
     */
    public int insertAlbumAndArtist(AlbumAndArtist albumAndArtist);

    /**
     * 修改专辑、艺人关联
     * 
     * @param albumAndArtist 专辑、艺人关联
     * @return 结果
     */
    public int updateAlbumAndArtist(AlbumAndArtist albumAndArtist);

    /**
     * 批量删除专辑、艺人关联
     * 
     * @param ids 需要删除的专辑、艺人关联主键集合
     * @return 结果
     */
    public int deleteAlbumAndArtistByIds(Long[] ids);

    /**
     * 删除专辑、艺人关联信息
     * 
     * @param id 专辑、艺人关联主键
     * @return 结果
     */
    public int deleteAlbumAndArtistById(Long id);
}
