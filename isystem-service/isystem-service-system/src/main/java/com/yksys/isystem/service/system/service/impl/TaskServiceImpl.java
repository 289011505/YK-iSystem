package com.yksys.isystem.service.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.common.model.TaskInfo;
import com.yksys.isystem.common.vo.TaskInfoVo;
import com.yksys.isystem.service.system.mapper.TaskMapper;
import com.yksys.isystem.service.system.service.TaskService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-23 14:56
 **/
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<String> getJobGroupNames() {
        try {
            return scheduler.getJobGroupNames();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    @Override
    public List<TaskInfo> getJobs(Map<String, Object> map) {
        return taskMapper.getJobs(map);
    }

    @Override
    public List<TaskInfo> getJobs(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getJobs(map);
    }

    @Override
    public TaskInfo addSimpleJob(TaskInfo taskInfo) {
        return addJob(taskInfo);
    }

    @Override
    public TaskInfo addCronJob(TaskInfo taskInfo) {
        return addJob(taskInfo);
    }

    private TaskInfo addJob(TaskInfo taskInfo) {
        String jobName = taskInfo.getJobName();
        String jobClassName = taskInfo.getJobClassName();
        String jobGroupName = taskInfo.getJobGroupName();
        String jobDescription = taskInfo.getJobDescription();
        String cronExpression = taskInfo.getCronExpression();
        JobDataMap dataMap = new JobDataMap();
        if (!CollectionUtils.isEmpty(taskInfo.getData())) {
            dataMap.putAll(taskInfo.getData());
        }
        dataMap.put("createTime", TimeUtil.getCurrentDatetime());
        try {
            if (checkExists(jobName, jobGroupName)) {
                throw new ParameterException(String.format("任务已存在, jobName:[%s], jobGroup:[%s]", jobName, jobGroupName));
            }
            //触发器的key值
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            //job的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            Trigger trigger;
            if (StringUtil.isBlank(cronExpression)) {
                //简单调度
                trigger = TriggerBuilder
                        .newTrigger().withIdentity(triggerKey)
                        .startAt(TimeUtil.parseTimeToDate(taskInfo.getStartTime()))
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMilliseconds(taskInfo.getRepeatInterval()).withRepeatCount(taskInfo.getRepeatCount()))
                        .endAt(TimeUtil.parseTimeToDate(taskInfo.getEndTime())).build();
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                        .cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();
            }

            Class<? extends Job> clazz = (Class<? extends Job>) Class
                    .forName(jobClassName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey)
                    .withDescription(jobDescription).usingJobData(dataMap).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ParseException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ParameterException("任务添加失败");
        }
        return taskInfo;
    }

    @Override
    public TaskInfo editSimpleJob(TaskInfo taskInfo) {
        return editJob(taskInfo);
    }

    @Override
    public TaskInfo editCronJob(TaskInfo taskInfo) {
        return editJob(taskInfo);
    }

    private TaskInfo editJob(TaskInfo taskInfo) {
        String jobName = taskInfo.getJobName();
        String jobGroupName = taskInfo.getJobGroupName();
        String jobDescription = taskInfo.getJobDescription();
        String cronExpression = taskInfo.getCronExpression();
        JobDataMap dataMap = new JobDataMap();
        if (!CollectionUtils.isEmpty(taskInfo.getData())) {
            dataMap.putAll(taskInfo.getData());
        }
        try {
            if (!checkExists(jobName, jobGroupName)) {
                throw new ParameterException(String.format("任务不存在, jobName:[%s], jobGroup:[%s]", jobName, jobGroupName));
            }
            //触发器的key值
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            //job的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            Trigger trigger;
            if (StringUtil.isBlank(cronExpression)) {
                //简单调度
                trigger = TriggerBuilder
                        .newTrigger().withIdentity(triggerKey)
                        .startAt(TimeUtil.parseTimeToDate(taskInfo.getStartTime()))
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMilliseconds(taskInfo.getRepeatInterval()).withRepeatCount(taskInfo.getRepeatCount()))
                        .endAt(TimeUtil.parseTimeToDate(taskInfo.getEndTime())).build();
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                        .cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();
            }

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

    @Override
    public void deleteJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new ParameterException("删除任务错误");
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new ParameterException("暂停任务错误");
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.resumeTrigger(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new ParameterException("恢复任务错误");
        }
    }

    /**
     * 验证任务是否存在
     * @param jobName
     * @param jobGroup
     * @return
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}