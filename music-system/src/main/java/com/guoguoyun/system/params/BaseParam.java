package com.guoguoyun.system.params;

/**
 * @Auther:
 * @Date: 2020/10/28 0028
 * @Description:
 */

import com.guoguoyun.common.core.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseParam extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 参数校验分组：增加
     */
    public @interface add {
    }

    /**
     * 参数校验分组：编辑
     */
    public @interface edit {
    }


    /**
     * 参数校验分组：置顶
     */
    public @interface updateTop {
    }

    /**
     * 参数校验分组：列表
     */
    public @interface list {
    }

    /**
     * 参数校验分组：添加艺术家
     */
    public @interface saveActor {
    }

    /**
     * 参数校验分组：登录
     */
    public @interface login {
    }
}

