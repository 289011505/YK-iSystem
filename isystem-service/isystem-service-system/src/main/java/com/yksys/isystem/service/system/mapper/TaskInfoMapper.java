package com.yksys.isystem.service.system.mapper;

import com.yksys.isystem.common.model.TaskInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统定时任务调度表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface TaskInfoMapper {
    /**
     * 新增定时任务调度表
     * @param taskInfo 定时任务调度表实体
     * @return
     */
    int addTaskInfo(TaskInfo taskInfo);

    /**
     * 批量新增定时任务调度表
     * @param list 定时任务调度表集合
     */
    void addTaskInfos(@Param(value = "list") List<TaskInfo> list);

    /**
     * 根据id查询指定定时任务调度表
     * @param id  主键
     * @return
     */
    Map<String, Object> getTaskInfoById(String id);

    /**
     * 根据id修改定时任务调度表
     * @param taskInfo 定时任务调度表实体
     * @return
     */
    int editTaskInfoById(TaskInfo taskInfo);

    /**
     * 批量修改定时任务调度表
     *
     * @param taskInfo 定时任务调度表实体
     * @param ids 主键集合
     */
    void editTaskInfoByIds(@Param("map") TaskInfo taskInfo, @Param("list") List<String> ids);

    /**
     * 根据id删除定时任务调度表
     * @param id
     * @return
     */
    int delTaskInfoById(String id);

    /**
     * 批量删除定时任务调度表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delTaskInfoByIds(List<String> ids);

    /**
     * 真删除定时任务调度表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delTaskInfoRealById(String id);

    /**
     * 真批量删除定时任务调度表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delTaskInfoRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllTaskInfoReal();

    /**
     * 获取所有定时任务调度表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<TaskInfo> getTaskInfos(Map<String, Object> map);
}
