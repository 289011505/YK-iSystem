package com.yksys.isystem.service.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-06 09:16
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"com.yksys.isystem.service.business.mapper"})
public class ServiceBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBusinessApplication.class, args);
    }
}