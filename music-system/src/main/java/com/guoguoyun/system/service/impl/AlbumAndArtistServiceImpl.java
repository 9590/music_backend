package com.guoguoyun.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AlbumAndArtistMapper;
import com.guoguoyun.system.domain.AlbumAndArtist;
import com.guoguoyun.system.service.IAlbumAndArtistService;

/**
 * 专辑、艺人关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@Service
public class AlbumAndArtistServiceImpl extends ServiceImpl<AlbumAndArtistMapper, AlbumAndArtist> implements IAlbumAndArtistService
{
    @Autowired
    private AlbumAndArtistMapper albumAndArtistMapper;

    /**
     * 查询专辑、艺人关联
     * 
     * @param id 专辑、艺人关联主键
     * @return 专辑、艺人关联
     */
    @Override
    public AlbumAndArtist selectAlbumAndArtistById(Long id)
    {
        return albumAndArtistMapper.selectAlbumAndArtistById(id);
    }

    /**
     * 查询专辑、艺人关联列表
     * 
     * @param albumAndArtist 专辑、艺人关联
     * @return 专辑、艺人关联
     */
    @Override
    public List<AlbumAndArtist> selectAlbumAndArtistList(AlbumAndArtist albumAndArtist)
    {
        return albumAndArtistMapper.selectAlbumAndArtistList(albumAndArtist);
    }

    /**
     * 新增专辑、艺人关联
     * 
     * @param albumAndArtist 专辑、艺人关联
     * @return 结果
     */
    @Override
    public int insertAlbumAndArtist(AlbumAndArtist albumAndArtist)
    {
        return albumAndArtistMapper.insertAlbumAndArtist(albumAndArtist);
    }

    /**
     * 修改专辑、艺人关联
     * 
     * @param albumAndArtist 专辑、艺人关联
     * @return 结果
     */
    @Override
    public int updateAlbumAndArtist(AlbumAndArtist albumAndArtist)
    {
        return albumAndArtistMapper.updateAlbumAndArtist(albumAndArtist);
    }

    /**
     * 批量删除专辑、艺人关联
     * 
     * @param ids 需要删除的专辑、艺人关联主键
     * @return 结果
     */
    @Override
    public int deleteAlbumAndArtistByIds(Long[] ids)
    {
        return albumAndArtistMapper.deleteAlbumAndArtistByIds(ids);
    }

    /**
     * 删除专辑、艺人关联信息
     * 
     * @param id 专辑、艺人关联主键
     * @return 结果
     */
    @Override
    public int deleteAlbumAndArtistById(Long id)
    {
        return albumAndArtistMapper.deleteAlbumAndArtistById(id);
    }
}
