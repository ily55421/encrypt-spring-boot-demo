package com.link.encrypt.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lin 2022/12/9 22:46
 * 结果异常处理
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultException extends RuntimeException implements Serializable {
    private Integer code;
    private String msg;
}