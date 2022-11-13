package com.guoguoyun.system.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
public class AlbumParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {edit.class})
    private Long id;

    /** 专辑名称 */
    @Excel(name = "专辑名称")
    @NotBlank(message = "专辑名称不能为空", groups = {edit.class,add.class})
    private String albumName;

    /** 英文名称 */
    @Excel(name = "英文名称")
    private String albumEnglishName;

    /** 编号 */
    @Excel(name = "编号")
    @NotBlank(message = "编号不能为空", groups = {edit.class,add.class})
    private String albumNumber;

    /** 分类id */
    @Excel(name = "分类id")
    // @NotNull(message = "分类id不能为空", groups = {edit.class,add.class})
    private Long albumClassifyId;

    /** 专辑价格 */
    @Excel(name = "专辑价格")
    // @NotNull(message = "专辑价格不能为空", groups = {edit.class,add.class})
    private Double albumPrice;

    /** 实体购买链接 */
    @Excel(name = "实体购买链接")
    private String buyUrl;

    /** 参与艺人 */
    @Excel(name = "参与艺人")
    // @NotNull(message = "参与艺人不能为空", groups = {edit.class,add.class})
    private List<Long> userIds;

    /** 专辑介绍 */
    @Excel(name = "专辑介绍")
    private String albumIntroduce;

    /** 制作资料 */
    @Excel(name = "制作资料")
    private String productionData;

    /** 包含曲目 */
    @Excel(name = "包含曲目")
    // @NotNull(message = "包含曲目不能为空", groups = {edit.class,add.class})
    private List<Long> songIds;

    /** ean码 */
    @Excel(name = "ean码")
    private String albumEan;

    /** 发行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @Excel(name = "发行时间", width = 30, dateFormat = "yyyy-MM-dd")
    // @NotNull(message = "发行时间不能为空", groups = {edit.class,add.class})
    private Date issueTime;

    /** 封面图片 */
    // @NotBlank(message = "封面图片不能为空", groups = {edit.class,add.class})
    private String imgUrl;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }


    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }
}
