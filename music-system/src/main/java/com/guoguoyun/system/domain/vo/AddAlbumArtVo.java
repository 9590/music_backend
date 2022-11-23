package com.guoguoyun.system.domain.vo;

import com.guoguoyun.system.domain.AlbumAndArtist;
import lombok.Data;

import java.util.List;

/**
 * 新增专辑参与艺人
 *
 * @author ZCL
 * @date 2022-11-21
 */
@Data
public class AddAlbumArtVo {

    private Integer albumId;
    private List<AlbumAndArtist> albumAndArtists;

}
