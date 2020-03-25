package com.yksys.isystem.service.system.service;

/**
 * @program: yk-isystem
 * @description: 系统任务
 * @author: YuKai Fan
 * @create: 2020-03-25 21:10
 **/
public interface SystemTaskService {
    /**
     * 从redis中添加操作日志
     */
    void addActionLogByRedis();
}