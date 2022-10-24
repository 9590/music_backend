package com.guoguoyun.system.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class AppUserActorParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class})
    private Long id;

    /** 用户账号 */
    @Excel(name = "用户账号")
    @NotBlank(message = "用户账号不能为空", groups = {BaseParam.edit.class,saveActor.class})
    private String userAccount;

    /** 用户名称 */
    @Excel(name = "用户名称")
    @NotBlank(message = "用户名称不能为空", groups = {BaseParam.edit.class,saveActor.class})
    private String userName;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userAvatar;


    /** 密码 */
    @Excel(name = "密码")
    @NotBlank(message = "密码不能为空", groups = {BaseParam.edit.class,saveActor.class})
    private String password;


    /** 收获地址 */
    @Excel(name = "专辑")
    private List<Long> albumIds;

    public List<Long> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<Long> albumIds) {
        this.albumIds = albumIds;
    }
}
