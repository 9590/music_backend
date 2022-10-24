package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 系统管理-积分来源对象 sys_integral_source
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Accessors(chain = true)
@Data
public class SysIntegralSource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 类型  1为点击 2为播放 3为投稿 */
    @Excel(name = "类型  1为点击 2为播放 3为投稿")
    private Integer type;

    /** 次数 */
    @Excel(name = "次数")
    private Long countNum;

    /** 获得积分 */
    @Excel(name = "获得积分")
    private Long earnPoint;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setCountNum(Long countNum) 
    {
        this.countNum = countNum;
    }

    public Long getCountNum() 
    {
        return countNum;
    }
    public void setEarnPoint(Long earnPoint) 
    {
        this.earnPoint = earnPoint;
    }

    public Long getEarnPoint() 
    {
        return earnPoint;
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
            .append("type", getType())
            .append("countNum", getCountNum())
            .append("earnPoint", getEarnPoint())
            .toString();
    }
}
