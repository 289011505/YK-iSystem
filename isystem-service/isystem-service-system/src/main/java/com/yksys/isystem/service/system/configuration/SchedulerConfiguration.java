package com.yksys.isystem.service.system.configuration;

import com.yksys.isystem.service.system.task.TaskTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-24 11:27
 **/
@Configuration
public class SchedulerConfiguration {

    @Bean
    public TaskTest taskTest() {
        return new TaskTest();
    }
}