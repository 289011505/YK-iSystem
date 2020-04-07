package com.yksys.isystem.common.core.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: YK-iSystem
 * @description: 自定义swagger配置
 * @author: YuKai Fan
 * @create: 2020-04-07 14:10
 **/
@Data
@ConfigurationProperties(prefix = "isystem.swagger2")
public class YkSwaggerProperties {
    /**
     * 是否启用swagger
     */
    private boolean enabled;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 版本
     */
    private String version;

}