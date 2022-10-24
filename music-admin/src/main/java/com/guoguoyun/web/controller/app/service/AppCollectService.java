package com.guoguoyun.web.controller.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.domain.UserCollectSongFolder;
import com.guoguoyun.system.params.app.UserCollectParams;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface AppCollectService {

    /**
     * 添加收藏
     * @param userCollectParams
     */
    public Integer userAddCollect(UserCollectParams userCollectParams);

    /**
     * 歌曲收藏夹
     * @param userId
     * @return
     */
    public Page<UserCollectSongFolder> songFolderList(Long userId, Integer pageNum, Integer pageSize);

    /**
     *
     * @param
     * @return
     */
    public PageInfo userCollectList(UserCollectParams userCollectParams);


    /**
     * 歌曲收藏夹详情
     * @param id
     * @return
     */
    public List<AlbumSong> songFolderDetail(Long id);
}
