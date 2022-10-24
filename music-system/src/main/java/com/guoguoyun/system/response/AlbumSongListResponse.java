package com.guoguoyun.system.response;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专辑管理-乐曲管理对象 album_song
 *
 * @author ruoyi
 * @date 2022-02-17
 */
@Accessors(chain = true)
@Data
public class AlbumSongListResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 乐曲名称 */
    @Excel(name = "乐曲名称")
    private String songName;

    /** 音频精度 */
    @Excel(name = "音频精度")
    private String songPrecision;

    private String albums;


}
