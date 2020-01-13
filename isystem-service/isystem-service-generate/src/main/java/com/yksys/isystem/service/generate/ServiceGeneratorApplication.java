package com.yksys.isystem.service.generate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-01-13 11:43
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.yksys.isystem.service.generate.mapper"})
public class ServiceGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGeneratorApplication.class, args);
    }
}