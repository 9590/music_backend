package com.guoguoyun.framework.web.exception;

public class APIException extends RuntimeException {
    private int code;
    private String msg;

    // 手动设置异常
    public APIException(int code, String message) {
        // message用于用户设置抛出错误详情，例如：当前价格-5，小于0
        super(message);
        // 状态码
        this.code = code;
        // 状态码配套的msg
        this.msg = message;
    }

    public APIException(String message) {
        // message用于用户设置抛出错误详情，例如：当前价格-5，小于0
        super(message);
    }

    public APIException(int resultCode) {
        super();
        this.code = resultCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}