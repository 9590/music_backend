package com.guoguoyun.system.params.app;

import com.guoguoyun.system.params.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AppSearchParams extends BaseParam {

    /**
     * 搜索词
     */
    @NotBlank(message = "搜索词不能为空", groups = {list.class})
    private String searchWord;

    /**
     * 用户id
     */

    private Long userId;
}
