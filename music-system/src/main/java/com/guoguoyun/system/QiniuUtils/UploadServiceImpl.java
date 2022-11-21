package com.guoguoyun.system.QiniuUtils;

import com.google.gson.Gson;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author ZCL
 * @date 2022/4/21 22:50
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("oss.accessKey")
    private String accessKey;
    @Value("oss.secretKey")
    private String secretKey;
    @Value("oss.bucket")
    private String bucket;
    @Value("oss.domain")
    private String domain;

    @Override
    public String upload(MultipartFile file,UpdateParams updateParams) {
        //判断文件类型
        if (file.isEmpty()) {
            AjaxResult.error("文件上传失败！");
        }
        String originalFileName = file.getOriginalFilename();
        //上传文件类型
        String fileType = file.getContentType();
        //对原始文件名进行判断
        assert originalFileName != null;
        assert fileType != null;
        if (!originalFileName.endsWith(".png") && !originalFileName.endsWith(".jpg") &&
                !originalFileName.endsWith(".flac") && !originalFileName.endsWith(".mp3")) {
            //TODO 抛出错误，此处只允许.png
            AjaxResult.error("只可以上传指定格式的文件！");
        }

        String path = PathUtils.generateFilePath(originalFileName,updateParams);

        return uploadOss(file, path);
    }

    private String uploadOss(MultipartFile file, String path) {
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = path;
        try {
            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return domain + key;
            } catch (QiniuException e) {
                Response r = e.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException e2) {

                }
            }
        } catch (Exception e) {

        }
        return "www";
    }
}

