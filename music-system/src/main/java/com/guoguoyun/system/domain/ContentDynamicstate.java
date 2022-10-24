package com.guoguoyun.system.domain;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 内容管理-动态管理对象 content_dynamicstate
 *
 * @author ruoyi
 * @date 2022-02-15
 */
@Accessors(chain = true)
@Data
public class ContentDynamicstate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

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

    /**
     * 收藏状态  1为收藏 2为未收藏
     */
    @TableField(exist = false)
    public Integer collectStatus;
    /**
     * 评论树形列表
     */
    @TableField(exist = false)
    private List<Tree<String>> commentList;

    public List<Tree<String>> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Tree<String>> commentList) {
        this.commentList = commentList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setIssueType(Integer issueType) {
        this.issueType = issueType;
    }

    public Integer getIssueType() {
        return issueType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("userId", getUserId())
                .append("content", getContent())
                .append("imgUrls", getImgUrls())
                .append("videoUrl", getVideoUrl())
                .append("issueType", getIssueType())
                .toString();
    }
}
