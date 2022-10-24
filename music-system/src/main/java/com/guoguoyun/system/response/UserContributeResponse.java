package com.guoguoyun.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 投稿对象 user_contribute
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
@Accessors(chain = true)
@Data
public class UserContributeResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    private String userName;

    private String userAccount;

    private String albumNames;

    private List<Album> albums;

    /** 阅读数 */
    @Excel(name = "阅读数")
    private Integer readNum;


    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer checkState;

    private String checkStateName;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String imgUrls;

    private List<ImgUrlVo> imgUrlList;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<ImgUrlVo> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<ImgUrlVo> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }
}
