package com.yksys.isystem.service.system.util;

import com.google.common.collect.Sets;
import com.yksys.isystem.common.core.constants.ScheduleConstant;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.common.model.TaskInfo;
import org.quartz.*;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.Set;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-24 09:16
 **/
public class ScheduleUtil {
    /**
     * 验证任务是否存在
     *
     * @param jobName
     * @param jobGroup
     * @param scheduler
     * @return
     */
    public static boolean checkExists(Scheduler scheduler, String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }

    /**
     * 获取Trigger
     *
     * @param taskInfo
     * @param triggerKey
     * @return
     * @throws ParseException
     */
    public static Trigger getTrigger(TaskInfo taskInfo, TriggerKey triggerKey) throws ParseException {
        Trigger trigger;
        if (ScheduleConstant.JOB_TRIGGER_SIMPLE.equals(taskInfo.getJobTrigger())) {
            //简单调度
            trigger = TriggerBuilder
                    .newTrigger().withIdentity(triggerKey)
                    .startAt(TimeUtil.parseTimeToDate(taskInfo.getStartTime()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInMilliseconds(taskInfo.getRepeatInterval()).withRepeatCount(taskInfo.getRepeatCount()))
                    .endAt(TimeUtil.parseTimeToDate(taskInfo.getEndTime())).build();
        } else {
            //定时调度
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(taskInfo.getCronExpression());
            //根据定时策略构建调度器
            scheduleBuilder = handleCronScheduleMisfirePolicy(scheduleBuilder, taskInfo.getMisfirePolicy());
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
        }
        return trigger;
    }

    /**
     * 根据定时策略获取构建器
     *
     * @param cronScheduleBuilder
     * @param misfirePolicy
     * @return
     */
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(CronScheduleBuilder cronScheduleBuilder, String misfirePolicy) {
        switch (misfirePolicy) {
            case ScheduleConstant.MISFIRE_DEFAULT:
                return cronScheduleBuilder;
            case ScheduleConstant.MISFIRE_IGNORE_MISFIRES:
                return cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstant.MISFIRE_FIRE_AND_PROCEED:
                return cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstant.MISFIRE_DO_NOTHING:
                return cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
            default:
                throw new ParameterException("任务策略错误:" + misfirePolicy);
        }
    }

    /**
     * 编辑本地定时任务
     * @param taskInfo
     * @return
     */
    public static TaskInfo editLocalJob(Scheduler scheduler, TaskInfo taskInfo) {
        String jobName = taskInfo.getJobName();
        String jobGroupName = taskInfo.getJobGroupName();
        String jobDescription = taskInfo.getJobDescription();
        JobDataMap dataMap = new JobDataMap();
        if (!CollectionUtils.isEmpty(taskInfo.getData())) {
            dataMap.putAll(taskInfo.getData());
        }
        try {
            if (!checkExists(scheduler, jobName, jobGroupName)) {
                throw new ParameterException(String.format("任务不存在, jobName:[%s], jobGroup:[%s]", jobName, jobGroupName));
            }
            //触发器的key值
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            //job的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            Trigger trigger = getTrigger(taskInfo, triggerKey);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey).getJobBuilder()
                    .withDescription(jobDescription).usingJobData(dataMap).build();
            Set<Trigger> triggerSet = Sets.newHashSet();
            triggerSet.add(trigger);
            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException | ParseException e) {
            e.printStackTrace();
            throw new ParameterException("任务添加失败");
        }
        return taskInfo;
    }

    /**
     * 添加本地定时任务
     * @param scheduler
     * @param taskInfo
     * @return
     */
    public static TaskInfo addLocalJob(Scheduler scheduler, TaskInfo taskInfo) {
        return addJob(scheduler, taskInfo);
    }

    /**
     * 添加远程任务
     * @param scheduler
     * @param taskInfo
     * @return
     */
    public static TaskInfo addHttpJob(Scheduler scheduler, TaskInfo taskInfo) {
        return null;
    }

    /**
     * 修改远程任务
     * @param scheduler
     * @param taskInfo
     * @return
     */
    public static TaskInfo editHttpJob(Scheduler scheduler, TaskInfo taskInfo) {
        return null;
    }

    private static Class<? extends Job> getQuartzJobClass(TaskInfo taskInfo) throws ClassNotFoundException {
        Class<? extends Job> clazz;
        if (ScheduleConstant.LOCAL_TASK.equals(taskInfo.getTaskProp())) {
            boolean isConcurrent = "0".equals(taskInfo.getConcurrent());
            clazz = !isConcurrent ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;

        } else {
            clazz = (Class<? extends Job>) Class
                    .forName(taskInfo.getJobClassName());
        }
        return clazz;
    }

    private static TaskInfo addJob(Scheduler scheduler, TaskInfo taskInfo) {
        String jobName = taskInfo.getJobName();
        String jobGroupName = taskInfo.getJobGroupName();
        String jobDescription = taskInfo.getJobDescription();
        JobDataMap dataMap = new JobDataMap();
        if (!CollectionUtils.isEmpty(taskInfo.getData())) {
            dataMap.putAll(taskInfo.getData());
        }
        dataMap.put("createTime", TimeUtil.getCurrentDatetime());
        dataMap.put(ScheduleConstant.TASK_PROPERTIES, taskInfo);
        try {
            if (checkExists(scheduler, jobName, jobGroupName)) {
                throw new ParameterException(String.format("任务已存在, jobName:[%s], jobGroup:[%s]", jobName, jobGroupName));
            }
            //触发器的key值
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            //job的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            Trigger trigger = getTrigger(taskInfo, triggerKey);

            JobDetail jobDetail = JobBuilder.newJob(getQuartzJobClass(taskInfo)).withIdentity(jobKey)
                    .withDescription(jobDescription).usingJobData(dataMap).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ParseException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ParameterException("任务添加失败");
        }
        return taskInfo;
    }
}