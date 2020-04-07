package com.yksys.isystem.service.fileupload.service.feign.fallback;

import com.yksys.isystem.common.core.hystrix.Fallback;
import com.yksys.isystem.service.fileupload.service.feign.SystemUserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-02 14:11
 **/
@Component
public class SystemUserServiceFallback implements FallbackFactory<SystemUserService> {
    @Override
    public SystemUserService create(Throwable throwable) {
        return systemUserVo -> {
            throwable.printStackTrace();
            return Fallback.badGateway();
        };
    }
}