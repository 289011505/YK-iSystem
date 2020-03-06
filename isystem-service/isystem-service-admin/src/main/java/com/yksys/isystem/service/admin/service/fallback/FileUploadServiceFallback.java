package com.yksys.isystem.service.admin.service.fallback;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.hystrix.Fallback;
import com.yksys.isystem.common.vo.BucketVo;
import com.yksys.isystem.service.admin.service.feign.FileUploadService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-06 10:32
 **/
@Component
public class FileUploadServiceFallback implements FallbackFactory<FileUploadService> {
    @Override
    public FileUploadService create(Throwable throwable) {
        return new FileUploadService() {
            @Override
            public Result uploadOSS(MultipartFile file, String bucketName, String storageType, String dataRedundancyType, String cannedACL) throws IOException {
                return Fallback.badGateway();
            }

            @Override
            public Result uploadOSS(MultipartFile file, String bucketName, String storageType, String dataRedundancyType, String cannedACL, String ownId) throws IOException {
                return Fallback.badGateway();
            }

            @Override
            public Result uploadOSSByFile(MultipartFile file, BucketVo bucketVo) throws IOException {
                return Fallback.badGateway();
            }
        };
    }
}