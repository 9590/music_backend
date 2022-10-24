package com.guoguoyun.system.domain;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * 商城管理-商品管理对象 shopping_commodity
 *
 * @author ruoyi
 * @date 2022-02-14
 */
@Accessors(chain = true)
@Data
public class ShoppingCommodity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String shopName;

    /** 分类id */
    @Excel(name = "分类id")
    private Long classifyId;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal shopPrice;

    /** 商品内容 */
    @Excel(name = "商品内容")
    private String shopContent;

    /** 是否置顶 1为是 0为否 */
    @Excel(name = "是否置顶 1为是 0为否")
    private Integer shopIsTop;

    /** 购买链接 */
    @Excel(name = "购买链接")
    private String shopUrl;

    /** 轮播图 */
    @Excel(name = "轮播图")
    private String shopImg;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String shopNumber;

    @Excel(name = "图片链接")
    @TableField(exist = false)
    private List<ImgUrlVo> imgUrls;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getShopName()
    {
        return shopName;
    }
    public void setClassifyId(Long classifyId)
    {
        this.classifyId = classifyId;
    }

    public Long getClassifyId()
    {
        return classifyId;
    }
    public void setShopPrice(BigDecimal shopPrice)
    {
        this.shopPrice = shopPrice;
    }

    public BigDecimal getShopPrice()
    {
        return shopPrice;
    }
    public void setShopContent(String shopContent)
    {
        this.shopContent = shopContent;
    }

    public String getShopContent()
    {
        return shopContent;
    }
    public void setShopIsTop(Integer shopIsTop)
    {
        this.shopIsTop = shopIsTop;
    }

    public Integer getShopIsTop()
    {
        return shopIsTop;
    }
    public void setShopUrl(String shopUrl)
    {
        this.shopUrl = shopUrl;
    }

    public String getShopUrl()
    {
        return shopUrl;
    }
    public void setShopImg(String shopImg)
    {
        this.shopImg = shopImg;
    }

    public String getShopImg()
    {
        return shopImg;
    }
    public void setShopNumber(String shopNumber)
    {
        this.shopNumber = shopNumber;
    }

    public String getShopNumber()
    {
        return shopNumber;
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
                .append("shopName", getShopName())
                .append("classifyId", getClassifyId())
                .append("shopPrice", getShopPrice())
                .append("shopContent", getShopContent())
                .append("shopIsTop", getShopIsTop())
                .append("shopUrl", getShopUrl())
                .append("shopImg", getShopImg())
                .append("shopNumber", getShopNumber())
                .toString();
    }
}
