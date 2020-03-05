package com.yksys.isystem.service.fileupload.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description: 阿里云oss配置
 * @author: YuKai Fan
 * @create: 2020-03-05 08:59
 **/
@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class AliyunOSSProperties {
    //地域节点
    private String endPoint;
    //用户keyId
    private String accessKeyId;
    //用户keySecret
    private String accessKeySecret;
}