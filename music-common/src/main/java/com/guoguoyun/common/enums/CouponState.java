package com.guoguoyun.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum CouponState {


    YES(1,"在启用"),
    NO(2,"已禁用"),
    UNKNOWN(6,"未知");

    private final Integer code;
    private final String info;

    CouponState(Integer code, String info)
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

    public static CouponState getEnumType(Integer key) {
        return Arrays.stream(CouponState.values())
                .filter(alarmGrade -> Objects.equals(alarmGrade.getCode(), key))
                .findFirst().orElse(CouponState.UNKNOWN);
    }
}
