package com.yksys.isystem.service.system.service;

import com.yksys.isystem.common.model.TaskInfo;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 定时任务调度表 service
 * @author: YuKai Fan
 * @create: 2020-03-24 13:56:49
 **/
public interface TaskInfoService {
    /**
     * 新增定时任务调度表
     * @param taskInfo
     * @return
     */
    TaskInfo addTaskInfo(TaskInfo taskInfo);

    /**
     * 根据id查询定时任务调度表
     * @param id
     * @return
     */
    Map<String, Object> getTaskInfoById(String id);

    /**
     * 获取所有定时任务调度表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<TaskInfo> getTaskInfos(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有定时任务调度表
     * @param map 参数
     * @return
     */
    List<TaskInfo> getTaskInfos(Map<String, Object> map);

    /**
     * 修改定时任务调度表
     * @param taskInfo
     */
    void editTaskInfo(TaskInfo taskInfo);

    /**
     * 根据id删除定时任务调度表(软删除)
     * @param id
     */
    void delTaskInfoById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delTaskInfoByIs(List<String> ids);

    /**
     * 根据id删除定时任务调度表(真删除)
     * @param id
     */
    void delTaskInfoRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delTaskInfoRealByIds(List<String> ids);
}
