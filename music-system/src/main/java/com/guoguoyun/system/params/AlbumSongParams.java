package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 专辑管理-乐曲管理对象 album_song
 *
 * @author ruoyi
 * @date 2022-02-17
 */
@Accessors(chain = true)
@Data
public class AlbumSongParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class})
    private Long id;

    /** 乐曲名称 */
    @Excel(name = "乐曲名称")
    @NotBlank(message = "乐曲名称不能为空", groups = {BaseParam.edit.class,add.class})
    private String songName;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String songEnglistName;

    /** 乐曲时长(分) */
    @Excel(name = "乐曲时长(分)")
    // @NotNull(message = "乐曲时长(分)不能为空", groups = {BaseParam.edit.class,add.class})
    private Long songTimeMin;

    乐曲时长(秒) 
    @Excel(name = "乐曲时长(秒)")
    // @NotNull(message = "乐曲时长(秒)不能为空", groups = {BaseParam.edit.class,add.class})
    private Long songTimeSec;


    /** 资料 */
    @Excel(name = "资料")
    private String songData;

    /** UPM价格 */
    @Excel(name = "UPM价格")
    // @NotNull(message = "UPM价格不能为空", groups = {BaseParam.edit.class,add.class})
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
    @NotBlank(message = "乐曲编号不能为空", groups = {BaseParam.edit.class,add.class})
    private String songNumber;

    /** isrc码 */
    /** @Excel(name = "isrc码")
    @NotBlank(message = "isrc码不能为空", groups = {BaseParam.edit.class,add.class})
    private String songIsrc; */

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
