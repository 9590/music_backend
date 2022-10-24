package com.guoguoyun.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum AdvShowStatus {

    YES(1,"是"),
    NO(2,"否"),
    UNKNOWN(6,"未知");

    private final Integer code;
    private final String info;

    AdvShowStatus(Integer code, String info)
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

    public static AdvShowStatus getEnumType(Integer key) {
        return Arrays.stream(AdvShowStatus.values())
                .filter(alarmGrade -> Objects.equals(alarmGrade.getCode(), key))
                .findFirst().orElse(AdvShowStatus.UNKNOWN);
    }
}
