package com.guoguoyun.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ShopIsTopType {


    YES(1,"是"),
    NO(0,"否"),
    UNKNOWN(6,"未知");

    private final Integer code;
    private final String info;

    ShopIsTopType(Integer code, String info)
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

    public static ShopIsTopType getEnumType(Integer key) {
        return Arrays.stream(ShopIsTopType.values())
                .filter(alarmGrade -> Objects.equals(alarmGrade.getCode(), key))
                .findFirst().orElse(ShopIsTopType.UNKNOWN);
    }
}
