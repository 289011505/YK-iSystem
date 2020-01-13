package com.yksys.isystem.service.generate.configuration;

import com.yksys.isystem.service.generate.interceptor.FrameworkFilter;
import com.yksys.isystem.service.generate.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-01-13 15:28
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }

    /**
     * 过滤器配置
     * @return
     */
    @Bean
    public FrameworkFilter frameworkFilter() {
        return new FrameworkFilter();
    }
}