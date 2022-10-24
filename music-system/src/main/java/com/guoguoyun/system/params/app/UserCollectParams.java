package com.guoguoyun.system.params.app;

import com.guoguoyun.system.params.BaseParam;
import lombok.Data;

@Data
public class UserCollectParams extends BaseParam {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收藏id
     */
    private Long targetId;

    /**
     * 收藏类型  1为专辑 2为文章 3为歌曲 4为投稿 5为动态
     */
    private Integer type;

    /**
     * 收藏状态  1为收藏 2为取消收藏
     */
    private Integer status;

    private Long albumId;

    private Long songId;
}
