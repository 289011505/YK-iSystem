package com.yksys.isystem.service.system.util;

import com.yksys.isystem.common.model.TaskInfo;
import org.quartz.JobExecutionContext;

/**
 * @program: YK-iSystem
 * @description: 定时任务处理（允许并发执行）
 * @author: YuKai Fan
 * @create: 2020-03-24 09:33
 **/
public class QuartzJobExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, TaskInfo taskInfo) throws Exception {
        TaskInvokeUtil.invoke(taskInfo);
    }
}