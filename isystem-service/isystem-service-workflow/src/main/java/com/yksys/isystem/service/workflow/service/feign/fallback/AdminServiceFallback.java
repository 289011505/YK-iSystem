package com.yksys.isystem.service.workflow.service.feign.fallback;

import com.yksys.isystem.service.workflow.service.feign.AdminService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-14 11:35
 **/
@Component
public class AdminServiceFallback implements FallbackFactory<AdminService> {
    @Override
    public AdminService create(Throwable throwable) {
        return null;
    }
}