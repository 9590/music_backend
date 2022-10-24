package com.guoguoyun.system.response;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.params.BaseParam;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class ContentArticleResponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String articleTitile;

    /** 文章类型 */
    @Excel(name = "文章类型")
    private Long articleType;
    private String articleTypeName;

    /** 标签 */
    @Excel(name = "标签")
    private List<String> articleTag;

    /** 图片链接 */
    @Excel(name = "图片链接")
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

    private String articleIsTopName;

    /** 专辑id */
    private List<Album> albumIds;

    private String albumNames;

    /** 阅读数 */
    @Excel(name = "阅读数")
    private Long readNum;

    public List<String> getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(List<String> articleTag) {
        this.articleTag = articleTag;
    }
}
