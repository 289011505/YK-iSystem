package com.yksys.isystem.service.generate.interceptor;

import com.yksys.isystem.service.generate.configuration.PathYml;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description: 通过此过滤器初始化路径或者其他配置
 * @author: YuKai Fan
 * @create: 2020-01-13 15:22
 **/
public class FrameworkFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!PathYml.isInit()) {
            //初始化路径配置
            PathYml.init((HttpServletRequest) servletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}