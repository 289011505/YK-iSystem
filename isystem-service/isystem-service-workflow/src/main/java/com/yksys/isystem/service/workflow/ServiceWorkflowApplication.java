package com.yksys.isystem.service.workflow;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-12 15:47
 **/
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(exclude={SecurityAutoConfiguration.class, OAuth2AutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@MapperScan(basePackages = {"com.yksys.isystem.service.workflow.mapper"})
public class ServiceWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceWorkflowApplication.class, args);
    }
}