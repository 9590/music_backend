package com.guoguoyun.system.response.app;


import com.guoguoyun.system.domain.Album;
import lombok.Data;

import java.util.List;

@Data
public class AppUserInfoResponse {

    /**
     * 昵称
     */
    private String userName;

    /**
     * 级别
     */
    private Integer userLevel;

    /**
     * 粉丝数
     */
    private Long fanCount;

    /**
     * 关注数
     */
    private Long followCount;

    /**
     * 用户分类  1为普通用户 2为艺人
     */
    private Integer userType;


    /**
     * 收藏曲目
     */
    private List collectSong;

    /**
     * 收藏曲目数
     */
    private Long collectSongCount;

    /**
     * 收藏专辑
     */
    private List<Album> collectAlbum;

    /**
     * 收藏专辑数
     */
    private Long collectAlbumCount;
}
