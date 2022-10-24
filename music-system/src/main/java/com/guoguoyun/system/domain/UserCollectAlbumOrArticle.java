package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 用户收藏文章或专辑对象 user_collect_album_or_article
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Accessors(chain = true)
@Data
public class UserCollectAlbumOrArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 收藏id */
    @Excel(name = "收藏id")
    private Long passivityId;


    /** 1为专辑 2为文章 */
    @Excel(name = "1为专辑 2为文章")
    private Integer type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setPassivityId(Long passivityId) 
    {
        this.passivityId = passivityId;
    }

    public Long getPassivityId() 
    {
        return passivityId;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
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
            .append("userId", getUserId())
            .append("passivityId", getPassivityId())
            .append("type", getType())
            .toString();
    }
}
