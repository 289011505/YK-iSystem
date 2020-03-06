package com.yksys.isystem.service.business.service.feign;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.vo.BucketVo;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-06 09:16
 **/
@FeignClient(value = "isystem-service-fileupload")
public interface FileUploadService {

    @RequestLine("POST /api/oss/uploadOSS")
    Result uploadOSS(@Param("file") MultipartFile file, @Param("bucketName") String bucketName,
                     @Param("storageType") String storageType, @Param("dataRedundancyType") String dataRedundancyType,
                     @Param("cannedACL") String cannedACL, @Param("ownId") String ownId) throws IOException;

    @RequestLine("POST /api/oss/uploadOSSByOwnId")
    Result uploadOSS(@Param("file") MultipartFile file, @Param("bucketName") String bucketName,
                     @Param("storageType") String storageType, @Param("dataRedundancyType") String dataRedundancyType,
                     @Param("cannedACL") String cannedACL) throws IOException;
}