package com.yksys.isystem.web.admin.service.fallback;

import com.yksys.isystem.common.core.hystrix.Fallback;
import com.yksys.isystem.web.admin.service.FileUploadService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-03 09:03
 **/
@Component
public class FileUploadServiceFallback implements FallbackFactory<FileUploadService> {
    @Override
    public FileUploadService create(Throwable throwable) {
        throwable.printStackTrace();
        return request -> Fallback.badGateway();
    }
}