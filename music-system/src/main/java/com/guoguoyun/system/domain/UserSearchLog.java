package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 搜索记录对象 user_search_log
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@Accessors(chain = true)
@Data
public class UserSearchLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 搜索词语 */
    @Excel(name = "搜索词语")
    private String searchWord;

    /** 是否删除 1为删除 2为未删除 */
    @Excel(name = "是否删除 1为删除 2为未删除")
    private Integer deleteType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSearchWord(String searchWord)
    {
        this.searchWord = searchWord;
    }

    public String getSearchWord()
    {
        return searchWord;
    }
    public void setDeleteType(Integer deleteType) 
    {
        this.deleteType = deleteType;
    }

    public Integer getDeleteType() 
    {
        return deleteType;
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
            .append("userId", getUserId())
            .append("searchWord", getSearchWord())
            .append("deleteType", getDeleteType())
            .toString();
    }
}
