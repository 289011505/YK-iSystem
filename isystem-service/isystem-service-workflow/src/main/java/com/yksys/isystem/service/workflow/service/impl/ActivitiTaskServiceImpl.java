package com.yksys.isystem.service.workflow.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.service.workflow.entity.BaseTask;
import com.yksys.isystem.service.workflow.entity.TaskEntity;
import com.yksys.isystem.service.workflow.service.ActivitiTaskService;
import com.yksys.isystem.service.workflow.service.feign.AdminService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
@Service
public class ActivitiTaskServiceImpl implements ActivitiTaskService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public PageInfo<TaskEntity> getTasks(int start, int pageSize, Map<String, Object> map) {
        return null;
    }

    @Override
    public PageInfo<TaskEntity> getUpcomingTasks(int start, int pageSize, Map<String, Object> map) {
        String userId = map.get("userId").toString();
        List<Task> taskList = taskService.createTaskQuery()
                .taskCandidateOrAssigned(userId).list();
        int total = 0;
        if (!CollectionUtils.isEmpty(taskList)) {
            total = taskList.size();
        }
        if (start == 1) {
            taskList =  taskService.createTaskQuery()
                    .taskCandidateOrAssigned(userId).listPage(start - 1, pageSize*start);
        } else {
            taskList =  taskService.createTaskQuery()
                    .taskCandidateOrAssigned(userId).listPage((start - 1) * pageSize, pageSize*start);
        }

        List<TaskEntity> list = Lists.newArrayList();
        taskList.forEach(task -> {
            Map<String, Object> variables = taskService.getVariables(task.getId());
            BaseTask baseTask = (BaseTask) variables.get("baseTask");
            TaskEntity taskEntity = new TaskEntity(task);
            taskEntity.setReason(baseTask.getReason());
            taskEntity.setUrlPath(baseTask.getUrlPath());
            taskEntity.setUserName(AppSession.getCurrentUser().getUsername());
            //判断当前办理人是否是自己
            if (userId.equals(baseTask.getUserId())) {
                if (variables.containsKey("flag") && StringUtil.isNotBlank(variables.get("flag"))) {
                    //判断流程是否通过
                    taskEntity.setStatus((boolean)variables.get("flag") ? 1 : 2);
                } else {
                    taskEntity.setStatus(1);
                }
            } else { // 办理人不是自己, 状态为2
                taskEntity.setStatus(2);
            }

            list.add(taskEntity);
        });
        PageInfo<TaskEntity> pageList = new PageInfo<>(list);
        pageList.setTotal(total);
        return pageList;
    }

    @Override
    public PageInfo<TaskEntity> getDoneTasks(int start, int pageSize, Map<String, Object> map) {
        String userId = map.get("userId").toString();
        List<HistoricTaskInstance> historicTaskList = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userId).list();
        int total = 0;
        if (!CollectionUtils.isEmpty(historicTaskList)) {
            total = historicTaskList.size();
        }
        if (start == 1) {
            historicTaskList =  historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(userId).listPage(start - 1, pageSize*start);
        } else {
            historicTaskList =  historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(userId).listPage((start - 1) * pageSize, pageSize*start);
        }

        PageInfo<HistoricTaskInstance> pageList = new PageInfo<>(historicTaskList);
        pageList.setTotal(total);
        return null;
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> variable) {

    }

    @Override
    public void handleTask(String taskId, boolean pass, Map<String, Object> map) {

    }

    @Override
    public Map<String, Object> getVariables(String taskId) {
        return null;
    }

    @Override
    public Map<String, Object> getHighLightProcImage(HttpServletRequest request, HttpServletResponse response, String processInstanceId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getApproveInfo(String processInstanceId) {
        return null;
    }
}