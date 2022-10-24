package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 投稿-专辑对象 user_contribute_album
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
@Accessors(chain = true)
@Data
public class UserContributeAlbum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 专辑id */
    @Excel(name = "专辑id")
    private Long albumId;

    /** 投稿id */
    @Excel(name = "投稿id")
    private Long contributeId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAlbumId(Long albumId) 
    {
        this.albumId = albumId;
    }

    public Long getAlbumId() 
    {
        return albumId;
    }
    public void setContributeId(Long contributeId) 
    {
        this.contributeId = contributeId;
    }

    public Long getContributeId() 
    {
        return contributeId;
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
            .append("albumId", getAlbumId())
            .append("contributeId", getContributeId())
            .toString();
    }
}
