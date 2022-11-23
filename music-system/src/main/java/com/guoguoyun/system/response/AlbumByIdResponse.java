package com.guoguoyun.system.response;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.AlbumArtist;
import com.guoguoyun.system.domain.AlbumSong;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 专辑管理-专辑库对象 album
 *
 * @author ruoyi
 * @date 2022-02-18
 */
@Accessors(chain = true)
@Data
public class AlbumByIdResponse extends BaseEntity
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
    private List<AlbumArtist> participateArtist;

    private String participateArtistName;

    /** 专辑介绍 */
    @Excel(name = "专辑介绍")
    private String albumIntroduce;

    /** 制作资料 */
    @Excel(name = "制作资料")
    private String productionData;

    /** 包含曲目 */
    @Excel(name = "包含曲目")
    private List<AlbumSong> includeSong;

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
    /**
     * 收藏状态  1为收藏 2为没收藏
     */
    @TableField(exist = false)
    private Integer collectStatus;

    /**
     * 评论数
     */
    @TableField(exist = false)
    private Integer commentCount;

    public List<AlbumSong> getIncludeSong() {
        return includeSong;
    }

    public void setIncludeSong(List<AlbumSong> includeSong) {
        this.includeSong = includeSong;
    }

    public List<AlbumArtist> getParticipateArtist() {
        return participateArtist;
    }

    public void setParticipateArtist(List<AlbumArtist> participateArtist) {
        this.participateArtist = participateArtist;
    }
}
