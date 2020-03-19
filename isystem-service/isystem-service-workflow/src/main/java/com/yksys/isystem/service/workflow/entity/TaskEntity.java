package com.yksys.isystem.service.workflow.entity;

import com.yksys.isystem.common.core.utils.TimeUtil;
import lombok.Data;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.Map;

/**
 * @program: project_base
 * @description:
 * @author: YuKai Fan
 * @create: 2019-08-08 14:50
 **/
@Data
public class TaskEntity {
    private String id;
    private String name;
    //任务开始时间
    private String startTime;
    //任务结束时间
    private String endTime;
    private String createTime;
    private String assignee;
    private String processInstanceId;//流程实例id
    private String processDefinitionId;//流程定义id
    private String description;
    private String category;

    private String userName;
    private String reason;
    private String urlPath;

    private Map<String, Object> params;

    //任务状态
    private int status;

    public TaskEntity() {
    }

    public TaskEntity(Task t) {
        this.id = t.getId();
        this.name = t.getName();
        this.createTime = TimeUtil.parseTime(t.getCreateTime());
        this.assignee = t.getAssignee();
        this.processInstanceId = t.getProcessInstanceId();
        this.processDefinitionId = t.getProcessDefinitionId();
        this.description = t.getDescription();
        this.category = t.getCategory();
    }

    public TaskEntity(HistoricTaskInstance t) {
        this.id = t.getId();
        this.name = t.getName();
        this.createTime = TimeUtil.parseTime(t.getCreateTime());
        this.assignee = t.getAssignee();
        this.processInstanceId = t.getProcessInstanceId();
        this.processDefinitionId = t.getProcessDefinitionId();
        this.description = t.getDescription();
        this.category = t.getCategory();
        this.startTime = TimeUtil.parseTime(t.getStartTime());
        this.endTime = TimeUtil.parseTime(t.getEndTime());
    }
}