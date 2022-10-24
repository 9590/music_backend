package com.guoguoyun.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 运营管理-订单管理对象 operation_order
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
@Accessors(chain = true)
@Data
public class OperationOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 支付编号 */
    @Excel(name = "支付编号")
    private String payNumber;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Integer payType;

    /** 购买类型 */
    @Excel(name = "购买类型")
    private Long shopType;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String shopName;

    /** 商品id */
    @Excel(name = "商品id")
    private Long shopId;

    /** 金额 */
    @Excel(name = "金额")
    private Double shopPrice;

    /** 作者 */
    @Excel(name = "作者")
    private String writer;

    /** 购买者 */
    @Excel(name = "购买者")
    private Long userId;

    /**
     * 支付状态 1为未支付 2为支付成功 3为支付失败
     */
    private Integer payState;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 1为专辑  2为单曲
     */
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date endTime;

    @TableField(exist = false)
    private Album album;

    @TableField(exist = false)
    private AlbumSong albumSong;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPayNumber(String payNumber) 
    {
        this.payNumber = payNumber;
    }

    public String getPayNumber() 
    {
        return payNumber;
    }
    public void setPayType(Integer payType) 
    {
        this.payType = payType;
    }

    public Integer getPayType() 
    {
        return payType;
    }
    public void setShopType(Long shopType) 
    {
        this.shopType = shopType;
    }

    public Long getShopType() 
    {
        return shopType;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }
    public void setShopPrice(Double shopPrice) 
    {
        this.shopPrice = shopPrice;
    }

    public Double getShopPrice() 
    {
        return shopPrice;
    }
    public void setWriter(String writer) 
    {
        this.writer = writer;
    }

    public String getWriter() 
    {
        return writer;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
            .append("payNumber", getPayNumber())
            .append("payType", getPayType())
            .append("shopType", getShopType())
            .append("shopName", getShopName())
            .append("shopId", getShopId())
            .append("shopPrice", getShopPrice())
            .append("writer", getWriter())
            .append("userId", getUserId())
            .toString();
    }
}
