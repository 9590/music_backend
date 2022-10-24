package com.guoguoyun.system.params.app;


import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import lombok.Data;

import java.util.List;

@Data
public class UserContributeAppLikeParams {

    //投稿或动态id
    private Long contributeId;

    //用户id
    private Long userId;

    //1为点赞  2为取消
    private Integer type;

}
