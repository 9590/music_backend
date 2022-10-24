package com.guoguoyun.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * app端用户对象 app_user
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Accessors(chain = true)
@Data
public class AppUserResponse extends BaseEntity
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

    /** 级别 */
    @Excel(name = "级别")
    private Integer userLevel;

    /** 用户当前经验 */
    @Excel(name = "用户当前经验")
    private Integer userExp;

    /** 用户会员级别  1为非会员  2为月 3为年 */
    @Excel(name = "用户会员级别  1为非会员  2为月 3为年")
    private Integer userVipState;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;



}
