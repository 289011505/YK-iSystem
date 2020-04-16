package com.yksys.isystem.common.core.exception;

import lombok.Data;

/**
 * @program: YK-iSystem
 * @description: 参数异常
 * @author: YuKai Fan
 * @create: 2019-12-04 10:16
 **/
@Data
public class ParameterException extends RuntimeException {
    //返回码
    private int code;

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ParameterException() {

    }
}