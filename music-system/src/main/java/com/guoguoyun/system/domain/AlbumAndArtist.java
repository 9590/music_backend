package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 专辑、艺人关联对象 album_and_artist
 *
 * @author xx
 * @date 2022-11-18
 */
@Accessors(chain = true)
@Data
public class AlbumAndArtist
{
    private static final long serialVersionUID = 1L;


    private Long id;


    private Long albumId;

    private Long albumArtistId;

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
    public void setAlbumArtistId(Long albumArtistId)
    {
        this.albumArtistId = albumArtistId;
    }

    public Long getAlbumArtistId()
    {
        return albumArtistId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("albumId", getAlbumId())
            .append("albumArtistId", getAlbumArtistId())
            .toString();
    }
}
