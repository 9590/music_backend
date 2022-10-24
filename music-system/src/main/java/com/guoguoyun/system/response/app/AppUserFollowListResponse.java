package com.guoguoyun.system.response.app;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AppUserFollowListResponse extends BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 类型 1为投稿 2为动态
     */
    private Integer type;

    /**
     * 艺人
     */
    @Excel(name = "艺人")
    private Long userId;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 图片链接
     */
    @Excel(name = "图片链接")
    private String imgUrls;

    /**
     * 视频链接
     */
    @Excel(name = "视频链接")
    private String videoUrl;

    /**
     * 发布类型 1为前端 2为后台
     */
    @Excel(name = "发布类型 1为前端 2为后台")
    private Integer issueType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date endTime;

    /** 点赞数 */
    @Excel(name = "点赞数")
    @TableField(exist = false)
    private Integer likeNum;
    /**
     * 点赞状态  1为点赞 2为未点赞
     */
    @TableField(exist = false)
    private Integer likeStatus;

    @TableField(exist = false)
    private List<ImgUrlVo> imgUrlList;

    @TableField(exist = false)
    private List<ImgUrlVo> videoUrlList;

    /* 评论数 */
    @TableField(exist = false)
    private Integer commentCount;

    @TableField(exist = false)
    private AppUser user;


    /** 标题 */
    @Excel(name = "标题")
    private String title;







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


    /**
     * 收藏状态  1为收藏 2为未收藏
     */
    @TableField(exist = false)
    public Integer collectStatus;


    /** 专辑id */
    @TableField(exist = false)
    private List<Long> albumIds;

    @TableField(exist = false)
    private List<Album> albumList;

}
