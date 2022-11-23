package com.guoguoyun.system.QiniuUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ZCL
 * @date 2022/4/21 22:46
 */
public class PathUtils {
    public static String generateFilePath(String fileName,UpdateParams updateParams) {

        //根据日期生成路径 2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");


        int index = fileName.lastIndexOf(".");

        String type = fileName.substring(index);

        /*
         * album images
         */
        if (updateParams.getFileType().contains(FileConstants.JPEG) ||
                updateParams.getFileType().contains(FileConstants.JPG) ||
                updateParams.getFileType().contains(FileConstants.PNG) ){
            if (FileConstants.ONE.equals(updateParams.getUpdatePath())){
                return new StringBuilder()
                        .append(FileConstants.JPG_FILE_PATH)
                        .append(updateParams.getFileName())
                        .append(type)
                        .toString();
            }
        }
        /*
         * album mp3
         */
        if (updateParams.getFileType().contains(FileConstants.MP3) ||
                updateParams.getFileType().contains(FileConstants.FLAC)){
            if (FileConstants.TWO.equals(updateParams.getUpdatePath())){
                return new StringBuilder()
                        .append(FileConstants.MP3_FILE_PATH)
                        .append(updateParams.getFileName())
                        .append(type).toString();
            }
        }
        /*
         * advert images
         */
        if (FileConstants.THERE.equals(updateParams.getUpdatePath())){
            return new StringBuilder()
                    .append(FileConstants.ADVERT_IMAGE_PATH)
                    .append(uuid)
                    .append(type).toString();
            }

        /*
         * it is not all  save default path
         */
         return new StringBuilder()
                .append(FileConstants.DEFAULT_FILE_PATH)
                .append(uuid)
                .append(type)
                .toString();

    }
}

