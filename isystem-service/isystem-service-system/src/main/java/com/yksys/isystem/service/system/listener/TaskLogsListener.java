package com.yksys.isystem.service.system.listener;

import com.yksys.isystem.common.core.utils.SpringContextUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.common.pojo.TaskLog;
import com.yksys.isystem.service.system.service.TaskLogService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @program: yk-isystem
 * @description: 任务执行日志监听
 * @author: YuKai Fan
 * @create: 2020-03-24 21:50
 **/
public class TaskLogsListener implements JobListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String NAME = "TaskLogsListener";

    private TaskLogService taskLogService;

    /**
     * 线程本地变量
     */
    private static ThreadLocal<LocalDateTime> threadLocal = new ThreadLocal<>();

    public TaskLogsListener(TaskLogService taskLogService) {
        this.taskLogService = taskLogService;
    }

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * 任务执行前
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        threadLocal.set(LocalDateTime.now());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    /**
     * 任务执行完成或异常
     * @param context
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        LocalDateTime startTime = threadLocal.get();
        threadLocal.remove();

        final TaskLog taskLog = new TaskLog();
        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        String jobGroup = jobDetail.getKey().getGroup();
        String jobClassName = jobDetail.getJobClass().getName();

        taskLog.setJobName(jobName);
        taskLog.setJobClassName(jobClassName);
        taskLog.setJobGroupName(jobGroup);
        taskLog.setStartTime(TimeUtil.parseTime(startTime));
        LocalDateTime endTime = LocalDateTime.now();
        taskLog.setEndTime(TimeUtil.parseTime(endTime));
        Duration duration = Duration.between(startTime, endTime);
        long runMs = duration.toMillis();
        taskLog.setJobMessage(jobName + "总共耗时: " + runMs + "毫秒");
        if (e != null) {
            taskLog.setStatus(2);
            taskLog.setExceptionInfo(e.getMessage());

            //发送邮件 TODO
        } else {
            taskLog.setStatus(1);
        }

        //存入数据库
        taskLogService.addTaskLog(taskLog);

    }
}