package com.guoguoyun.system.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 专辑管理-专辑库对象 album
 *
 * @author ruoyi
 * @date 2022-02-18
 */
@Accessors(chain = true)
@Data
public class Album extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 专辑名称 */
    @Excel(name = "专辑名称")
    private String albumName;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String albumEnglishName;

    /** 编号 */
    @Excel(name = "编号")
    private String albumNumber;

    /** 分类id */
    @Excel(name = "分类id")
    private Long albumClassifyId;

    /** 专辑价格 */
    @Excel(name = "专辑价格")
    private Double albumPrice;

    /** 实体购买链接 */
    @Excel(name = "实体购买链接")
    private String buyUrl;

    /** 参与艺人 */
    @Excel(name = "参与艺人")
    private String participateArtist;

    /** 专辑介绍 */
    @Excel(name = "专辑介绍")
    private String albumIntroduce;

    /** 制作资料 */
    @Excel(name = "制作资料")
    private String productionData;

    /** 包含曲目 */
    @Excel(name = "包含曲目")
    private String includeSong;

    /** ean码 */
    @Excel(name = "ean码")
    private String albumEan;

    private String imgUrl;

    private Integer deleteType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date endTime;

    /** 发行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Excel(name = "发行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAlbumName(String albumName)
    {
        this.albumName = albumName;
    }

    public String getAlbumName()
    {
        return albumName;
    }
    public void setAlbumEnglishName(String albumEnglishName)
    {
        this.albumEnglishName = albumEnglishName;
    }

    public String getAlbumEnglishName()
    {
        return albumEnglishName;
    }
    public void setAlbumNumber(String albumNumber)
    {
        this.albumNumber = albumNumber;
    }

    public String getAlbumNumber()
    {
        return albumNumber;
    }
    public void setAlbumClassifyId(Long albumClassifyId)
    {
        this.albumClassifyId = albumClassifyId;
    }

    public Long getAlbumClassifyId()
    {
        return albumClassifyId;
    }
    public void setAlbumPrice(Double albumPrice)
    {
        this.albumPrice = albumPrice;
    }

    public Double getAlbumPrice()
    {
        return albumPrice;
    }
    public void setBuyUrl(String buyUrl)
    {
        this.buyUrl = buyUrl;
    }

    public String getBuyUrl()
    {
        return buyUrl;
    }
    public void setParticipateArtist(String participateArtist)
    {
        this.participateArtist = participateArtist;
    }

    public String getParticipateArtist()
    {
        return participateArtist;
    }
    public void setAlbumIntroduce(String albumIntroduce)
    {
        this.albumIntroduce = albumIntroduce;
    }

    public String getAlbumIntroduce()
    {
        return albumIntroduce;
    }
    public void setProductionData(String productionData)
    {
        this.productionData = productionData;
    }

    public String getProductionData()
    {
        return productionData;
    }
    public void setIncludeSong(String includeSong)
    {
        this.includeSong = includeSong;
    }

    public String getIncludeSong()
    {
        return includeSong;
    }
    public void setAlbumEan(String albumEan)
    {
        this.albumEan = albumEan;
    }

    public String getAlbumEan()
    {
        return albumEan;
    }
    public void setIssueTime(Date issueTime)
    {
        this.issueTime = issueTime;
    }

    public Date getIssueTime()
    {
        return issueTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(Integer deleteType) {
        this.deleteType = deleteType;
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
                .append("albumName", getAlbumName())
                .append("albumEnglishName", getAlbumEnglishName())
                .append("albumNumber", getAlbumNumber())
                .append("albumClassifyId", getAlbumClassifyId())
                .append("albumPrice", getAlbumPrice())
                .append("buyUrl", getBuyUrl())
                .append("participateArtist", getParticipateArtist())
                .append("albumIntroduce", getAlbumIntroduce())
                .append("productionData", getProductionData())
                .append("includeSong", getIncludeSong())
                .append("albumEan", getAlbumEan())
                .append("issueTime", getIssueTime())
                .toString();
    }
}
