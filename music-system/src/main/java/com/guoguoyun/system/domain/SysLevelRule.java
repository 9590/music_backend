package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 系统管理-等级规则对象 sys_level_rule
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Accessors(chain = true)
@Data
public class SysLevelRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 积分 */
    @Excel(name = "积分")
    private Integer startNumber;

    /** 积分 */
    @Excel(name = "积分")
    private Integer endNumber;

    /** 等级 */
    @Excel(name = "等级")
    private Integer levelNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStartNumber(Integer startNumber) 
    {
        this.startNumber = startNumber;
    }

    public Integer getStartNumber() 
    {
        return startNumber;
    }
    public void setEndNumber(Integer endNumber) 
    {
        this.endNumber = endNumber;
    }

    public Integer getEndNumber() 
    {
        return endNumber;
    }
    public void setLevelNum(Integer levelNum) 
    {
        this.levelNum = levelNum;
    }

    public Integer getLevelNum() 
    {
        return levelNum;
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
            .append("startNumber", getStartNumber())
            .append("endNumber", getEndNumber())
            .append("levelNum", getLevelNum())
            .toString();
    }
}
