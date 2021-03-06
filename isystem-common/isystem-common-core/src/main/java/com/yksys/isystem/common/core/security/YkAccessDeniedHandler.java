package com.yksys.isystem.common.core.security;

import com.yksys.isystem.common.core.exception.ExceptionResult;
import com.yksys.isystem.common.core.exception.GlobalExceptionHandler;
import com.yksys.isystem.common.core.utils.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-16 16:18
 **/
public class YkAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ExceptionResult exceptionResult = globalExceptionHandler.handleOauth2WebResponseException(e);
        WebUtil.writeJson(httpServletResponse, exceptionResult);
    }
}