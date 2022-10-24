package com.guoguoyun.system.domain;

import java.util.Date;
import java.util.List;

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

/**
 * 投稿对象 user_contribute
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
@Accessors(chain = true)
@Data
public class UserContribute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String imgUrls;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 阅读数 */
    @Excel(name = "阅读数")
    private Integer readNum;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer likeNum;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer checkState;

    /**
     * 点赞状态  1为点赞 2为未点赞
     */
    @TableField(exist = false)
    private Integer likeStatus;

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

    @TableField(exist = false)
    private List<ImgUrlVo> imgUrlList;

    /* 评论数 */
    @TableField(exist = false)
    private Integer commentCount;

    @TableField(exist = false)
    private AppUser user;

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

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public List<Long> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<Long> albumIds) {
        this.albumIds = albumIds;
    }

    public List<ImgUrlVo> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<ImgUrlVo> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setImgUrls(String imgUrls) 
    {
        this.imgUrls = imgUrls;
    }

    public String getImgUrls() 
    {
        return imgUrls;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setReadNum(Integer readNum) 
    {
        this.readNum = readNum;
    }

    public Integer getReadNum() 
    {
        return readNum;
    }
    public void setLikeNum(Integer likeNum) 
    {
        this.likeNum = likeNum;
    }

    public Integer getLikeNum() 
    {
        return likeNum;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setCheckState(Integer checkState) 
    {
        this.checkState = checkState;
    }

    public Integer getCheckState() 
    {
        return checkState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("title", getTitle())
            .append("userId", getUserId())
            .append("imgUrls", getImgUrls())
            .append("content", getContent())
            .append("readNum", getReadNum())
            .append("likeNum", getLikeNum())
            .append("checkTime", getCheckTime())
            .append("checkState", getCheckState())
            .toString();
    }
}
