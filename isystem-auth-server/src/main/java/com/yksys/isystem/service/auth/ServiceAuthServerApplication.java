package com.yksys.isystem.service.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-10 16:49
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = {"com.yksys.isystem.service.auth.mapper"})
@EnableSwagger2
public class ServiceAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthServerApplication.class, args);
    }
}