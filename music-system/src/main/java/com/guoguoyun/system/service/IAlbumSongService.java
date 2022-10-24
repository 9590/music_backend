package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.params.AlbumSongExeclParams;
import com.guoguoyun.system.response.AlbumSongResponse;

/**
 * 专辑管理-乐曲管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
public interface IAlbumSongService extends IService<AlbumSong>
{
    /**
     * 查询专辑管理-乐曲管理
     * 
     * @param id 专辑管理-乐曲管理主键
     * @return 专辑管理-乐曲管理
     */
    public AlbumSongResponse selectAlbumSongById(Long id);

    /**
     * 查询专辑管理-乐曲管理列表
     * 
     * @param albumSong 专辑管理-乐曲管理
     * @return 专辑管理-乐曲管理集合
     */
    public PageInfo selectAlbumSongList(AlbumSong albumSong);

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
     * 批量删除专辑管理-乐曲管理
     * 
     * @param ids 需要删除的专辑管理-乐曲管理主键集合
     * @return 结果
     */
    public int deleteAlbumSongByIds(Long[] ids);

    /**
     * 删除专辑管理-乐曲管理信息
     * 
     * @param id 专辑管理-乐曲管理主键
     * @return 结果
     */
    public int deleteAlbumSongById(Long id);

    public void  importData(List<AlbumSongExeclParams> songList);
}
