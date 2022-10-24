package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商城管理-分类管理对象 classify
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@Accessors(chain = true)
@Data
public class ClassifyParams extends BaseParam
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "id不能为空", groups = {BaseParam.edit.class})
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    @NotBlank(message = "id不能为空", groups = {BaseParam.edit.class,add.class})
    private String classifyName;

    /** 1为商品分类 2为专辑分类 */
    @Excel(name = "1为商品分类 2为专辑分类")
    @NotNull(message = "id不能为空", groups = {add.class,list.class})
    private Integer classifyType;

    /** 分类编号 */
    @Excel(name = "1为商品分类 2为专辑分类")
    @NotBlank(message = "分类编号不能为空", groups = {add.class,edit.class})
    private String classifyNumber;

    /** 父类id */
    @Excel(name = "父类id")
    private Long classifyParent;

}
