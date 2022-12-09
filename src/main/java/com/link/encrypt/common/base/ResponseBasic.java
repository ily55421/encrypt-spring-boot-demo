package com.link.encrypt.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lin 2022/12/9 22:55
 * 统一返回数据实体
 */
@Data
public class ResponseBasic<T> implements Serializable {
    private int code;
    private T data;
    private String msg;
    private int salt;
    private String signature;
    public ResponseBasic<T> fail(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseBasic<T> success(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }
}