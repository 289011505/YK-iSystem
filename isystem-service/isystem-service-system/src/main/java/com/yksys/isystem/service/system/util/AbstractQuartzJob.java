package com.yksys.isystem.service.system.util;

import com.yksys.isystem.common.core.constants.ScheduleConstant;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.model.TaskInfo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @program: YK-iSystem
 * @description: quartz抽象类
 * @author: YuKai Fan
 * @create: 2020-03-24 09:34
 **/
public abstract class AbstractQuartzJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(AbstractQuartzJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        TaskInfo taskInfo = (TaskInfo) context.getMergedJobDataMap().get(ScheduleConstant.TASK_PROPERTIES);
        if (taskInfo != null) {
            try {
                doExecute(context, taskInfo);
            } catch (Exception e) {
                logger.error("任务执行异常: ", e);
                throw new ParameterException("任务执行异常");
            }
        } else {
            logger.error("参数错误: taskInfo={}", taskInfo);
            throw new ParameterException("参数错误");
        }
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param taskInfo 任务信息
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, TaskInfo taskInfo) throws Exception;
}