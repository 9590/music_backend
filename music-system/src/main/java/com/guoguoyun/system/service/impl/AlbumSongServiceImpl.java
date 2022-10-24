package com.guoguoyun.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AlbumSongRelation;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.params.AlbumSongExeclParams;
import com.guoguoyun.system.response.AlbumSongListResponse;
import com.guoguoyun.system.response.AlbumSongResponse;
import com.guoguoyun.system.service.IAlbumService;
import com.guoguoyun.system.service.IAlbumSongRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AlbumSongMapper;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.service.IAlbumSongService;

/**
 * 专辑管理-乐曲管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Service
public class AlbumSongServiceImpl extends ServiceImpl<AlbumSongMapper, AlbumSong> implements IAlbumSongService
{
    @Autowired
    private AlbumSongMapper albumSongMapper;

    @Autowired
    private IAlbumSongRelationService albumSongRelationService;

    @Autowired
    private IAlbumService albumService;

    /**
     * 查询专辑管理-乐曲管理
     * 
     * @param id 专辑管理-乐曲管理主键
     * @return 专辑管理-乐曲管理
     */
    @Override
    public AlbumSongResponse selectAlbumSongById(Long id)
    {
        AlbumSong albumSong = albumSongMapper.selectAlbumSongById(id);
        AlbumSongResponse albumSongRelation = BeanUtil.toBean(albumSong, AlbumSongResponse.class);
        QueryWrapper<AlbumSongRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AlbumSongRelation::getSongId,albumSong.getId());
        List<AlbumSongRelation> list = albumSongRelationService.list(wrapper);
        if (list.size() != 0){
            List<Long> albumids = list.stream().map(t -> t.getAlbumId()).collect(Collectors.toList());
            QueryWrapper<Album> wrapper1 = new QueryWrapper<>();
            wrapper1.lambda().in(Album::getId,albumids);
            wrapper1.lambda().eq(Album::getDeleteType,0);
            List<Album> albumList = albumService.list(wrapper1);
            albumSongRelation.setAlbums(albumList);
        }
        return albumSongRelation;
    }

    /**
     * 查询专辑管理-乐曲管理列表
     * 
     * @param albumSong 专辑管理-乐曲管理
     * @return 专辑管理-乐曲管理
     */
    @Override
    public PageInfo selectAlbumSongList(AlbumSong albumSong)
    {
        List<AlbumSong> albumSongs = albumSongMapper.selectAlbumSongListLike(albumSong);
        List<AlbumSongListResponse> albumSongListResponses = albumSongs.stream().map(t -> {
            AlbumSongListResponse albumSongListResponse = BeanUtil.toBean(t, AlbumSongListResponse.class);
            QueryWrapper<AlbumSongRelation> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(AlbumSongRelation::getSongId,t.getId());
            List<AlbumSongRelation> list = albumSongRelationService.list(wrapper);
            if (list.size() != 0){
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    QueryWrapper<Album> wrapper1 = new QueryWrapper<>();
                    wrapper1.lambda().eq(Album::getId,list.get(i).getAlbumId());
                    wrapper1.lambda().eq(Album::getDeleteType,0);
                    Album byId = albumService.getOne(wrapper1);
                    if (ObjectUtil.isNotEmpty(byId)){
                        if (i == list.size() - 1) {
                            stringBuffer.append(byId.getAlbumName());
                        } else {
                            stringBuffer.append(byId.getAlbumName() + ",");
                        }
                    }
                }
                if (ObjectUtil.isNotEmpty(stringBuffer)){
                    albumSongListResponse.setAlbums(stringBuffer.toString());
                }else {
                    albumSongListResponse.setAlbums("无");
                }

            }else {
                albumSongListResponse.setAlbums("无");
            }
            if (ObjectUtil.isEmpty(t.getSongPrecision())){
                albumSongListResponse.setSongPrecision("无");
            }
            return albumSongListResponse;
        }).collect(Collectors.toList());

        PageInfo<AlbumSong> page = new PageInfo<>(albumSongs);
        PageInfo<AlbumSongListResponse> pageInfo = new PageInfo<>(albumSongListResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(albumSongListResponses);
        return pageInfo;
    }

    /**
     * 新增专辑管理-乐曲管理
     * 
     * @param albumSong 专辑管理-乐曲管理
     * @return 结果
     */
    @Override
    public int insertAlbumSong(AlbumSong albumSong)
    {
        QueryWrapper<AlbumSong> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AlbumSong::getSongNumber,albumSong.getSongNumber());
        AlbumSong song = this.getOne(wrapper);
        if (ObjectUtil.isNotEmpty(song)){
            throw new SecurityException(MusicCode.ERROR_NUMBER_REOETITION);
        }

        albumSong.setCreateTime(DateUtils.getNowDate());
        albumSong.setId(IdUtil.getSnowflakeNextId());
        return albumSongMapper.insertAlbumSong(albumSong);
    }

    /**
     * 修改专辑管理-乐曲管理
     * 
     * @param albumSong 专辑管理-乐曲管理
     * @return 结果
     */
    @Override
    public int updateAlbumSong(AlbumSong albumSong)
    {
        albumSong.setUpdateTime(DateUtils.getNowDate());
        return albumSongMapper.updateAlbumSong(albumSong);
    }

    /**
     * 批量删除专辑管理-乐曲管理
     * 
     * @param ids 需要删除的专辑管理-乐曲管理主键
     * @return 结果
     */
    @Override
    public int deleteAlbumSongByIds(Long[] ids)
    {
        Long id = ids[0];
        QueryWrapper<AlbumSongRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AlbumSongRelation::getSongId,id);
        albumSongRelationService.remove(wrapper);
        return albumSongMapper.deleteAlbumSongByIds(ids);
    }

    /**
     * 删除专辑管理-乐曲管理信息
     * 
     * @param id 专辑管理-乐曲管理主键
     * @return 结果
     */
    @Override
    public int deleteAlbumSongById(Long id)
    {
        return albumSongMapper.deleteAlbumSongById(id);
    }

    @Override
    public void importData(List<AlbumSongExeclParams> songList) {
        songList.forEach(t -> {
            AlbumSong albumSong = BeanUtil.toBean(t, AlbumSong.class);
            albumSong.setUpdateTime(DateUtils.getNowDate());
            albumSong.setCreateBy(SecurityUtils.getLoginUser().getUsername());
            albumSong.setUpdateBy(SecurityUtils.getLoginUser().getUsername());

            Date songTime = t.getSongTime();
            albumSong.setSongTimeMin(Long.valueOf(songTime.getMinutes()));
            albumSong.setSongTimeSec(Long.valueOf(songTime.getSeconds()));

            this.insertAlbumSong(albumSong);
        });
    }
}
