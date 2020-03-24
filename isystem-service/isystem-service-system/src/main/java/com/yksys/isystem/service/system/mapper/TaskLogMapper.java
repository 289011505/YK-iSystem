package com.yksys.isystem.service.system.mapper;

import com.yksys.isystem.common.pojo.TaskLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统定时任务调度日志表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface TaskLogMapper {
    /**
     * 新增定时任务调度日志表
     * @param taskLog 定时任务调度日志表实体
     * @return
     */
    int addTaskLog(TaskLog taskLog);

    /**
     * 批量新增定时任务调度日志表
     * @param list 定时任务调度日志表集合
     */
    void addTaskLogs(@Param(value = "list") List<TaskLog> list);

    /**
     * 根据id查询指定定时任务调度日志表
     * @param id  主键
     * @return
     */
    Map<String, Object> getTaskLogById(String id);

    /**
     * 根据id修改定时任务调度日志表
     * @param taskLog 定时任务调度日志表实体
     * @return
     */
    int editTaskLogById(TaskLog taskLog);

    /**
     * 批量修改定时任务调度日志表
     *
     * @param taskLog 定时任务调度日志表实体
     * @param ids 主键集合
     */
    void editTaskLogByIds(@Param("map") TaskLog taskLog, @Param("list") List<String> ids);

    /**
     * 根据id删除定时任务调度日志表
     * @param id
     * @return
     */
    int delTaskLogById(String id);

    /**
     * 批量删除定时任务调度日志表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delTaskLogByIds(List<String> ids);

    /**
     * 真删除定时任务调度日志表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delTaskLogRealById(String id);

    /**
     * 真批量删除定时任务调度日志表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delTaskLogRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllTaskLogReal();

    /**
     * 获取所有定时任务调度日志表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getTaskLogs(Map<String, Object> map);
}
