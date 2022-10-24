package com.guoguoyun.framework.web.exception;


import com.guoguoyun.common.constant.ResultCode;

public class ExceptionCast {
    /**
     * 此静态方法为了方便抛出自定义异常，统一异常处理，主要抛出方法
     * @param resultCode
     */
    public static void cast(ResultCode resultCode){
        throw new APIException(resultCode.code(),resultCode.message());
    }

    public static void cast(ResultCode resultCode, String message){
        throw new APIException(resultCode.code(),resultCode.message());
    }
}