package com.yksys.isystem.service.admin.configuration;

import feign.Contract;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-06 09:36
 **/
@Configuration
public class FeignConfiguration {
    // 启用Fegin自定义注解 如@RequestLine @Param
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }


    //feign 实现多pojo传输与MultipartFile上传 编码器，需配合开启feign自带注解使用
    @Bean
    public Encoder feignSpringFormEncoder(){
        return new FeignSpringFormEncoder();
    }
}