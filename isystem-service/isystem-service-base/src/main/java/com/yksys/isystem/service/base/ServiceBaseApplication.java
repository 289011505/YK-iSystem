package com.yksys.isystem.service.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-13 11:10
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.yksys.isystem.service.base.mapper"})
public class ServiceBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBaseApplication.class, args);
    }
}