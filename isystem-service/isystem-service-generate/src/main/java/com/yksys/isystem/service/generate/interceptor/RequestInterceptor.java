package com.yksys.isystem.service.generate.interceptor;

import com.yksys.isystem.service.generate.configuration.PathYml;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: YK-iSystem
 * @description: 请求拦截器
 * @author: YuKai Fan
 * @create: 2020-01-13 15:26
 **/
public class RequestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String projectPath = PathYml.getRootPath() + PathYml.getContextPath();
        request.setAttribute("projectPath", projectPath);
        return super.preHandle(request, response, handler);
    }
}