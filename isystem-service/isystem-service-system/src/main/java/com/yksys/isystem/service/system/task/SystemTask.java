package com.yksys.isystem.service.system.task;

import com.yksys.isystem.service.system.service.SystemTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description: 系统任务
 * @author: YuKai Fan
 * @create: 2020-03-27 08:42
 **/
@Component
public class SystemTask {
    @Autowired
    private SystemTaskService systemTaskService;

    /**
     * 每天23:59 从redis更新操作日志到数据库
     */
    public void updateActionLogByRedis() {
        systemTaskService.addActionLogByRedis();
    }

    /**
     * 每40秒 更新热点新闻到redis
     */
    public void updateHotNewsToRedis() {
        systemTaskService.updateHotNewsToRedis();
    }
}