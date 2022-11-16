package com.guoguoyun.system.QiniuUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ZCL
 * @date 2022/4/21 22:46
 */
public class PathUtils {
    public static String generateFilePath(String fileName,String fileType) {

        //根据日期生成路径 2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());

        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        int index = fileName.lastIndexOf(".");

        String type = fileName.substring(index);

        if (fileType.contains(FileConstants.JPEG) ||
                fileType.contains(FileConstants.JPG) ||
                fileType.contains(FileConstants.PNG) ){
            return new StringBuilder()
                    .append(FileConstants.JPG_FILE_PATH)
                    .append(fileName.substring(0,index))
                    .append(type)
                    .toString();
        }

        if (fileType.contains(FileConstants.MP3) ||
                fileType.contains(FileConstants.FLAC)){
            return new StringBuilder()
                    .append(FileConstants.MP3_FILE_PATH)
                    .append(fileName.substring(0,index))
                    .append(type).toString();
        }

        return null;

    }
}

