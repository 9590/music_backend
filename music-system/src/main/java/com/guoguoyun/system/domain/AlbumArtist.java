package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 参与艺人对象 album_artist
 *
 * @author xx
 * @date 2022-11-18
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class AlbumArtist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 参与艺人名称 */
    @Excel(name = "参与艺人名称")
    private String artistName;


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }

    public String getArtistName()
    {
        return artistName;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("artistName", getArtistName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
