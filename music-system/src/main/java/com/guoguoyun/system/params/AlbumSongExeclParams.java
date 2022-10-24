package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 专辑管理-乐曲管理对象 album_song
 *
 * @author ruoyi
 * @date 2022-02-17
 */
@Accessors(chain = true)
@Data
public class AlbumSongExeclParams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 乐曲名称 */
    @Excel(name = "曲目名称")
    private String songName;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String songEnglistName;

    /** 乐曲时长(分) */
    @Excel(name = "曲目时长", width = 30, dateFormat = "HH:mm:ss")
    private Date songTime;


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

    /** 乐曲编号 */
    @Excel(name = "曲目编号")
    private String songNumber;

    /** isrc码 */
    @Excel(name = "ISRC")
    private String songIsrc;
}
