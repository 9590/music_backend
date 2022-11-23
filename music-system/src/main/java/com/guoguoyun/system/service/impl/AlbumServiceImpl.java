package com.guoguoyun.system.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.params.AlbumExeclParams;
import com.guoguoyun.system.params.AlbumParams;
import com.guoguoyun.system.response.AlbumByIdResponse;
import com.guoguoyun.system.response.AlbumResponse;
import com.guoguoyun.system.response.AlbumSongListResponse;
import com.guoguoyun.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AlbumMapper;

/**
 * 专辑管理-专辑库Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-11
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements IAlbumService
{
    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private IAlbumUserService albumUserService;

    @Autowired
    private IAlbumSongRelationService albumSongRelationService;

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IAlbumArtistService albumArtistService;

    @Autowired
    private IAlbumSongService albumSongService;

    /**
     * 查询专辑管理-专辑库
     *
     * @param id 专辑管理-专辑库主键
     * @return 专辑管理-专辑库
     */
    @Override
    public AlbumByIdResponse selectAlbumById(Long id)
    {
        Album album = albumMapper.selectAlbumById(id);
        AlbumByIdResponse albumByIdResponse = BeanUtil.toBean(album, AlbumByIdResponse.class);

        QueryWrapper<AlbumUser> albumUserQueryWrapper = new QueryWrapper<>();
        albumUserQueryWrapper.lambda().eq(AlbumUser::getAlbumId,albumByIdResponse.getId());
        List<Long> userIds = albumUserService.list(albumUserQueryWrapper).stream().map(t -> t.getUserId()).collect(Collectors.toList());
        QueryWrapper<AlbumArtist> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(AlbumArtist::getId,userIds);
        List<AlbumArtist> appUsers = albumArtistService.list(queryWrapper);
        albumByIdResponse.setParticipateArtist(appUsers);

        QueryWrapper<AlbumSongRelation> albumSongRelationQueryWrapper = new QueryWrapper<>();
        albumSongRelationQueryWrapper.lambda().eq(AlbumSongRelation::getAlbumId,albumByIdResponse.getId());
        List<Long> songIds = albumSongRelationService.list(albumSongRelationQueryWrapper).stream().map(t -> t.getSongId()).collect(Collectors.toList());
        if (songIds.size() != 0){
            QueryWrapper<AlbumSong> albumSongQueryWrapper = new QueryWrapper<>();
            albumSongQueryWrapper.lambda().in(AlbumSong::getId,songIds);
            List<AlbumSong> albumSongs = albumSongService.list(albumSongQueryWrapper);
            albumByIdResponse.setIncludeSong(albumSongs);
        }

        return albumByIdResponse;
    }

    /**
     * 查询专辑管理-专辑库列表
     *
     * @param album 专辑管理-专辑库
     * @return 专辑管理-专辑库
     */
    @Override
    public PageInfo selectAlbumList(Album album)
    {
        List<Album> albums = albumMapper.selectAlbumListLike(album);
        List<AlbumResponse> albumResponses = albums.stream().map(t -> {
            AlbumResponse albumResponse = BeanUtil.toBean(t, AlbumResponse.class);
            Classify classify = classifyService.getById(t.getAlbumClassifyId());
            if (ObjectUtil.isNotEmpty(classify)){
                albumResponse.setAlbumClassifyName(classify.getClassifyName());
            }


            QueryWrapper<AlbumUser> albumUserQueryWrapper = new QueryWrapper<>();
            albumUserQueryWrapper.lambda().eq(AlbumUser::getAlbumId,t.getId());
            albumUserQueryWrapper.lambda().orderByAsc(AlbumUser::getCreateTime);

            List<AlbumUser> albumUsers = albumUserService.list(albumUserQueryWrapper);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < albumUsers.size(); i++) {
                AlbumArtist appUser = albumArtistService.getById(albumUsers.get(i).getUserId());
                if (ObjectUtil.isNotEmpty(appUser)){
                    if (i == albumUsers.size() - 1) {
                        stringBuffer.append(appUser.getArtistName());
                    } else {
                        stringBuffer.append(appUser.getArtistName() + ",");
                    }
                }

            }
            albumResponse.setParticipateArtist(stringBuffer.toString());
            return albumResponse;
        }).collect(Collectors.toList());

        PageInfo<Album> page = new PageInfo<>(albums);
        PageInfo<AlbumResponse> pageInfo = new PageInfo<>(albumResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(albumResponses);

        return pageInfo;
    }

    /**
     * 新增专辑管理-专辑库
     *
     * @param album 专辑管理-专辑库
     * @return 结果
     */
    @Override
    public int insertAlbum(Album album)
    {
        QueryWrapper<Album> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Album::getAlbumNumber,album.getAlbumNumber());
        Album song = this.getOne(wrapper);
        if (ObjectUtil.isNotEmpty(song)){
            throw new SecurityException(MusicCode.ERROR_NUMBER_REOETITION);
        }

        album.setCreateTime(DateUtils.getNowDate());
        album.setId(IdUtil.getSnowflakeNextId());
        return albumMapper.insertAlbum(album);
    }

    @Override
    public void insertAlbum(AlbumParams albumParams) {
        Album album = BeanUtil.toBean(albumParams, Album.class);
        this.insertAlbum(album);


        //参与艺人
        albumParams.getUserIds().forEach(t -> {
            AlbumUser albumUser = new AlbumUser();
            albumUser.setUserId(t);
            albumUser.setAlbumId(album.getId());
            albumUserService.insertAlbumUser(albumUser);
        });

        albumParams.getSongIds().forEach(t -> {
            AlbumSongRelation albumSongRelation = new AlbumSongRelation();
            albumSongRelation.setAlbumId(album.getId());
            albumSongRelation.setSongId(t);
            albumSongRelationService.insertAlbumSongRelation(albumSongRelation);
        });
    }

    /**
     * 修改专辑管理-专辑库
     *
     * @param albumParams 专辑管理-专辑库
     * @return 结果
     */
    @Override
    public int updateAlbum(AlbumParams albumParams)
    {
        Album album = BeanUtil.toBean(albumParams, Album.class);
        album.setUpdateTime(DateUtils.getNowDate());
        album.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        album.setUpdateBy(SecurityUtils.getLoginUser().getUsername());

        //参与艺人
        QueryWrapper<AlbumUser> albumUserQueryWrapper = new QueryWrapper<>();
        albumUserQueryWrapper.lambda().eq(AlbumUser::getAlbumId,albumParams.getId());
        albumUserService.remove(albumUserQueryWrapper);
        albumParams.getUserIds().forEach(t -> {
            AlbumUser albumUser = new AlbumUser();
            albumUser.setUserId(t);
            albumUser.setAlbumId(album.getId());
            albumUserService.insertAlbumUser(albumUser);
        });

        //曲目
        QueryWrapper<AlbumSongRelation> albumSongRelationQueryWrapper = new QueryWrapper<>();
        albumSongRelationQueryWrapper.lambda().eq(AlbumSongRelation::getAlbumId,albumParams.getId());
        albumSongRelationService.remove(albumSongRelationQueryWrapper);
        albumParams.getSongIds().forEach(t -> {
            AlbumSongRelation albumSongRelation = new AlbumSongRelation();
            albumSongRelation.setAlbumId(album.getId());
            albumSongRelation.setSongId(t);
            albumSongRelationService.insertAlbumSongRelation(albumSongRelation);
        });

        return albumMapper.updateAlbum(album);
    }

    /**
     * 批量删除专辑管理-专辑库
     *
     * @param ids 需要删除的专辑管理-专辑库主键
     * @return 结果
     */
    @Override
    public int deleteAlbumByIds(Long[] ids)
    {
        return albumMapper.deleteAlbumByIds(ids);
    }

    /**
     * 删除专辑管理-专辑库信息
     *
     * @param id 专辑管理-专辑库主键
     * @return 结果
     */
    @Override
    public int deleteAlbumById(Long id)
    {
        return albumMapper.deleteAlbumById(id);
    }

    @Override
    public void importData(List<AlbumExeclParams> albumList) {
        albumList.forEach(t -> {
            Album album = BeanUtil.toBean(t, Album.class);
            //根据编号查分类
            if (ObjectUtil.isNotEmpty(t.getAlbumClassifyId())){
                QueryWrapper<Classify> classifyQueryWrapper = new QueryWrapper<>();
                classifyQueryWrapper.lambda().eq(Classify::getClassifyNumber,t.getAlbumClassifyId());
                Classify classify = classifyService.getOne(classifyQueryWrapper);
                if (ObjectUtil.isNotEmpty(classify)){
                    album.setAlbumClassifyId(classify.getId());
                }
            }
            this.insertAlbum(album);

            //参与艺人
            if (ObjectUtil.isNotEmpty(t.getUserIds())){
                boolean contains = t.getUserIds().contains("，");
                String[] users = new String[]{};
                if (contains){
                    users = t.getUserIds().split("，");
                }else {
                    users = t.getUserIds().split(",");
                }
                if (users.length != 0){
                    List<String> userIds = Arrays.asList(users);
                    QueryWrapper<AppUser> wrapper = new QueryWrapper<>();
                    wrapper.lambda().in(AppUser::getUserAccount,userIds);
                    List<AppUser> appUsers = appUserService.list(wrapper);
                    appUsers.forEach(x -> {
                        AlbumUser albumUser = new AlbumUser();
                        albumUser.setUserId(x.getId());
                        albumUser.setAlbumId(album.getId());
                        albumUserService.insertAlbumUser(albumUser);
                    });

                }

            }
            //包含曲目
            if (ObjectUtil.isNotEmpty(t.getSongIds())){
                boolean contains = t.getSongIds().contains("，");
                String[] songs = new String[]{};
                if (contains){
                    songs = t.getSongIds().split("，");
                }else {
                    songs = t.getSongIds().split(",");
                }
                if (songs.length != 0){
                    List<String> songIds = Arrays.asList(songs);
                    QueryWrapper<AlbumSong> wrapper = new QueryWrapper<>();
                    wrapper.lambda().in(AlbumSong::getSongNumber,songIds);
                    List<AlbumSong> albumSongs = albumSongService.list(wrapper);
                    albumSongs.forEach(x -> {
                        AlbumSongRelation albumSongRelation = new AlbumSongRelation();
                        albumSongRelation.setSongId(x.getId());
                        albumSongRelation.setAlbumId(album.getId());
                        albumSongRelationService.insertAlbumSongRelation(albumSongRelation);
                    });
                }
            }
        });
    }

    @Override
    public AjaxResult getMaxAlbumCode() {
        List<Map<String, Object>> maps = albumMapper.selectMaps(new QueryWrapper<Album>().select("max(album_number)"));
        //拿到最大编号+1  %08d防止乱码
        String workId = String.format("%08d", Integer.parseInt(maps.get(0).get("max(album_number)").toString()) + 1);

        return AjaxResult.success("success",workId);
    }
}
