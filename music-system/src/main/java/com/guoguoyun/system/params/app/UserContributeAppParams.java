package com.guoguoyun.system.params.app;


import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;

import java.util.List;

@Data
public class UserContributeAppParams {

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private List<ImgUrlVo> imgUrls;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    private Long userId;

    /** 专辑id */
    private List<Long> albumIds;

    public List<ImgUrlVo> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<ImgUrlVo> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<Long> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<Long> albumIds) {
        this.albumIds = albumIds;
    }
}
