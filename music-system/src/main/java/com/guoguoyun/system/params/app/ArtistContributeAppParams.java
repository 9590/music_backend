package com.guoguoyun.system.params.app;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;

import java.util.List;

@Data
public class ArtistContributeAppParams {

    private String content;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private List<ImgUrlVo> imgUrls;


    private Long userId;

    public List<ImgUrlVo> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<ImgUrlVo> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
