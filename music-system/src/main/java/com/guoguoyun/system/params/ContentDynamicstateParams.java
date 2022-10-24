package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 内容管理-动态管理对象 content_dynamicstate
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Accessors(chain = true)
@Data
public class ContentDynamicstateParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class})
    private Long id;

    /** 艺人 */
    @Excel(name = "艺人")
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class,add.class})
    private Long userId;

    /** 内容 */
    @Excel(name = "内容")
    @NotBlank(message = "内容不能为空", groups = {BaseParam.edit.class,add.class})
    private String content;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private List<ImgUrlVo> imgUrls;

    /** 视频链接 */
    @Excel(name = "视频链接")
    private List<ImgUrlVo> videoUrl;

    public List<ImgUrlVo> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<ImgUrlVo> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<ImgUrlVo> getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(List<ImgUrlVo> videoUrl) {
        this.videoUrl = videoUrl;
    }
}
