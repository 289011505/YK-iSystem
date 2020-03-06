package com.yksys.isystem.service.admin.service.feign;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.Attachment;
import com.yksys.isystem.common.vo.BucketVo;
import com.yksys.isystem.service.admin.service.fallback.FileUploadServiceFallback;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-06 10:25
 **/
@FeignClient(value = "isystem-service-fileupload", fallbackFactory = FileUploadServiceFallback.class)
public interface FileUploadService {

    /**
     * 文件上传oss
     * @param file
     * @param bucketName
     * @param storageType
     * @param dataRedundancyType
     * @param cannedACL
     * @return
     * @throws IOException
     */
    @RequestLine("POST /api/oss/uploadOSS")
    Result uploadOSS(@Param("file") MultipartFile file, @Param("bucketName") String bucketName,
                     @Param("storageType") String storageType, @Param("dataRedundancyType") String dataRedundancyType,
                     @Param("cannedACL") String cannedACL) throws IOException;

    /**
     * 文件上传oss
     * @param file
     * @param bucketName
     * @param storageType
     * @param dataRedundancyType
     * @param cannedACL
     * @param ownId
     * @return
     * @throws IOException
     */
    @RequestLine("POST /api/oss/uploadOSSByOwnId")
    Result uploadOSS(@Param("file") MultipartFile file, @Param("bucketName") String bucketName,
                     @Param("storageType") String storageType, @Param("dataRedundancyType") String dataRedundancyType,
                     @Param("cannedACL") String cannedACL, @Param("ownId") String ownId) throws IOException;

    /**
     * 文件上传oss
     * @param file
     * @param bucketVo
     * @return
     * @throws IOException
     */
    @RequestLine("POST /api/oss/uploadOSSByFile")
    Result<Attachment> uploadOSSByFile(@Param("file") MultipartFile file, @Param("bucketVo") BucketVo bucketVo) throws IOException;
}