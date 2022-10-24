package com.guoguoyun.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum CheckState {

    TO_AUDIT(1,"待审核"),
    HAS_REFUSED(2,"已拒绝"),
    PASS(3,"已通过"),
    STOP(4,"已停用"),
    UNKNOWN(6,"未知");

    private final Integer code;
    private final String info;

    CheckState(Integer code, String info)
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

    public static CheckState getEnumType(Integer key) {
        return Arrays.stream(CheckState.values())
                .filter(alarmGrade -> Objects.equals(alarmGrade.getCode(), key))
                .findFirst().orElse(CheckState.UNKNOWN);
    }
}
