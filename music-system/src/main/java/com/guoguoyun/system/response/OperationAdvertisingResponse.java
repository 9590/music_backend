package com.guoguoyun.system.response;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 运行管理-广告管理对象 operation_advertising
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Accessors(chain = true)
@Data
public class OperationAdvertisingResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 广告名称 */
    @Excel(name = "广告名称")
    private String advName;

    /** 广告图片地址 */
    @Excel(name = "广告图片地址")
    private String advImgUrl;

    /** 链接 */
    @Excel(name = "链接")
    private String advUrl;

    /** 广告投放位置 */
    @Excel(name = "广告投放位置")
    private String advLocation;

    /** 排序 */
    @Excel(name = "排序")
    private Integer advSort;

    /** 显示 1为是 2为否 */
    @Excel(name = "显示 1为是 2为否")
    private String advShow;
}
