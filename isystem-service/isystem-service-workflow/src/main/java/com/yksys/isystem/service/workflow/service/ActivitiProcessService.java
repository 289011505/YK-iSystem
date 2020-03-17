package com.yksys.isystem.service.workflow.service;

import com.github.pagehelper.PageInfo;
import com.yksys.isystem.service.workflow.entity.HistoryProcessInstanceEntity;
import com.yksys.isystem.service.workflow.entity.ProcessDefinition;
import com.yksys.isystem.service.workflow.entity.ProcessInstanceEntity;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-16 11:24
 **/
public interface ActivitiProcessService {
    /**
     * 获取所有流程列表(分页)
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    PageInfo<ProcessInstanceEntity> getActProcessDeploys(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有流程列表
     * @param map
     * @return
     */
    List<ProcessInstanceEntity> getActProcessDeploys(Map<String, Object> map);

    /**
     * 删除流程定义 级联 删除流程节点绑定信息
     * @param deploymentId 部署id
     */
    void delProcessDeployById(String deploymentId);

    /**
     * 启动流程
     * @param deploymentId
     */
    String startProcess(String deploymentId);

    /**
     * 删除流程实例
     * @param processInstanceId
     */
    void deleteProcessInstance(String processInstanceId, String reason);

    /**
     * 挂起流程
     * @param processInstanceId
     */
    void pendProcess(String processInstanceId);

    /**
     * 激活流程
     * @param processInstanceId
     */
    void activateProcess(String processInstanceId);

    /**
     * 获取历史流程列表
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    PageInfo<HistoryProcessInstanceEntity> getHistoryProcess(int start, int pageSize, Map<String, Object> map);
}