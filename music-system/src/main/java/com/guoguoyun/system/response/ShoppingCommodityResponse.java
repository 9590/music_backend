package com.guoguoyun.system.response;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 商城管理-商品管理对象 shopping_commodity
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Accessors(chain = true)
@Data
public class ShoppingCommodityResponse extends BaseEntity
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

    private String classifyName;

    /** 价格 */
    @Excel(name = "价格")
    private Double shopPrice;

    /** 商品内容 */
    @Excel(name = "商品内容")
    private String shopContent;

    /** 是否置顶 1为是 2为否 */
    @Excel(name = "是否置顶 1为是 2为否")
    private Integer shopIsTop;

    private String shopIsTopName;

    /** 购买链接 */
    @Excel(name = "购买链接")
    private String shopUrl;

    /** 轮播图 */
    @Excel(name = "轮播图")
    private List<ImgUrlVo> shopImgs;
    /** 商品编号 */
    @Excel(name = "商品编号")
    private String shopNumber;

    public List<ImgUrlVo> getShopImgs() {
        return shopImgs;
    }

    public void setShopImgs(List<ImgUrlVo> shopImgs) {
        this.shopImgs = shopImgs;
    }
}
