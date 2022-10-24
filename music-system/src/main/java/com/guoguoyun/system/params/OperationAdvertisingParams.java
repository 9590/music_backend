package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 运行管理-广告管理对象 operation_advertising
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Accessors(chain = true)
@Data
public class OperationAdvertisingParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {edit.class})
    private Long id;

    /** 广告名称 */
    @NotBlank(message = "广告名称不能为空", groups = {edit.class,add.class})
    private String advName;

    /** 广告图片地址 */
    @NotBlank(message = "广告图片地址不能为空", groups = {edit.class,add.class})
    private String advImgUrl;

    /** 链接 */
    @Excel(name = "链接")
    private String advUrl;

    /** 广告投放位置 */
    @NotNull(message = "广告投放位置不能为空", groups = {edit.class,add.class})
    private Integer advLocation;

    /** 排序 */
    @NotNull(message = "排序不能为空", groups = {edit.class,add.class})
    private Integer advSort;

    /** 显示 1为是 2为否 */
    @NotNull(message = "显示不能为空", groups = {edit.class,add.class})
    private Integer advShow;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("advName", getAdvName())
            .append("advImgUrl", getAdvImgUrl())
            .append("advUrl", getAdvUrl())
            .append("advLocation", getAdvLocation())
            .append("advSort", getAdvSort())
            .append("advShow", getAdvShow())
            .toString();
    }
}
