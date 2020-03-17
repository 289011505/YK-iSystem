package com.yksys.isystem.service.workflow.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.constants.ActivitiConstant;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.utils.Base64Util;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.service.workflow.entity.BaseTask;
import com.yksys.isystem.service.workflow.entity.TaskEntity;
import com.yksys.isystem.service.workflow.service.ActivitiTaskService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.HMProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
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
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngineFactoryBean processEngine;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

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
        List<TaskEntity> list = Lists.newArrayList();

        historicTaskList.forEach(historicTaskInstance -> {
            TaskEntity taskEntity = new TaskEntity(historicTaskInstance);
            list.add(taskEntity);
        });

        PageInfo<TaskEntity> pageList = new PageInfo<>(list);
        pageList.setTotal(total);
        return pageList;
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> variable) {
        taskService.complete(taskId, variable);
    }

    @Override
    public void handleTask(String taskId, boolean pass, Map<String, Object> map) {
        try {
            Map<String, Object> variables = this.getVariables(taskId);

            //处理当前节点信息
            map.put("createTime", new Date());
            map.put("userId", AppSession.getCurrentUserId());
            map.put("userName", AppSession.getCurrentUser().getUsername());
            map.put("pass", pass);

            Map<String, Object> taskInfo = Maps.newHashMap();
            taskInfo.put("pass", pass);
            //判断是否已经拒绝过一次
            Object alreadyNo = variables.get("alreadyNo");
            if (alreadyNo != null && (boolean) alreadyNo && (!pass)) {//如果之前已经拒绝过,再次申请，直接结束流程
                taskInfo.put("approve", -1);
            } else {
                if (pass) {
                    taskInfo.put("approve", 1); //通过下一节点
                } else {
                    taskInfo.put("approve", 0); //不通过
                }
            }

            List<Map<String, Object>> approveMsgList = Lists.newArrayList();
            Object o = variables.get(ActivitiConstant.APPROVAL_MESSAGE);
            if (o != null) {
                approveMsgList = (List<Map<String, Object>>) o;
            }
            approveMsgList.add(map);

            taskInfo.put(ActivitiConstant.APPROVAL_MESSAGE, approveMsgList);

            this.completeTask(taskId, taskInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("操作错误!");
        }
    }

    @Override
    public Map<String, Object> getVariables(String taskId) {
        return taskService.getVariables(taskId);
    }

    @Override
    public Map<String, Object> getHighLightProcImage(HttpServletRequest request, HttpServletResponse response, String processInstanceId) {
        try {
            Map<String, Object> map = Maps.newHashMap();
            JSONArray imageBase64 = new JSONArray();
            InputStream imageStream = generateImageStream(processInstanceId, true);
            if (imageStream != null) {
                String imageCurrentNode = Base64Util.ioToBase64(imageStream);
                if (org.apache.commons.lang3.StringUtils.isNotBlank(imageCurrentNode)) {
                    imageBase64.add(imageCurrentNode);
                }
            }
            InputStream imageNoCurrentStream = generateImageStream(processInstanceId, false);
            if (imageNoCurrentStream != null) {
                String imageNoCurrentNode = Base64Util.ioToBase64(imageNoCurrentStream);
                if (org.apache.commons.lang3.StringUtils.isNotBlank(imageNoCurrentNode)) {
                    imageBase64.add(imageNoCurrentNode);
                }
            }
            map.put("images", imageBase64);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("获取流程图错误!");
        }
    }

    @Override
    public List<Map<String, Object>> getApproveInfo(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        List<Map<String, Object>> approvalList = Lists.newArrayList();
        //判断当前流程是否运行中
        if (processInstance != null) {
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            Map<String, Object> variables = this.getVariables(task.getId());
            Object o = variables.get(ActivitiConstant.APPROVAL_MESSAGE);
            if (o != null) {
                approvalList = (List<Map<String, Object>>) o;
            }
        } else {
            List<HistoricDetail> list = historyService.createHistoricDetailQuery()
                    .processInstanceId(processInstanceId).list();

            HistoricVariableUpdate variableUpdate;
            for (HistoricDetail historicDetail : list) {
                variableUpdate = (HistoricVariableUpdate) historicDetail;
                String variableName = variableUpdate.getVariableName();
                if (ActivitiConstant.APPROVAL_MESSAGE.equals(variableName)) {
                    approvalList.clear();
                    approvalList.addAll((List<Map<String, Object>>)variableUpdate.getValue());
                }
            }
        }
        return approvalList;
    }
    /**
     * @Description 获取当前流程图，历史流程
     *
     * @Author YuKai Fan
     * @Date 19:50 2019/8/9
     * @Param isCurrent:是否获取当前流程
     * @return
     **/
    private InputStream generateImageStream(String processInstanceId, boolean isCurrent) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        String processDefinitionId = null;
        //执行过的activityId集合
        List<String> executedActivityIdList = Lists.newArrayList();
        //当前执行的activityId集合
        List<String> currentActivityIdList = Lists.newArrayList();
        //历史执行的activityId集合
        List<HistoricActivityInstance> historicActivityInstanceList = Lists.newArrayList();

        if (processInstance != null) {//判断当前流程实例是否为空
            processDefinitionId = processInstance.getProcessDefinitionId();
            if (isCurrent) {//是否获取当前流程
                currentActivityIdList = runtimeService.getActiveActivityIds(processInstance.getId());
            }
        }

        if (historicProcessInstance != null) {//判断历史流程实例是否为空
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
            historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(historicActivityInstance.getActivityId());
            }
        }

        if (StringUtil.isEmpty(processDefinitionId) || CollectionUtils.isEmpty(executedActivityIdList)) {
            return null;
        }

        //高亮线路集合id
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        List<String> highLightedFlows = getHighLightedFlows(processDefinitionEntity, historicActivityInstanceList);

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        HMProcessDiagramGenerator diagramGenerator = (HMProcessDiagramGenerator) processEngineConfiguration.getProcessDiagramGenerator();


        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png",
                executedActivityIdList, highLightedFlows, "宋体",
                "微软雅黑","黑体", null, 1.0,
                currentActivityIdList);

        return imageStream;
    }

    public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = Lists.newArrayList();//保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            ActivityImpl activity = processDefinitionEntity.findActivity(historicActivityInstances.get(i).getActivityId());

            List<ActivityImpl> sameStartTimeNodes = Lists.newArrayList();//保存需要开始时间相同的节点
            ActivityImpl activity1 = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1).getActivityId());

            //将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(activity1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                //后续第一个节点
                HistoricActivityInstance historicActivityInstance1 = historicActivityInstances.get(j);
                //后续第二个节点
                HistoricActivityInstance historicActivityInstance2 = historicActivityInstances.get(j + 1);
                if (historicActivityInstance1.getStartTime().equals(historicActivityInstance2.getStartTime())) {
                    //如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl activity2 = processDefinitionEntity.findActivity(historicActivityInstance2.getActivityId());
                    sameStartTimeNodes.add(activity2);
                } else {
                    break;
                }
            }

            //取出节点的所有出去的线
            List<PvmTransition> pvmTransitions = activity.getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitions) {
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }


        }
        return highFlows;
    }
}