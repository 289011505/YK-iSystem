package com.yksys.isystem.service.workflow.service;

import com.github.pagehelper.PageInfo;
import com.yksys.isystem.service.workflow.entity.ProcessDefinition;

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
    PageInfo<ProcessDefinition> getActProcessDeploys(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有流程列表
     * @param map
     * @return
     */
    List<ProcessDefinition> getActProcessDeploys(Map<String, Object> map);

    /**
     * 删除流程定义 级联 删除流程节点绑定信息
     * @param deploymentId 部署id
     */
    void delProcessDeployById(String deploymentId);

    /**
     * 启动流程
     * @param deploymentId
     */
    void startProcess(String deploymentId);
}