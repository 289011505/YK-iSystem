package com.yksys.isystem.service.system.service;
import com.yksys.isystem.common.pojo.TaskLog;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 定时任务调度日志表 service
 * @author: YuKai Fan
 * @create: 2020-03-24 21:46:54
 **/
public interface TaskLogService {
    /**
     * 新增定时任务调度日志表
     * @param taskLog
     * @return
     */
    TaskLog addTaskLog(TaskLog taskLog);

    /**
     * 根据id查询定时任务调度日志表
     * @param id
     * @return
     */
    Map<String, Object> getTaskLogById(String id);

    /**
     * 获取所有定时任务调度日志表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getTaskLogs(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有定时任务调度日志表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getTaskLogs(Map<String, Object> map);

    /**
     * 修改定时任务调度日志表
     * @param taskLog
     */
    void editTaskLog(TaskLog taskLog);

    /**
     * 根据id删除定时任务调度日志表(软删除)
     * @param id
     */
    void delTaskLogById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delTaskLogByIs(List<String> ids);

    /**
     * 根据id删除定时任务调度日志表(真删除)
     * @param id
     */
    void delTaskLogRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delTaskLogRealByIds(List<String> ids);
}
