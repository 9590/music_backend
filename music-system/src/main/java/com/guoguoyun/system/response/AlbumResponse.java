package com.guoguoyun.system.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 专辑管理-专辑库对象 album
 *
 * @author ruoyi
 * @date 2022-02-18
 */
@Accessors(chain = true)
@Data
public class AlbumResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 专辑名称 */
    @Excel(name = "专辑名称")
    private String albumName;


    /** 分类id */
    @Excel(name = "分类id")
    private String albumClassifyName;

    /** 专辑价格 */
    @Excel(name = "专辑价格")
    private Double albumPrice;


    /** 参与艺人 */
    @Excel(name = "参与艺人")
    private String participateArtist;


    /** 发行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Excel(name = "发行时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueTime;

}
