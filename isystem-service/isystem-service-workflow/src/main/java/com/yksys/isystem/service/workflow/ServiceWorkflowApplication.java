package com.yksys.isystem.service.workflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-12 15:47
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"com.yksys.isystem.service.workflow.mapper"})
public class ServiceWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceWorkflowApplication.class, args);
    }
}