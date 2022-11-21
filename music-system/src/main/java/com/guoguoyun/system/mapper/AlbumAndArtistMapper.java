package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.AlbumAndArtist;
import org.springframework.stereotype.Repository;

/**
 * 专辑、艺人关联Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@Repository
public interface AlbumAndArtistMapper extends BaseMapper<AlbumAndArtist>
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
     * 删除专辑、艺人关联
     * 
     * @param id 专辑、艺人关联主键
     * @return 结果
     */
    public int deleteAlbumAndArtistById(Long id);

    /**
     * 批量删除专辑、艺人关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlbumAndArtistByIds(Long[] ids);
}
