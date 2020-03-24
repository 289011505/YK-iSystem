package com.yksys.isystem.service.system.service;

import com.yksys.isystem.common.model.TaskInfo;
import com.yksys.isystem.common.vo.TaskInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 定时任务service
 * @author: YuKai Fan
 * @create: 2020-03-23 14:42
 **/
public interface TaskService {
    /**
     * 获取任务分组名称
     *
     * @return
     */
    List<String> getJobGroupNames();

    /**
     * 获取任务列表
     * @param map
     * @return
     */
    List<TaskInfo> getJobs(Map<String, Object> map);

    /**
     * 获取任务列表(分页)
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    List<TaskInfo> getJobs(int start, int pageSize, Map<String, Object> map);

    /**
     * 添加本地任务
     * @param taskInfo
     * @return
     */
    TaskInfo addLocalJob(TaskInfo taskInfo);

    /**
     * 修改本地任务
     * @param taskInfo
     * @return
     */
    TaskInfo editLocalJob(TaskInfo taskInfo);

    /**
     * 添加远程任务
     * @param taskInfo
     * @return
     */
    TaskInfo addHttpJob(TaskInfo taskInfo);

    /**
     * 修改远程任务
     * @param taskInfo
     * @return
     */
    TaskInfo editHttpJob(TaskInfo taskInfo);

    /**
     * 删除定时任务
     * @param jobName 任务名
     * @param jobGroup 任务组名
     */
    void deleteJob(String jobName, String jobGroup);

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复定时任务
     * @param jobName
     * @param jobGroup
     */
    void resumeJob(String jobName, String jobGroup);
}