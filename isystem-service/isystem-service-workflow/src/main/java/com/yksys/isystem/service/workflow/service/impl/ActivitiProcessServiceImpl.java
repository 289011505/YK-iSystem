package com.yksys.isystem.service.workflow.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.service.workflow.entity.HistoryProcessInstanceEntity;
import com.yksys.isystem.service.workflow.entity.ProcessInstanceEntity;
import com.yksys.isystem.service.workflow.service.ActivitiProcessService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-16 11:26
 **/
@Service
public class ActivitiProcessServiceImpl implements ActivitiProcessService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    @Override
    public PageInfo<ProcessInstanceEntity> getActProcessDeploys(int start, int pageSize, Map<String, Object> map) {
//        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//        List<ProcessDefinition> processDefinitions = Lists.newArrayList();
//        //根据deploymentId, name查询流程
//        if (StringUtil.isNotBlank(map.get("deploymentId"))) {
//            processDefinitionQuery.deploymentId(map.get("deploymentId").toString());
//        }
//        if (StringUtil.isNotBlank(map.get("name"))) {
//            processDefinitionQuery.processDefinitionNameLike("%" + map.get("name").toString() + "%");
//        }
//        if (StringUtil.isNotBlank(map.get("key"))) {
//            processDefinitionQuery.processDefinitionKey(map.get("key").toString());
//        }
//
//        processDefinitionQuery.listPage(start - 1, pageSize).forEach(processDefinition -> processDefinitions.add(new ProcessDefinition(processDefinition)));
//        processDefinitions.forEach(processDefinition -> {
//            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
//            processDefinition.setDeploymentName(deployment.getName());
//            processDefinition.setDeploymentTime(TimeUtil.parseTime(deployment.getDeploymentTime()));
//
//            //根据流程定义id查询流程实例
//            List<ProcessInstance> activateList = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition.getId()).active().list();
//            List<ProcessInstance> suspendList = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition.getId()).suspended().list();
//            List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition.getId()).list();
//            if (!CollectionUtils.isEmpty(activateList)) {
//                processDefinition.setStatus(1);
//            } else if (!CollectionUtils.isEmpty(suspendList)) {
//                processDefinition.setStatus(3);
//            } else if (!CollectionUtils.isEmpty(list)) {
//                processDefinition.setStatus(0);
//            }
//        });
//        PageInfo pageList = new PageInfo<>(processDefinitions);
//        return pageList;
        List<ProcessInstanceEntity> list = Lists.newArrayList();
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
        int total = 0;
        if (!CollectionUtils.isEmpty(processInstances)) {
            total = processInstances.size();
        }
        if (start == 1) {
            processInstances = runtimeService.createProcessInstanceQuery().listPage(start - 1, pageSize*start);
        } else {
            processInstances = runtimeService.createProcessInstanceQuery().listPage((start - 1) * pageSize, pageSize*start);
        }
        processInstances.forEach(processInstance -> {
            ProcessInstanceEntity processInstanceEntity = new ProcessInstanceEntity((ExecutionEntity) processInstance);
//            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processInstance.getDeploymentId()).singleResult();
//            ProcessInstanceEntity processInstanceEntity = new ProcessInstanceEntity((ExecutionEntity) processInstance);
//            processInstanceEntity.setDeploymentName(deployment.getName());
//            processInstanceEntity.setDeploymentTime(TimeUtil.parseTime(deployment.getDeploymentTime()));

            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(processInstance.getDeploymentId()).singleResult();
            processInstanceEntity.setDiagramResourceName(processDefinition.getDiagramResourceName());
            processInstanceEntity.setResourceName(processDefinition.getResourceName());

            if (processInstanceEntity.isActive() && processInstanceEntity.isSuspended()) {
                processInstanceEntity.setStatus(2);
            } else if (processInstanceEntity.isActive() && !processInstanceEntity.isSuspended()) {
                processInstanceEntity.setStatus(1);
            } else if (processInstanceEntity.isEnded()) {
                processInstanceEntity.setStatus(0);
            }
//
            list.add(processInstanceEntity);
        });
        PageInfo pageList = new PageInfo<>(list);
        pageList.setTotal(total);
        return pageList;

    }

    @Override
    public List<ProcessInstanceEntity> getActProcessDeploys(Map<String, Object> map) {
        List<ProcessInstanceEntity> list = Lists.newArrayList();
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
        processInstances.forEach(processInstance -> {
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processInstance.getDeploymentId()).singleResult();
            ProcessInstanceEntity processInstanceEntity = new ProcessInstanceEntity((ExecutionEntity) processInstance);
            processInstanceEntity.setDeploymentName(deployment.getName());
            processInstanceEntity.setDeploymentTime(TimeUtil.parseTime(deployment.getDeploymentTime()));

            repositoryService.createProcessDefinitionQuery().deploymentId(processInstance.getDeploymentId()).singleResult();
            list.add(processInstanceEntity);
        });
        return list;
    }

    @Override
    public void delProcessDeployById(String deploymentId) {
        try {
            //级联删除，会删除和当前规则有关的所有信息, 包括历史
            repositoryService.deleteDeployment(deploymentId, true);
            //通知正在走这条流程的用户, 流程失败, 因为流程改变了 todo
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("流程定义删除失败");
        }
    }

    @Override
    public String startProcess(String deploymentId) {
        if (StringUtil.isBlank(deploymentId)) {
            throw new ParameterException("流程部署id为空, 请部署流程");
        }
        org.activiti.engine.repository.ProcessDefinition processDefinition = getProcessDefinition(deploymentId);
        if (processDefinition == null) {
            throw new ParameterException("流程部署错误!");
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinition.getKey());
        return processInstance.getId();
    }

    @Override
    public void deleteProcessInstance(String processInstanceId, String reason) {
        try {
            runtimeService.deleteProcessInstance(processInstanceId, reason);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("删除流程实例错误!");
        }
    }

    @Override
    public void pendProcess(String processInstanceId) {
        try {
            runtimeService.suspendProcessInstanceById(processInstanceId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("挂起流程实例错误!");
        }
    }

    @Override
    public void activateProcess(String processInstanceId) {
        try {
            runtimeService.activateProcessInstanceById(processInstanceId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("激活流程实例错误!");
        }
    }

    @Override
    public PageInfo<HistoryProcessInstanceEntity> getHistoryProcess(int start, int pageSize, Map<String, Object> map) {
        List<HistoryProcessInstanceEntity> list = Lists.newArrayList();
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().list();
//        int total = 0;
//        if (!CollectionUtils.isEmpty(historicProcessInstances)) {
//            total = historicProcessInstances.size();
//        }
//        if (start == 1) {
//            historicProcessInstances = historyService.createHistoricProcessInstanceQuery().listPage(start - 1, pageSize*start);
//        } else {
//            historicProcessInstances = historyService.createHistoricProcessInstanceQuery().listPage((start - 1) * pageSize, pageSize*start);
//        }
        //过滤未结束的流程
        historicProcessInstances.stream().filter(historicProcessInstance -> StringUtil.isNotBlank(historicProcessInstance.getEndTime()))
                .forEach(historicProcessInstance -> {
                            HistoryProcessInstanceEntity historyProcessInstanceEntity = new HistoryProcessInstanceEntity((HistoricProcessInstanceEntity) historicProcessInstance);
                            list.add(historyProcessInstanceEntity);
                });
        PageInfo pageList = new PageInfo<>(list);
//        pageList.setTotal(list.size());
        return pageList;
    }

    /**
     * 根据deploymentId获取流程定义实体
     *
     * @param deploymentId
     * @return
     */
    private org.activiti.engine.repository.ProcessDefinition getProcessDefinition(String deploymentId) {
        return repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
    }
}