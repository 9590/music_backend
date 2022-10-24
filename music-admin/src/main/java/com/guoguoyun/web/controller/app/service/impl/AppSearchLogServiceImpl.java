package com.guoguoyun.web.controller.app.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.UserSearchLog;
import com.guoguoyun.system.params.app.AppSearchParams;
import com.guoguoyun.system.service.IAlbumService;
import com.guoguoyun.system.service.IAlbumSongService;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.system.service.IUserSearchLogService;
import com.guoguoyun.web.controller.app.service.AppSearchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppSearchLogServiceImpl implements AppSearchLogService {

    @Autowired
    private IUserSearchLogService userSearchLogService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IAlbumSongService songService;

    @Autowired
    private IAppUserService userService;



    /**
     * 搜索热词
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> searchWordLog(Long userId) {
        Map<String, Object> map = new HashMap<>();

        List<UserSearchLog> userSearchLogs = userSearchLogService.selectUserSearchLogListGroup();
        map.put("hotSearch",userSearchLogs);

        if (ObjectUtil.isNotEmpty(userId)){
            QueryWrapper<UserSearchLog> userSearchLogQueryWrapper = new QueryWrapper<>();
            userSearchLogQueryWrapper.lambda().eq(UserSearchLog::getDeleteType,2);
            userSearchLogQueryWrapper.lambda().orderByDesc(UserSearchLog::getCreateTime);
            userSearchLogQueryWrapper.lambda().eq(UserSearchLog::getUserId,userId);
            List<UserSearchLog> list = userSearchLogService.list(userSearchLogQueryWrapper);
            List<String> collect = list.stream().map(t -> t.getSearchWord()).distinct().collect(Collectors.toList());
            map.put("userSearch",collect);
        }

        return map;
    }

    /**
     * 搜索
     * @param appSearchParams
     * @return
     */
    @Override
    public Map<String, Object> searchResult(AppSearchParams appSearchParams) {
        UserSearchLog userSearchLog = BeanUtil.toBean(appSearchParams, UserSearchLog.class);
        userSearchLogService.insertUserSearchLog(userSearchLog);

        Map<String, Object> map = new HashMap<>();

        QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
        albumQueryWrapper.lambda().eq(Album::getDeleteType,0);
        albumQueryWrapper.lambda().like(Album::getAlbumName,appSearchParams.getSearchWord());
        List<Album> albumList = albumService.list(albumQueryWrapper);

        QueryWrapper<AlbumSong> albumSongQueryWrapper = new QueryWrapper<>();
        albumSongQueryWrapper.lambda().eq(AlbumSong::getDeleteType,0);
        albumSongQueryWrapper.lambda().like(AlbumSong::getSongName,appSearchParams.getSearchWord());
        List<AlbumSong> songList = songService.list(albumSongQueryWrapper);

        QueryWrapper<AppUser> appUserQueryWrapper = new QueryWrapper<>();
        appUserQueryWrapper.lambda().eq(AppUser::getUserType,2);
        appUserQueryWrapper.lambda().like(AppUser::getUserName,appSearchParams.getSearchWord());
        List<AppUser> userList = userService.list(appUserQueryWrapper);


        map.put("user",userList);
        map.put("album",albumList);
        map.put("song",songList);
        return map;
    }

    @Override
    public void removeUserSearchLog(Long userId) {
        userSearchLogService.deleteUserSearchLogById(userId);
    }
}
