package com.guoguoyun.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum AdvertisingLocation {

    WELCOMEHOME(1,"欢迎首页"),

    FLASHVIEW(2,"首页轮播图"),

    SHOPFLASHVIEW(3,"商城轮播图"),

    ENTERTAINMENT(4,"乐讯"),

    FAIRYLAND(5,"发烧乐园"),

    UNKNOWN(6,"未知")
    ;

    private final Integer code;
    private final String info;

    AdvertisingLocation(Integer code, String info)
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

    public static AdvertisingLocation getEnumType(Integer key) {
        return Arrays.stream(AdvertisingLocation.values())
                .filter(alarmGrade -> Objects.equals(alarmGrade.getCode(), key))
                .findFirst().orElse(AdvertisingLocation.UNKNOWN);
    }
}
