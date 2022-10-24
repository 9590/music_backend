package com.guoguoyun.web.controller.app.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.enums.AdvertisingLocation;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.mapper.AlbumMapper;
import com.guoguoyun.system.mapper.OperationAdvertisingMapper;
import com.guoguoyun.system.response.AlbumByIdResponse;
import com.guoguoyun.system.response.AlbumResponse;
import com.guoguoyun.system.response.app.ClassifyResponse;
import com.guoguoyun.system.service.*;
import com.guoguoyun.web.controller.app.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IOperationAdvertisingService advertisingService;

    @Autowired
    private IOperationIndexService operationIndexService;

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private IAlbumSongRelationService albumSongRelationService;

    @Autowired
    private IAlbumUserService albumUserService;

    @Autowired
    private IAlbumSongService albumSongService;

    @Autowired
    private IUserCollectSongFolderService userCollectSongFolderService;

    @Autowired
    private IUserCollectSongFolderRelationService userCollectSongFolderRelationService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IUserCollectAlbumOrArticleService collectAlbumOrArticleService;

    @Autowired
    private IPlayRecordService playRecordService;

    /**
     * 欢迎广告
     *
     * @return
     */
    @Override
    public List<OperationAdvertising> indexAdv() {
        QueryWrapper<OperationAdvertising> advertisingQueryWrapper = new QueryWrapper<>();
        advertisingQueryWrapper.lambda().eq(OperationAdvertising::getAdvShow,1);
        advertisingQueryWrapper.lambda().orderByAsc(OperationAdvertising::getAdvSort);
        advertisingQueryWrapper.lambda().eq(OperationAdvertising::getAdvLocation, AdvertisingLocation.WELCOMEHOME.getCode());
        return advertisingService.list(advertisingQueryWrapper);
    }

    /**
     * 首页
     *
     * @return
     */
    @Override
    public Map<String, Object> indexSong() {
        Map<String, Object> map = operationIndexService.selectOperationIndexList();
        QueryWrapper<OperationAdvertising> advertisingQueryWrapper = new QueryWrapper<>();
        advertisingQueryWrapper.lambda().eq(OperationAdvertising::getAdvShow,1);
        advertisingQueryWrapper.lambda().orderByAsc(OperationAdvertising::getAdvSort);
        advertisingQueryWrapper.lambda().eq(OperationAdvertising::getAdvLocation, AdvertisingLocation.FLASHVIEW.getCode());
        List<OperationAdvertising> advList = advertisingService.list(advertisingQueryWrapper);
        map.put("adv", advList);
        return map;
    }


    /**
     * 专辑分类
     *
     * @param parentId 父类id
     * @return
     */
    @Override
    public List<Classify> getAlbumClassify(Long parentId) {
        QueryWrapper<Classify> classifyQueryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isEmpty(parentId)) {
            classifyQueryWrapper.lambda().eq(Classify::getClassifyParent, MusicCode.CLASSIFY_PARENT);
        } else {
            classifyQueryWrapper.lambda().eq(Classify::getClassifyParent, parentId);
        }
        classifyQueryWrapper.lambda().eq(Classify::getClassifyType, MusicCode.CLASSIFY_TYPE_ALBUM);
        List<Classify> classifies = classifyService.list(classifyQueryWrapper);
        return classifies;
    }

    /**
     * 所有专辑分类
     */
    @Override
    public List<ClassifyResponse> getAlbumClassifyList() {
        QueryWrapper<Classify> classifyQueryWrapper = new QueryWrapper<>();
        classifyQueryWrapper.lambda().eq(Classify::getClassifyParent, MusicCode.CLASSIFY_PARENT);
        classifyQueryWrapper.lambda().eq(Classify::getClassifyType, MusicCode.CLASSIFY_TYPE_ALBUM);
        List<Classify> classifies = classifyService.list(classifyQueryWrapper);
        List<ClassifyResponse> collect = classifies.stream().map(t -> {
            ClassifyResponse classifyResponse = BeanUtil.toBean(t, ClassifyResponse.class);
            Album album = new Album();
            album.setAlbumClassifyId(t.getId());
            List<Album> albums = albumMapper.selectAlbumListLike(album);
            classifyResponse.setAlbumList(albums);
            return classifyResponse;
        }).collect(Collectors.toList());

        return collect;
    }


    /**
     * 根据分类id获取专辑
     * @return
     */
    @Override
    public List<Album> getAlbumByClassifyId(Album album) {
        List<Album> albums = albumMapper.selectAlbumListLike(album);
        return albums;
    }

    @Override
    public AlbumByIdResponse infoAlbum(Long albumId, Long userId) {
        Album album = albumMapper.selectAlbumById(albumId);
        AlbumByIdResponse albumResponse = BeanUtil.toBean(album, AlbumByIdResponse.class);
        //专辑歌曲
        QueryWrapper<AlbumSongRelation> albumSongRelationQueryWrapper = new QueryWrapper<>();

        albumSongRelationQueryWrapper.lambda().eq(AlbumSongRelation::getAlbumId,album.getId());
        List<Long> songIds = albumSongRelationService.list(albumSongRelationQueryWrapper).stream().map(t -> t.getSongId()).collect(Collectors.toList());
        if (songIds.size() != 0){
            QueryWrapper<AlbumSong> albumSongQueryWrapper = new QueryWrapper<>();
            albumSongQueryWrapper.lambda().in(AlbumSong::getId,songIds);
            List<AlbumSong> albumSongs = albumSongService.list(albumSongQueryWrapper);
            albumResponse.setIncludeSong(albumSongs);
        }


        //艺人
        QueryWrapper<AlbumUser> albumUserQueryWrapper = new QueryWrapper<>();
        albumUserQueryWrapper.lambda().eq(AlbumUser::getAlbumId,albumId);
        albumUserQueryWrapper.lambda().orderByAsc(AlbumUser::getCreateTime);
        List<AlbumUser> albumUsers = albumUserService.list(albumUserQueryWrapper);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < albumUsers.size(); i++) {
            AppUser appUser = appUserService.getById(albumUsers.get(i).getUserId());
            if (ObjectUtil.isNotEmpty(appUser)){
                if (i == albumUsers.size() - 1) {
                    stringBuffer.append(appUser.getUserName());
                } else {
                    stringBuffer.append(appUser.getUserName() + ",");
                }
            }

        }
        albumResponse.setParticipateArtistName(stringBuffer.toString());

        if (ObjectUtil.isNotEmpty(userId)){
            QueryWrapper<UserCollectAlbumOrArticle> userCollectAlbumOrArticleQueryWrapper = new QueryWrapper<>();
            userCollectAlbumOrArticleQueryWrapper.lambda().eq(UserCollectAlbumOrArticle::getType,1);
            userCollectAlbumOrArticleQueryWrapper.lambda().eq(UserCollectAlbumOrArticle::getPassivityId,albumId);
            userCollectAlbumOrArticleQueryWrapper.lambda().eq(UserCollectAlbumOrArticle::getUserId,userId);
            UserCollectAlbumOrArticle orArticle = collectAlbumOrArticleService.getOne(userCollectAlbumOrArticleQueryWrapper);
            if (ObjectUtil.isNotEmpty(orArticle)){
                albumResponse.setCollectStatus(1);
            }else {
                albumResponse.setCollectStatus(2);
            }
        }else {
            albumResponse.setCollectStatus(2);
        }

        return albumResponse;

    }

    @Override
    public AlbumSong infoSong(Long songId, Long userId) {
        AlbumSong byId = albumSongService.getById(songId);
        if (ObjectUtil.isNotEmpty(userId)){



            QueryWrapper<UserCollectSongFolder> userCollectSongFolderQueryWrapper = new QueryWrapper<>();
            userCollectSongFolderQueryWrapper.lambda().eq(UserCollectSongFolder::getUserId,userId);
            List<UserCollectSongFolder> list = userCollectSongFolderService.list(userCollectSongFolderQueryWrapper);
            if (list.size() == 0){
                byId.setCollectStatus(2);
            }else {
                QueryWrapper<UserCollectSongFolderRelation> userCollectSongFolderRelationQueryWrapper = new QueryWrapper<>();
                userCollectSongFolderRelationQueryWrapper.lambda().eq(UserCollectSongFolderRelation::getFolderId,list.get(0).getId());
                userCollectSongFolderRelationQueryWrapper.lambda().eq(UserCollectSongFolderRelation::getSongId,songId);
                List<UserCollectSongFolderRelation> relationList = userCollectSongFolderRelationService.list(userCollectSongFolderRelationQueryWrapper);
                if (relationList.size() != 0){
                    byId.setCollectStatus(1);
                }else {
                    byId.setCollectStatus(2);
                }
            }

            PlayRecord playRecord = new PlayRecord();
            playRecord.setPlayTime(new Date());
            playRecord.setUserId(userId);
            playRecord.setAlbumId(songId);
            playRecordService.insertPlayRecord(playRecord);

        }else {
            byId.setCollectStatus(2);
        }


        return byId;
    }
}
