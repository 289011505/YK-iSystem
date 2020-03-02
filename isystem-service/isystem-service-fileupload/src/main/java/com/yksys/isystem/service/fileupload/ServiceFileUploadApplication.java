package com.yksys.isystem.service.fileupload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-02-15 15:55
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.yksys.isystem.service.fileupload.mapper"})
public class ServiceFileUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFileUploadApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("1024MB");
        // 设置总上传数据大小
        factory.setMaxRequestSize("102400MB");
        return factory.createMultipartConfig();
    }
}