package com.guoguoyun.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.Album;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * app端用户对象 app_user
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Accessors(chain = true)
@Data
public class AppUserActorResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userAccount;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userAvatar;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    private List<Album> albumIds;

    public List<Album> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<Album> albumIds) {
        this.albumIds = albumIds;
    }
}
