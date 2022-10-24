package com.guoguoyun.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * app端用户对象 app_user
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Accessors(chain = true)
@Data
public class AppUser extends BaseEntity
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 用户分类  1为普通用户 2为艺人 */
    @Excel(name = "用户分类  1为普通用户 2为艺人")
    private Integer userType;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 收获地址 */
    @Excel(name = "收获地址")
    private String shippingAddress;

    /** 城市 */
    @Excel(name = "城市")
    private String cityName;

    /** 县区 */
    @Excel(name = "县区")
    private String areaName;

    /** 微信登录唯一标识 */
    @Excel(name = "微信登录唯一标识")
    private String wxOpenid;

    /** qq登录唯一标识 */
    @Excel(name = "qq登录唯一标识")
    private String qqLogin;

    /** 微博登录唯一标识 */
    @Excel(name = "微博登录唯一标识")
    private String wbLogin;

    /*    不属于数据库的字段     */
    /* 关注状态 1为已关注 2为未关注  */
    @TableField(exist = false)
    private Integer followStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserAccount(String userAccount)
    {
        this.userAccount = userAccount;
    }

    public String getUserAccount()
    {
        return userAccount;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserAvatar(String userAvatar)
    {
        this.userAvatar = userAvatar;
    }

    public String getUserAvatar()
    {
        return userAvatar;
    }
    public void setUserLevel(Integer userLevel)
    {
        this.userLevel = userLevel;
    }

    public Integer getUserLevel()
    {
        return userLevel;
    }
    public void setUserExp(Integer userExp)
    {
        this.userExp = userExp;
    }

    public Integer getUserExp()
    {
        return userExp;
    }
    public void setUserVipState(Integer userVipState)
    {
        this.userVipState = userVipState;
    }

    public Integer getUserVipState()
    {
        return userVipState;
    }
    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }

    public Integer getUserType()
    {
        return userType;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getBirthday()
    {
        return birthday;
    }
    public void setShippingAddress(String shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress()
    {
        return shippingAddress;
    }
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityName()
    {
        return cityName;
    }
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getAreaName()
    {
        return areaName;
    }
    public void setWxOpenid(String wxOpenid)
    {
        this.wxOpenid = wxOpenid;
    }

    public String getWxOpenid()
    {
        return wxOpenid;
    }
    public void setQqLogin(String qqLogin)
    {
        this.qqLogin = qqLogin;
    }

    public String getQqLogin()
    {
        return qqLogin;
    }
    public void setWbLogin(String wbLogin)
    {
        this.wbLogin = wbLogin;
    }

    public String getWbLogin()
    {
        return wbLogin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("userAccount", getUserAccount())
                .append("userName", getUserName())
                .append("userAvatar", getUserAvatar())
                .append("userLevel", getUserLevel())
                .append("userExp", getUserExp())
                .append("userVipState", getUserVipState())
                .append("lastLoginTime", getLastLoginTime())
                .append("password", getPassword())
                .append("userType", getUserType())
                .append("sex", getSex())
                .append("birthday", getBirthday())
                .append("shippingAddress", getShippingAddress())
                .append("cityName", getCityName())
                .append("areaName", getAreaName())
                .append("wxOpenid", getWxOpenid())
                .append("qqLogin", getQqLogin())
                .append("wbLogin", getWbLogin())
                .toString();
    }
}
