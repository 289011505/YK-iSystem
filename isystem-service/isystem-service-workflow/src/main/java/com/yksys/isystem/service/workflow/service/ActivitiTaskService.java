package com.yksys.isystem.service.workflow.service;

import com.github.pagehelper.PageInfo;
import com.yksys.isystem.service.workflow.entity.HistoryTaskEntity;
import com.yksys.isystem.service.workflow.entity.TaskEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-17 16:28
 **/
public interface ActivitiTaskService {
    /**
     * 获取任务列表
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    PageInfo<TaskEntity> getTasks(int start, int pageSize, Map<String, Object> map);

    /**
     *
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    PageInfo<TaskEntity> getUpcomingTasks(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取待办任务列表
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    PageInfo<HistoryTaskEntity> getDoneTasks(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取已办任务列表
     * @param taskId
     * @param variable
     */
    void completeTask(String taskId, Map<String, Object> variable);

    /**
     * 重新提交更新任务
     * @param taskId
     * @param pass
     * @param map
     */
    void handleTask(String taskId, boolean pass, Map<String, Object> map);

    /**
     * 完成审批任务
     * @param taskId
     * @return
     */
    Map<String, Object> getVariables(String taskId);

    /**
     * 获取流程高亮图片
     * @param request
     * @param response
     * @param processInstanceId
     * @return
     */
    Map<String, Object> getHighLightProcImage(HttpServletRequest request, HttpServletResponse response, String processInstanceId);

    /**
     * 根据流程实例id获取审批信息
     * @param processInstanceId
     * @return
     */
    List<Map<String, Object>> getApproveInfo(String processInstanceId);

    /**
     * 获取申请事项
     * @param taskId
     * @return
     */
    Map<String, Object> getApplicationMatters(String taskId);

    /**
     * 获取历史申请事项
     * @param executionId
     * @return
     */
    Map<String, Object> getHisApplicationMatters(String executionId);
}