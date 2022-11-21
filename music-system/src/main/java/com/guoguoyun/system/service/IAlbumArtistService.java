package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.AlbumArtist;

/**
 * 参与艺人Service接口
 *
 * @author xx
 * @date 2022-11-18
 */
public interface IAlbumArtistService extends IService<AlbumArtist>
{
    /**
     * 查询参与艺人
     *
     * @param id 参与艺人主键
     * @return 参与艺人
     */
    public AlbumArtist selectAlbumArtistById(Long id);

    /**
     * 查询参与艺人列表
     *
     * @param albumArtist 参与艺人
     * @return 参与艺人集合
     */
    public List<AlbumArtist> selectAlbumArtistList(AlbumArtist albumArtist);

    /**
     * 新增参与艺人
     *
     * @param albumArtist 参与艺人
     * @return 结果
     */
    public int insertAlbumArtist(AlbumArtist albumArtist);

    /**
     * 修改参与艺人
     *
     * @param albumArtist 参与艺人
     * @return 结果
     */
    public int updateAlbumArtist(AlbumArtist albumArtist);

    /**
     * 批量删除参与艺人
     *
     * @param ids 需要删除的参与艺人主键集合
     * @return 结果
     */
    public int deleteAlbumArtistByIds(Long[] ids);

    /**
     * 删除参与艺人信息
     *
     * @param id 参与艺人主键
     * @return 结果
     */
    public int deleteAlbumArtistById(Long id);
}
