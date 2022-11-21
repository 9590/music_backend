package com.guoguoyun.system.QiniuUtils;

import lombok.Data;

/**
 * 上传文件参数
 *
 * @author ZCL
 * @date 2022-11-18
 */
@Data
public class UpdateParams {

    private String fileName;
    private String updatePath;
    private String fileType;
}
