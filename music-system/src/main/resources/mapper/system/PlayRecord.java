package com.guoguoyun.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 播放记录对象 play_record
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
@Accessors(chain = true)
@Data
public class PlayRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 歌曲ID */
    @Excel(name = "歌曲ID")
    private Long albumId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 播放时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "播放时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date playTime;

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setPlayTime(Date playTime) 
    {
        this.playTime = playTime;
    }

    public Date getPlayTime() 
    {
        return playTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("albumId", getAlbumId())
            .append("userId", getUserId())
            .append("playTime", getPlayTime())
            .toString();
    }
}
