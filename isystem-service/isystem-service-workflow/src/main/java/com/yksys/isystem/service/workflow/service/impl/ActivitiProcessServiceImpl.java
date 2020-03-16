package com.yksys.isystem.service.workflow.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.service.workflow.entity.ProcessDefinition;
import com.yksys.isystem.service.workflow.service.ActivitiProcessService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
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

    @Override
    public PageInfo<ProcessDefinition> getActProcessDeploys(int start, int pageSize, Map<String, Object> map) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = Lists.newArrayList();
        //根据deploymentId, name查询流程
        if (StringUtil.isNotBlank(map.get("deploymentId"))) {
            processDefinitionQuery.deploymentId(map.get("deploymentId").toString());
        }
        if (StringUtil.isNotBlank(map.get("name"))) {
            processDefinitionQuery.processDefinitionNameLike("%" + map.get("name").toString() + "%");
        }
        if (StringUtil.isNotBlank(map.get("key"))) {
            processDefinitionQuery.processDefinitionKey(map.get("key").toString());
        }

        processDefinitionQuery.listPage(start - 1, pageSize).forEach(processDefinition -> processDefinitions.add(new ProcessDefinition(processDefinition)));
        processDefinitions.forEach(processDefinition -> {
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
            processDefinition.setDeploymentName(deployment.getName());
            processDefinition.setDeploymentTime(TimeUtil.parseTime(deployment.getDeploymentTime()));

            //根据流程定义id查询流程实例
            List<ProcessInstance> activateList = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition.getId()).active().list();
            List<ProcessInstance> suspendList = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition.getId()).suspended().list();
            List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinition.getId()).list();
            if (!CollectionUtils.isEmpty(activateList)) {
                processDefinition.setStatus(1);
            } else if (!CollectionUtils.isEmpty(suspendList)) {
                processDefinition.setStatus(3);
            } else if (!CollectionUtils.isEmpty(list)) {
                processDefinition.setStatus(0);
            }
        });
        PageInfo pageList = new PageInfo<>(processDefinitions);
        return pageList;
    }

    @Override
    public List<ProcessDefinition> getActProcessDeploys(Map<String, Object> map) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = Lists.newArrayList();
        //根据deploymentId, name查询流程
        if (StringUtil.isNotBlank(map.get("deploymentId"))) {
            processDefinitionQuery.deploymentId(map.get("deploymentId").toString());
        }
        if (StringUtil.isNotBlank(map.get("name"))) {
            processDefinitionQuery.processDefinitionNameLike("%" + map.get("name").toString() + "%");
        }
        if (StringUtil.isNotBlank(map.get("key"))) {
            processDefinitionQuery.processDefinitionKey(map.get("key").toString());
        }
        processDefinitionQuery.list().forEach(processDefinition -> processDefinitions.add(new ProcessDefinition(processDefinition)));
        return processDefinitions;
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
    public void startProcess(String deploymentId) {
        if (StringUtil.isBlank(deploymentId)) {
            throw new ParameterException("流程部署id为空, 请部署流程");
        }
        org.activiti.engine.repository.ProcessDefinition processDefinition = getProcessDefinition(deploymentId);
        runtimeService.startProcessInstanceByKey(processDefinition.getKey());
    }

    /**
     * 根据deploymentId获取流程定义实体
     * @param deploymentId
     * @return
     */
    private org.activiti.engine.repository.ProcessDefinition getProcessDefinition(String deploymentId) {
        return repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deploymentId)
                    .singleResult();
    }
}