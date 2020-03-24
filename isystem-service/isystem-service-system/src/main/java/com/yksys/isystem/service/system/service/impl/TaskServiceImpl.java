package com.yksys.isystem.service.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.model.TaskInfo;
import com.yksys.isystem.service.system.mapper.TaskInfoMapper;
import com.yksys.isystem.service.system.mapper.TaskMapper;
import com.yksys.isystem.service.system.service.TaskService;
import com.yksys.isystem.service.system.util.ScheduleUtil;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private TaskInfoMapper taskInfoMapper;

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
        return taskInfoMapper.getTaskInfos(map);
    }

    @Override
    public List<TaskInfo> getJobs(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getJobs(map);
    }

    @Override
    public TaskInfo addLocalJob(TaskInfo taskInfo) {
        return ScheduleUtil.addLocalJob(scheduler, taskInfo);
    }

    @Override
    public TaskInfo editLocalJob(TaskInfo taskInfo) {
        return ScheduleUtil.editLocalJob(scheduler, taskInfo);
    }

    @Override
    public TaskInfo addHttpJob(TaskInfo taskInfo) {
        return ScheduleUtil.addHttpJob(scheduler, taskInfo);
    }

    @Override
    public TaskInfo editHttpJob(TaskInfo taskInfo) {
        return ScheduleUtil.editHttpJob(scheduler, taskInfo);
    }

    @Override
    public void deleteJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (ScheduleUtil.checkExists(scheduler, jobName, jobGroup)) {
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
            if (ScheduleUtil.checkExists(scheduler, jobName, jobGroup)) {
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
            if (ScheduleUtil.checkExists(scheduler, jobName, jobGroup)) {
                scheduler.resumeTrigger(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new ParameterException("恢复任务错误");
        }
    }
}