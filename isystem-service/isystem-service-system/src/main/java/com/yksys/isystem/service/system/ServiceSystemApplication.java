package com.yksys.isystem.service.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-01-13 11:43
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.yksys.isystem.service.system.mapper"})
@EnableFeignClients
public class ServiceSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSystemApplication.class, args);
    }

    /**
     * 开启@LoadBalanced与Ribbon集成
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}