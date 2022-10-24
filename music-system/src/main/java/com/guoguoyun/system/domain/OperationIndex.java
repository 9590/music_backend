package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 运营管理-首页管理对象 operation_index
 * 
 * @author ruoyi
 * @date 2022-02-23
 */
@Accessors(chain = true)
@Data
public class OperationIndex extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 1为精选专辑  2为热门曲目 */
    @Excel(name = "1为精选专辑  2为热门曲目")
    private Integer type;

    /** 专辑或曲目id */
    @Excel(name = "专辑或曲目id")
    private Long albumSongId;

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
    public void setAlbumSongId(Long albumSongId) 
    {
        this.albumSongId = albumSongId;
    }

    public Long getAlbumSongId() 
    {
        return albumSongId;
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
            .append("albumSongId", getAlbumSongId())
            .toString();
    }
}
