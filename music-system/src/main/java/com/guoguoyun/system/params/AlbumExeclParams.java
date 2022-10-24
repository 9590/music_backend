package com.guoguoyun.system.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class AlbumExeclParams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */

    private Long id;

    /** 编号 */
    @Excel(name = "专辑编号")
    private String albumNumber;

    /** 专辑名称 */
    @Excel(name = "专辑名称")
    private String albumName;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String albumEnglishName;

    /** ean码 */
    @Excel(name = "EAN码")
    private String albumEan;

    /** 分类id */
    @Excel(name = "分类（编号）")
    private String albumClassifyId;

    /** 参与艺人 */
    @Excel(name = "参与艺人（账号，用逗号隔开）")
    private String userIds;

    /** 发行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Excel(name = "发行时间（年-月-日）", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueTime;

    /** 实体购买链接 */
    @Excel(name = "实体唱片购买链接")
    private String buyUrl;



    /** 专辑介绍 */
    @Excel(name = "专辑介绍")
    private String albumIntroduce;

    /** 制作资料 */
    @Excel(name = "制作资料")
    private String productionData;

    /** 包含曲目 */
    @Excel(name = "包含曲目（编号，用逗号隔开）")
    private String songIds;




    private String imgUrl;

}
