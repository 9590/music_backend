package com.guoguoyun.system.response;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 内容管理-动态管理对象 content_dynamicstate
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Accessors(chain = true)
@Data
public class ContentDynamicstateResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 艺人 */
    @Excel(name = "艺人")
    private Long userId;

    private String userName;

    private String userAccount;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private List<ImgUrlVo> imgUrlList;

    /** 视频链接 */
    @Excel(name = "视频链接")
    private List<ImgUrlVo> videoUrlList;

    /** 发布类型 1为前端 2为后台 */
    @Excel(name = "发布类型 1为前端 2为后台")
    private String issueTypeName;

    public List<ImgUrlVo> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<ImgUrlVo> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public List<ImgUrlVo> getVideoUrlList() {
        return videoUrlList;
    }

    public void setVideoUrlList(List<ImgUrlVo> videoUrlList) {
        this.videoUrlList = videoUrlList;
    }
}
