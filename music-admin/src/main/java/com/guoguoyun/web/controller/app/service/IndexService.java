package com.guoguoyun.web.controller.app.service;

import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.domain.Classify;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.response.AlbumByIdResponse;
import com.guoguoyun.system.response.app.ClassifyResponse;

import java.util.List;
import java.util.Map;

public interface IndexService {

    /**
     * 欢迎广告
     * @return
     */
    public List<OperationAdvertising> indexAdv();


    /**
     * 首页
     * @return
     */
    public Map<String,Object> indexSong();


    /**
     * 获得专辑分类
     * @return
     */
    public List<Classify> getAlbumClassify(Long parentId);


    /**
     * 所有专辑分类
     */
    public List<ClassifyResponse> getAlbumClassifyList();

    /**
     * 根据分类id获取专辑
     * @return
     */
    public List<Album> getAlbumByClassifyId(Album album);


    /**
     * 专辑信息
     */
    public AlbumByIdResponse infoAlbum(Long albumId, Long userId);

    /**
     * 歌曲信息
     * @param songId
     * @return
     */
    public AlbumSong infoSong(Long songId , Long userId);
}
