package com.yksys.isystem.common.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-04 10:20
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ExceptionResult handleParameterException(Exception e) {
        return ExceptionResult.create(902, e.getMessage());
    }

    /**
     * oauth2认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ExceptionResult handleOauth2WebResponseException(Exception e) {
        return ExceptionResult.create(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}