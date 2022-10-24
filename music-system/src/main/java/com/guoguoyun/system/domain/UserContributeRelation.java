package com.guoguoyun.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserContributeRelation {

    /** 主键 */

    @TableField(exist = false)
    private  Long Id;

    private Long userId;

    private Long contributeId;

    /**
     *  1为稿子  2为动态
     */
    private Integer type;

    /**
     * 1为阅读
     * 2为点赞
     */
    private Integer state;

}
