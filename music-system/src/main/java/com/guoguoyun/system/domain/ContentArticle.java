package com.guoguoyun.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 内容管理-文章管理对象 content_article
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Accessors(chain = true)
@Data
public class ContentArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String articleTitile;

    /** 文章类型 */
    @Excel(name = "文章类型")
    private Long articleType;

    /** 标签 */
    @Excel(name = "标签")
    private String articleTag;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String articleImgurl;

    /** 文章外链 */
    @Excel(name = "文章外链")
    private String articlePlace;

    /** 内容 */
    @Excel(name = "内容")
    private String articleContent;

    /** 是否置顶 1为是 0为否 */
    @Excel(name = "是否置顶 1为是 0为否")
    private Integer articleIsTop;

    /** 阅读数 */
    @Excel(name = "阅读数")
    private Long readNum;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likeNum;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long collectNum;

    /** 分享数 */
    @Excel(name = "分享数")
    private Long shareNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date endTime;

    /**
     * 点赞状态  1为点赞 2为未点赞
     */
    @TableField(exist = false)
    private Integer likeStatus;

    /**
     * 收藏数
     */
    @TableField(exist = false)
    private Integer collectCount;

    /**
     * 收藏状态 1为是  2为否
     */
    @TableField(exist = false)
    private Integer collectStatus;

    @TableField(exist = false)
    private String articleTypeName;

    @TableField(exist = false)
    private List<String> tagS;

    public List<String> getTagS() {
        return tagS;
    }

    public void setTagS(List<String> tagS) {
        this.tagS = tagS;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setArticleTitile(String articleTitile) 
    {
        this.articleTitile = articleTitile;
    }

    public String getArticleTitile() 
    {
        return articleTitile;
    }
    public void setArticleType(Long articleType) 
    {
        this.articleType = articleType;
    }

    public Long getArticleType() 
    {
        return articleType;
    }
    public void setArticleTag(String articleTag) 
    {
        this.articleTag = articleTag;
    }

    public String getArticleTag() 
    {
        return articleTag;
    }
    public void setArticleImgurl(String articleImgurl) 
    {
        this.articleImgurl = articleImgurl;
    }

    public String getArticleImgurl() 
    {
        return articleImgurl;
    }
    public void setArticlePlace(String articlePlace)
    {
        this.articlePlace = articlePlace;
    }

    public String getArticlePlace()
    {
        return articlePlace;
    }
    public void setArticleContent(String articleContent) 
    {
        this.articleContent = articleContent;
    }

    public String getArticleContent() 
    {
        return articleContent;
    }
    public void setArticleIsTop(Integer articleIsTop) 
    {
        this.articleIsTop = articleIsTop;
    }

    public Integer getArticleIsTop() 
    {
        return articleIsTop;
    }
    public void setReadNum(Long readNum) 
    {
        this.readNum = readNum;
    }

    public Long getReadNum() 
    {
        return readNum;
    }
    public void setLikeNum(Long likeNum) 
    {
        this.likeNum = likeNum;
    }

    public Long getLikeNum() 
    {
        return likeNum;
    }
    public void setCollectNum(Long collectNum) 
    {
        this.collectNum = collectNum;
    }

    public Long getCollectNum() 
    {
        return collectNum;
    }
    public void setShareNum(Long shareNum) 
    {
        this.shareNum = shareNum;
    }

    public Long getShareNum() 
    {
        return shareNum;
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
            .append("articleTitile", getArticleTitile())
            .append("articleType", getArticleType())
            .append("articleTag", getArticleTag())
            .append("articleImgurl", getArticleImgurl())
            .append("articlePlace", getArticlePlace())
            .append("articleContent", getArticleContent())
            .append("articleIsTop", getArticleIsTop())
            .append("readNum", getReadNum())
            .append("likeNum", getLikeNum())
            .append("collectNum", getCollectNum())
            .append("shareNum", getShareNum())
            .toString();
    }
}
