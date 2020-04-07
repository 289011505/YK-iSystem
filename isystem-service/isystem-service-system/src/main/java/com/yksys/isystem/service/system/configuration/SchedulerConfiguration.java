package com.yksys.isystem.service.system.configuration;

import com.yksys.isystem.service.system.listener.TaskLogsListener;
import com.yksys.isystem.service.system.service.TaskLogService;
import com.yksys.isystem.service.system.service.feign.EmailService;
import com.yksys.isystem.service.system.task.TaskTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @program: YK-iSystem
 * @description: 项目启动时执行 自动重启定时任务
 * @author: YuKai Fan
 * @create: 2020-03-24 11:27
 **/
@Configuration
public class SchedulerConfiguration implements SchedulerFactoryBeanCustomizer {
    @Autowired
    private TaskLogsListener taskLogsListener;

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        //延时5秒启动
        schedulerFactoryBean.setStartupDelay(5);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //任务执行日志监听
        schedulerFactoryBean.setGlobalJobListeners(taskLogsListener);
    }

    @Bean
    public TaskLogsListener taskLogsListener(TaskLogService taskLogService, EmailService emailService) {
        return new TaskLogsListener(taskLogService, emailService);
    }
}