package com.link.encrypt.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * @author lin 2022/12/9 22:46
 * 用户类型枚举类
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserType {
    VIP("VIP用户"),
    COMMON("普通用户");
    private String code;
    private String type;

    UserType(String type) {
        this.code = name();
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + name() + '\"' +
                ", \"type\":\"" + type + '\"' +
                '}';
    }
}