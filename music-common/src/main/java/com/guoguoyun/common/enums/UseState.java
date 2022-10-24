package com.guoguoyun.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum UseState {


    UNUSED(1,"未使用"),
    USED(2,"已使用"),
    PASTDUE(3,"已过期"),
    UNKNOWN(6,"未知");

    private final Integer code;
    private final String info;

    UseState(Integer code, String info)
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

    public static UseState getEnumType(Integer key) {
        return Arrays.stream(UseState.values())
                .filter(alarmGrade -> Objects.equals(alarmGrade.getCode(), key))
                .findFirst().orElse(UseState.UNKNOWN);
    }
}
