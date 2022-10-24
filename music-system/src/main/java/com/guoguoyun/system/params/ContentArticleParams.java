package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 内容管理-文章管理对象 content_article
 * 
 * @author ruoyi
 * @date 2022-02-16
 */
@Accessors(chain = true)
@Data
public class ContentArticleParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class})
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class,add.class})
    private String articleTitile;

    /** 文章类型 */
    @Excel(name = "文章类型")
    private Long articleType;

    /** 标签 */
    @Excel(name = "标签")
    private List<String> articleTag;

    /** 图片链接 */
    @Excel(name = "图片链接")
    @NotBlank(message = "图片链接不能为空", groups = {BaseParam.edit.class,add.class})
    private String articleImgurl;

    /** 文章外链 */
    @Excel(name = "文章外链")
    private String articlePlace;

    /** 内容 */
    @Excel(name = "内容")
    private String articleContent;

    /** 是否置顶 1为是 0为否 */
    @Excel(name = "是否置顶 1为是 0为否")
    private Integer articleIsTop;

    /** 专辑id */
    private List<Long> albumIds;

    private Long userId;

    public List<Long> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<Long> albumIds) {
        this.albumIds = albumIds;
    }

    public List<String> getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(List<String> articleTag) {
        this.articleTag = articleTag;
    }
}
