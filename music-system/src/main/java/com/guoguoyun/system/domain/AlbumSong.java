package com.guoguoyun.system.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 专辑管理-乐曲管理对象 album_song
 *
 * @author ruoyi
 * @date 2022-02-17
 */
@Accessors(chain = true)
@Data
public class AlbumSong extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 乐曲名称 */
    @Excel(name = "乐曲名称")
    private String songName;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String songEnglistName;

    /** 乐曲时长(分) */
    @Excel(name = "乐曲时长(分)")
    private Long songTimeMin;

    /** 乐曲时长(秒) */
    @Excel(name = "乐曲时长(秒)")
    private Long songTimeSec;

    /** 所属专辑 */
    @Excel(name = "所属专辑")
    private Long albumId;

    /** 资料 */
    @Excel(name = "资料")
    private String songData;

    /** UPM价格 */
    @Excel(name = "UPM价格")
    private Double upmPrice;

    /** 歌词 */
    @Excel(name = "歌词")
    private String songLyricUrl;

    /** 音频精度 */
    @Excel(name = "音频精度")
    private String songPrecision;

    /** SQ音频 */
    @Excel(name = "SQ音频")
    private String sqUrl;

    /** UPM音频 */
    @Excel(name = "UPM音频")
    private String upmUrl;

    /** 乐曲编号 */
    @Excel(name = "乐曲编号")
    private String songNumber;

    /** isrc码 */
    @Excel(name = "isrc码")
    private String songIsrc;

    private Integer deleteType;

    /**
     * 收藏状态  1为收藏 2为没收藏
     */
    @TableField(exist = false)
    private Integer collectStatus;

    @TableField(exist = false)
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSongName(String songName)
    {
        this.songName = songName;
    }

    public String getSongName()
    {
        return songName;
    }
    public void setSongEnglistName(String songEnglistName)
    {
        this.songEnglistName = songEnglistName;
    }

    public String getSongEnglistName()
    {
        return songEnglistName;
    }
    public void setSongTimeMin(Long songTimeMin)
    {
        this.songTimeMin = songTimeMin;
    }

    public Long getSongTimeMin()
    {
        return songTimeMin;
    }
    public void setSongTimeSec(Long songTimeSec)
    {
        this.songTimeSec = songTimeSec;
    }

    public Long getSongTimeSec()
    {
        return songTimeSec;
    }
    public void setAlbumId(Long albumId)
    {
        this.albumId = albumId;
    }

    public Long getAlbumId()
    {
        return albumId;
    }
    public void setSongData(String songData)
    {
        this.songData = songData;
    }

    public String getSongData()
    {
        return songData;
    }
    public void setUpmPrice(Double upmPrice)
    {
        this.upmPrice = upmPrice;
    }

    public Double getUpmPrice()
    {
        return upmPrice;
    }
    public void setSongLyricUrl(String songLyricUrl)
    {
        this.songLyricUrl = songLyricUrl;
    }

    public String getSongLyricUrl()
    {
        return songLyricUrl;
    }
    public void setSongPrecision(String songPrecision)
    {
        this.songPrecision = songPrecision;
    }

    public String getSongPrecision()
    {
        return songPrecision;
    }
    public void setSqUrl(String sqUrl)
    {
        this.sqUrl = sqUrl;
    }

    public String getSqUrl()
    {
        return sqUrl;
    }
    public void setUpmUrl(String upmUrl)
    {
        this.upmUrl = upmUrl;
    }

    public String getUpmUrl()
    {
        return upmUrl;
    }
    public void setSongNumber(String songNumber)
    {
        this.songNumber = songNumber;
    }

    public String getSongNumber()
    {
        return songNumber;
    }
    public void setSongIsrc(String songIsrc)
    {
        this.songIsrc = songIsrc;
    }

    public String getSongIsrc()
    {
        return songIsrc;
    }

    public Integer getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(Integer deleteType) {
        this.deleteType = deleteType;
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
                .append("songName", getSongName())
                .append("songEnglistName", getSongEnglistName())
                .append("songTimeMin", getSongTimeMin())
                .append("songTimeSec", getSongTimeSec())
                .append("albumId", getAlbumId())
                .append("songData", getSongData())
                .append("upmPrice", getUpmPrice())
                .append("songLyricUrl", getSongLyricUrl())
                .append("songPrecision", getSongPrecision())
                .append("sqUrl", getSqUrl())
                .append("upmUrl", getUpmUrl())
                .append("songNumber", getSongNumber())
                .append("songIsrc", getSongIsrc())
                .toString();
    }
}
