package com.yksys.isystem.web.admin.service;

import com.yksys.isystem.common.core.configure.auto.FeignAutoConfiguration;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.web.admin.configuration.MultipartSupportConfig;
import com.yksys.isystem.web.admin.service.fallback.FileUploadServiceFallback;
import feign.RequestLine;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description: 文件上传服务
 * @author: YuKai Fan
 * @create: 2020-03-03 09:02
 **/
@Component
@FeignClient(value = "isystem-service-fileupload", fallbackFactory = FileUploadServiceFallback.class, configuration = MultipartSupportConfig.class)
public interface FileUploadService {

    /**
     * 用户上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/api/fileupload/uploadByFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    Result upload(@RequestPart("file") MultipartFile file) throws IOException;
}