package com.guoguoyun.system.service.impl;

import java.util.List;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AlbumArtistMapper;
import com.guoguoyun.system.domain.AlbumArtist;
import com.guoguoyun.system.service.IAlbumArtistService;

/**
 * 参与艺人Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-18
 */
@Service
public class AlbumArtistServiceImpl extends ServiceImpl<AlbumArtistMapper, AlbumArtist> implements IAlbumArtistService
{
    @Autowired
    private AlbumArtistMapper albumArtistMapper;

    /**
     * 查询参与艺人
     *
     * @param id 参与艺人主键
     * @return 参与艺人
     */
    @Override
    public AlbumArtist selectAlbumArtistById(Long id)
    {
        return albumArtistMapper.selectAlbumArtistById(id);
    }

    /**
     * 查询参与艺人列表
     *
     * @param albumArtist 参与艺人
     * @return 参与艺人
     */
    @Override
    public List<AlbumArtist> selectAlbumArtistList(AlbumArtist albumArtist) {
        return albumArtistMapper.selectAlbumArtistList(albumArtist);
    }

    /**
     * 新增参与艺人
     *
     * @param albumArtist 参与艺人
     * @return 结果
     */
    @Override
    public int insertAlbumArtist(AlbumArtist albumArtist) {
        albumArtist.setCreateTime(DateUtils.getNowDate());
        return albumArtistMapper.insertAlbumArtist(albumArtist);
    }

    /**
     * 修改参与艺人
     *
     * @param albumArtist 参与艺人
     * @return 结果
     */
    @Override
    public int updateAlbumArtist(AlbumArtist albumArtist) {
        albumArtist.setUpdateTime(DateUtils.getNowDate());
        return albumArtistMapper.updateAlbumArtist(albumArtist);
    }

    /**
     * 批量删除参与艺人
     *
     * @param ids 需要删除的参与艺人主键
     * @return 结果
     */
    @Override
    public int deleteAlbumArtistByIds(Long[] ids)
    {
        return albumArtistMapper.deleteAlbumArtistByIds(ids);
    }

    /**
     * 删除参与艺人信息
     *
     * @param id 参与艺人主键
     * @return 结果
     */
    @Override
    public int deleteAlbumArtistById(Long id)
    {
        return albumArtistMapper.deleteAlbumArtistById(id);
    }
}
