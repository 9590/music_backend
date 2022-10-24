package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商城管理-商品管理对象 shopping_commodity
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Accessors(chain = true)
@Data
public class ShoppingCommodityParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class,updateTop.class})
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @NotBlank(message = "商品名称不能为空", groups = {BaseParam.edit.class,add.class})
    private String shopName;

    /** 分类id */
    @Excel(name = "分类id")
    @NotNull(message = "分类id不能为空", groups = {BaseParam.edit.class,add.class})
    private Long classifyId;

    /** 价格 */
    @Excel(name = "价格")
    @NotNull(message = "价格不能为空", groups = {BaseParam.edit.class,add.class})
    private Double shopPrice;

    /** 商品内容 */
    @Excel(name = "商品内容")
    @NotBlank(message = "商品内容不能为空", groups = {BaseParam.edit.class,add.class})
    private String shopContent;

    /** 是否置顶 1为是 2为否 */
    @Excel(name = "是否置顶 1为是 0为否")
    @NotNull(message = "是否置顶不能为空", groups = {BaseParam.edit.class,add.class})
    private Long shopIsTop;

    /** 购买链接 */
    @Excel(name = "购买链接")
    @NotBlank(message = "购买链接不能为空", groups = {BaseParam.edit.class,add.class})
    private String shopUrl;

    /** 轮播图 */
    @Excel(name = "轮播图")
//    @NotBlank(message = "轮播图不能为空", groups = {BaseParam.edit.class,add.class})
    private List<ImgUrlVo> shopImgs;



    /** 商品编号 */
    @Excel(name = "商品编号")
    @NotBlank(message = "商品编号不能为空", groups = {BaseParam.edit.class,add.class})
    private String shopNumber;

    private Date startTime;

    private Date endTime;

    public List<ImgUrlVo> getShopImgs() {
        return shopImgs;
    }

    public void setShopImgs(List<ImgUrlVo> shopImgs) {
        this.shopImgs = shopImgs;
    }
}
