package com.guoguoyun.system.response;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.Album;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 专辑管理-乐曲管理对象 album_song
 *
 * @author ruoyi
 * @date 2022-02-17
 */
@Accessors(chain = true)
@Data
public class AlbumSongResponse extends BaseEntity
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

    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
