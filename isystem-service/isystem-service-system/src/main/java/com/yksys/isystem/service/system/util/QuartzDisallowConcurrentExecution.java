package com.yksys.isystem.service.system.util;

import com.yksys.isystem.common.model.TaskInfo;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 * 
 * @author ruoyi
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, TaskInfo taskInfo) throws Exception {
        TaskInvokeUtil.invoke(taskInfo);
    }
}
