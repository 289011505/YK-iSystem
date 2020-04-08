package com.yksys.isystem.service.message;

import com.yksys.isystem.service.message.service.EmailConfigService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-30 10:47
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.yksys.isystem.service.message.mapper"})
@EnableSwagger2
public class ServiceMessageApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMessageApplication.class, args);
    }


    @Autowired
    private EmailConfigService emailConfigService;

    @Override
    public void run(String... args) throws Exception {
        emailConfigService.loadCacheConfig();
    }
}