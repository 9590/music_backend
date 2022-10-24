package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 运行管理-广告管理对象 operation_advertising
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Accessors(chain = true)
@Data
public class OperationAdvertising extends BaseEntity
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
    private Integer advLocation;

    /** 排序 */
    @Excel(name = "排序")
    private Integer advSort;

    /** 显示 1为是 2为否 */
    @Excel(name = "显示 1为是 2为否")
    private Integer advShow;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAdvName(String advName) 
    {
        this.advName = advName;
    }

    public String getAdvName() 
    {
        return advName;
    }
    public void setAdvImgUrl(String advImgUrl) 
    {
        this.advImgUrl = advImgUrl;
    }

    public String getAdvImgUrl() 
    {
        return advImgUrl;
    }
    public void setAdvUrl(String advUrl) 
    {
        this.advUrl = advUrl;
    }

    public String getAdvUrl() 
    {
        return advUrl;
    }
    public void setAdvLocation(Integer advLocation) 
    {
        this.advLocation = advLocation;
    }

    public Integer getAdvLocation() 
    {
        return advLocation;
    }
    public void setAdvSort(Integer advSort) 
    {
        this.advSort = advSort;
    }

    public Integer getAdvSort() 
    {
        return advSort;
    }
    public void setAdvShow(Integer advShow) 
    {
        this.advShow = advShow;
    }

    public Integer getAdvShow() 
    {
        return advShow;
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
            .append("advName", getAdvName())
            .append("advImgUrl", getAdvImgUrl())
            .append("advUrl", getAdvUrl())
            .append("advLocation", getAdvLocation())
            .append("advSort", getAdvSort())
            .append("advShow", getAdvShow())
            .toString();
    }
}
