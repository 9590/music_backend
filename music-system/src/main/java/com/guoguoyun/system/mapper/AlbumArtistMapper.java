package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.AlbumArtist;
import org.springframework.stereotype.Repository;

/**
 * 参与艺人Mapper接口
 *
 * @author xx
 * @date 2022-11-18
 */
@Repository
public interface AlbumArtistMapper extends BaseMapper<AlbumArtist>
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
     * 删除参与艺人
     *
     * @param id 参与艺人主键
     * @return 结果
     */
    public int deleteAlbumArtistById(Long id);

    /**
     * 批量删除参与艺人
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlbumArtistByIds(Long[] ids);
}
