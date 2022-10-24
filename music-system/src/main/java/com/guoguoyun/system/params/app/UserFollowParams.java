package com.guoguoyun.system.params.app;


import lombok.Data;

@Data
public class UserFollowParams {

    private Long userId;

    private Long fromId;

    private Long id;

    /**
     * 1为关注 2为未关注
     */
    private Integer type;

    private Integer pageNum;

    private Integer pageSize;
}
