package com.yksys.isystem.service.system.service.feign.fallback;

import com.yksys.isystem.service.system.service.feign.EmailService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-07 16:52
 **/
@Component
public class EmailServiceFallback implements FallbackFactory<EmailService> {
    @Override
    public EmailService create(Throwable throwable) {
        return null;
    }
}