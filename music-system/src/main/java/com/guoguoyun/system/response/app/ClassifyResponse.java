package com.guoguoyun.system.response.app;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.Album;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 商城管理-分类管理对象 classify
 *
 * @author ruoyi
 * @date 2022-02-17
 */
@Accessors(chain = true)
@Data
public class ClassifyResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String classifyName;

    /** 1为商品分类 2为专辑分类 3为文章分类 */
    @Excel(name = "1为商品分类 2为专辑分类 3为文章分类")
    private Integer classifyType;

    /** 分类编号 */
    @Excel(name = "分类编号")
    private String classifyNumber;

    /** 父类id */
    @Excel(name = "父类id")
    private Long classifyParent;

    private List<Album> albumList;

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setClassifyName(String classifyName)
    {
        this.classifyName = classifyName;
    }

    public String getClassifyName()
    {
        return classifyName;
    }
    public void setClassifyType(Integer classifyType)
    {
        this.classifyType = classifyType;
    }

    public Integer getClassifyType()
    {
        return classifyType;
    }
    public void setClassifyNumber(String classifyNumber)
    {
        this.classifyNumber = classifyNumber;
    }

    public String getClassifyNumber()
    {
        return classifyNumber;
    }
    public void setClassifyParent(Long classifyParent)
    {
        this.classifyParent = classifyParent;
    }

    public Long getClassifyParent()
    {
        return classifyParent;
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
                .append("classifyName", getClassifyName())
                .append("classifyType", getClassifyType())
                .append("classifyNumber", getClassifyNumber())
                .append("classifyParent", getClassifyParent())
                .toString();
    }
}
