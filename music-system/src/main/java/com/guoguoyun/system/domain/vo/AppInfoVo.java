package com.guoguoyun.system.domain.vo;

import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class AppInfoVo extends BaseEntity {

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 内容
     */
    private String content;











}
