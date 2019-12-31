package com.yksys.isystem.zuul.service.fallback;

import com.yksys.isystem.common.core.hystrix.Fallback;
import com.yksys.isystem.zuul.service.AuthorityService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-27 10:10
 **/
@Component
public class AuthorityServiceFallback implements FallbackFactory<AuthorityService> {
    @Override
    public AuthorityService create(Throwable throwable) {
        return () -> Fallback.badGateway();
    }
}