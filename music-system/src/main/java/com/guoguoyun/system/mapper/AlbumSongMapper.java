package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.AlbumSong;
import org.springframework.stereotype.Repository;

/**
 * 专辑管理-乐曲管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Repository
public interface AlbumSongMapper extends BaseMapper<AlbumSong>
{
    /**
     * 查询专辑管理-乐曲管理
     * 
     * @param id 专辑管理-乐曲管理主键
     * @return 专辑管理-乐曲管理
     */
    public AlbumSong selectAlbumSongById(Long id);

    /**
     * 查询专辑管理-乐曲管理列表
     * 
     * @param albumSong 专辑管理-乐曲管理
     * @return 专辑管理-乐曲管理集合
     */
    public List<AlbumSong> selectAlbumSongList(AlbumSong albumSong);
    public List<AlbumSong> selectAlbumSongListLike(AlbumSong albumSong);

    /**
     * 新增专辑管理-乐曲管理
     * 
     * @param albumSong 专辑管理-乐曲管理
     * @return 结果
     */
    public int insertAlbumSong(AlbumSong albumSong);

    /**
     * 修改专辑管理-乐曲管理
     * 
     * @param albumSong 专辑管理-乐曲管理
     * @return 结果
     */
    public int updateAlbumSong(AlbumSong albumSong);

    /**
     * 删除专辑管理-乐曲管理
     * 
     * @param id 专辑管理-乐曲管理主键
     * @return 结果
     */
    public int deleteAlbumSongById(Long id);

    /**
     * 批量删除专辑管理-乐曲管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlbumSongByIds(Long[] ids);
}
