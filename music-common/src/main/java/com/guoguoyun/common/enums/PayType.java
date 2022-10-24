package com.guoguoyun.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum PayType {

    WX(1,"微信"),
    ALY(2,"支付宝"),
    UNKNOWN(6,"未知");

    private final Integer code;
    private final String info;

    PayType(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }

    public static PayType getEnumType(Integer key) {
        return Arrays.stream(PayType.values())
                .filter(alarmGrade -> Objects.equals(alarmGrade.getCode(), key))
                .findFirst().orElse(PayType.UNKNOWN);
    }
}
