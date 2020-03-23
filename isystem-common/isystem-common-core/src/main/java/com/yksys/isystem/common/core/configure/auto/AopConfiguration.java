package com.yksys.isystem.common.core.configure.auto;

import com.yksys.isystem.common.core.aop.ActionLogAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: YK-iSystem
 * @description: aop公共配置类
 * @author: YuKai Fan
 * @create: 2020-03-23 11:16
 **/
@Configuration
public class AopConfiguration {
    @Bean
    public ActionLogAop actionLogAop() {
        return new ActionLogAop();
    }
}