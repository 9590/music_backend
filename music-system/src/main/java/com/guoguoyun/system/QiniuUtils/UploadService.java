package com.guoguoyun.system.QiniuUtils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZCL
 * @date 2022/4/21 22:49
 */
public interface UploadService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
