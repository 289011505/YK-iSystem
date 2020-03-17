package com.yksys.isystem.service.workflow.entity;

import lombok.Data;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

/**
 * @ClassName processDefinition
 * @Description 流程定义实体
 * @Author YuKai Fan
 * @Date 2019/8/7 21:26
 * @Version 1.0
 **/
@Data
public class ProcessInstanceEntity {
    private String processInstanceId;
    private String name;
    private String description;
    private String localizedName;
    private String processDefinitionId;
    private String processDefinitionName;
    private String processDefinitionKey;
    private String businessKey;
    private int processDefinitionVersion;
    private String deploymentId;
    private boolean isSuspended;
    private boolean isActive;
    private boolean isEnded;
    private String activityId;
    private String tenantId;
    private String deploymentName;
    private String deploymentTime;
    private String diagramResourceName;
    private String resourceName;
    private int status;

//    public ProcessInstance(ExecutionEntity p) {
//        this.processInstanceId=p.getProcessInstanceId();
//        this.description=p.getDescription();
//        this.name=p.getName();
//        this.processDefinitionKey=p.getProcessDefinitionKey();
//        this.description=p.getDescription();
//        this.processDefinitionVersion=p.getProcessDefinitionVersion();
//        this.businessKey=p.getBusinessKey();
//        this.deploymentId=p.getDeploymentId();
//        this.localizedName=p.getLocalizedName();
//        this.isActive=p.isActive();
//        this.isSuspended=p.isSuspended();
//        this.tenantId=p.getTenantId();
//        this.activityId=p.getActivityId();
//        this.processDefinitionId=p.getProcessDefinitionId();
//        this.processDefinitionName=p.getProcessDefinitionName();
//        this.tenantId=p.getTenantId();
//    }


    public ProcessInstanceEntity(ExecutionEntity e) {
        this.processInstanceId = e.getProcessInstanceId();
        this.name = e.getName();
        this.description = e.getDescription();
        this.localizedName = e.getLocalizedName();
        this.processDefinitionId = e.getProcessDefinitionId();
        this.processDefinitionName = e.getProcessDefinitionName();
        this.processDefinitionKey = e.getProcessDefinitionKey();
        this.businessKey = e.getBusinessKey();
        this.processDefinitionVersion = e.getProcessDefinitionVersion();
        this.deploymentId = e.getDeploymentId();
        this.isSuspended = e.isSuspended();
        this.isActive = e.isActive();
        this.isEnded = e.isEnded();
        this.activityId = e.getActivityId();
        this.tenantId = e.getTenantId();
    }
}
