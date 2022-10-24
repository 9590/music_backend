package com.guoguoyun.web.controller.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.params.app.UserCollectParams;
import com.guoguoyun.system.service.*;
import com.guoguoyun.web.controller.app.service.AppCollectService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppCollectServiceImpl implements AppCollectService {

    @Autowired
    private IUserCollectAlbumOrArticleService userCollectAlbumOrArticleService;

    @Autowired
    private IUserCollectSongFolderService userCollectSongFolderService;

    @Autowired
    private IUserCollectSongFolderRelationService userCollectSongFolderRelationService;

    @Autowired
    private IContentArticleService contentArticleService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IAlbumSongService albumSongService;

    /**
     * 添加收藏
     * @param userCollectParams
     */
    @Override
    public Integer userAddCollect(UserCollectParams userCollectParams) {
        if (userCollectParams.getType() == 3){
            QueryWrapper<UserCollectSongFolder> userCollectSongFolderQueryWrapper = new QueryWrapper<>();
            userCollectSongFolderQueryWrapper.lambda().eq(UserCollectSongFolder::getUserId,userCollectParams.getUserId());
            List<UserCollectSongFolder> list = userCollectSongFolderService.list(userCollectSongFolderQueryWrapper);
            UserCollectSongFolder userCollectSongFolder = new UserCollectSongFolder();
            if (list.size() == 0){
                userCollectSongFolder.setUserId(userCollectParams.getUserId());
                userCollectSongFolder.setFolderName("默认歌曲收藏夹");
                userCollectSongFolderService.insertUserCollectSongFolder(userCollectSongFolder);
            }else {
                userCollectSongFolder = list.get(0);
            }

            if (userCollectParams.getStatus() == 1){
                UserCollectSongFolderRelation userCollectSongFolderRelation = new UserCollectSongFolderRelation();
                userCollectSongFolderRelation.setFolderId(userCollectSongFolder.getId());
                userCollectSongFolderRelation.setSongId(userCollectParams.getTargetId());
                userCollectSongFolderRelationService.insertUserCollectSongFolderRelation(userCollectSongFolderRelation);
            }else {
                QueryWrapper<UserCollectSongFolderRelation> wrapper = new QueryWrapper<>();
                wrapper.lambda().eq(UserCollectSongFolderRelation::getFolderId,userCollectSongFolder.getId());
                wrapper.lambda().eq(UserCollectSongFolderRelation::getSongId,userCollectParams.getTargetId());
                userCollectSongFolderRelationService.remove(wrapper);
            }


        }else {
            if (userCollectParams.getStatus() == 1){
                UserCollectAlbumOrArticle userCollectAlbumOrArticle = new UserCollectAlbumOrArticle();
                userCollectAlbumOrArticle.setType(userCollectParams.getType());
                userCollectAlbumOrArticle.setUserId(userCollectParams.getUserId());
                userCollectAlbumOrArticle.setPassivityId(userCollectParams.getTargetId());
                userCollectAlbumOrArticleService.insertUserCollectAlbumOrArticle(userCollectAlbumOrArticle);
            }else {
                QueryWrapper<UserCollectAlbumOrArticle> wrapper = new QueryWrapper<>();
                wrapper.lambda().eq(UserCollectAlbumOrArticle::getType,userCollectParams.getType());
                wrapper.lambda().eq(UserCollectAlbumOrArticle::getUserId,userCollectParams.getUserId());
                wrapper.lambda().eq(UserCollectAlbumOrArticle::getPassivityId,userCollectParams.getTargetId());
                userCollectAlbumOrArticleService.remove(wrapper);
            }
        }


        return userCollectParams.getStatus();
    }

    /**
     * 乐曲收藏夹
     * @param userId
     * @return
     */
    @Override
    public Page<UserCollectSongFolder> songFolderList(Long userId,Integer pageNum,Integer pageSize) {
        Page<UserCollectSongFolder> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UserCollectSongFolder> userCollectSongFolderQueryWrapper = new QueryWrapper<>();
        userCollectSongFolderQueryWrapper.lambda().eq(UserCollectSongFolder::getUserId,userId);
        Page<UserCollectSongFolder> songFolderPage = userCollectSongFolderService.page(page, userCollectSongFolderQueryWrapper);
        return songFolderPage;
    }

    @Override
    public PageInfo userCollectList(UserCollectParams userCollectParams) {
        if (userCollectParams.getType() == 1){
            Map<String, Object> map = new HashMap<>();
            map.put("userId",userCollectParams.getUserId());
            Album album = new Album();
            album.setParams(map);
            PageInfo pageInfo = albumService.selectAlbumList(album);
            return pageInfo;
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("userId",userCollectParams.getUserId());
            ContentArticle article = new ContentArticle();
            article.setParams(map);
            PageInfo pageInfo = contentArticleService.selectContentArticleList(article);
            return pageInfo;
        }
    }

    /**
     * 歌曲收藏夹详情
     * @param id
     * @return
     */
    @Override
    public List<AlbumSong> songFolderDetail(Long id) {
        QueryWrapper<UserCollectSongFolderRelation> userCollectSongFolderRelationQueryWrapper = new QueryWrapper<>();
        userCollectSongFolderRelationQueryWrapper.lambda().eq(UserCollectSongFolderRelation::getFolderId,id);
        userCollectSongFolderRelationQueryWrapper.lambda().orderByDesc(UserCollectSongFolderRelation::getCreateTime);
        List<Long> songIds = userCollectSongFolderRelationService.list(userCollectSongFolderRelationQueryWrapper).stream().map(t -> t.getSongId()).collect(Collectors.toList());

        QueryWrapper<AlbumSong> songQueryWrapper = new QueryWrapper<>();
        songQueryWrapper.lambda().in(AlbumSong::getId,songIds);
        List<AlbumSong> list = albumSongService.list(songQueryWrapper);
        return list;
    }
}
