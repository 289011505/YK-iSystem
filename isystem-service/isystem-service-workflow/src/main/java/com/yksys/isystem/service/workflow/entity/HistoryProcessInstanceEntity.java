package com.yksys.isystem.service.workflow.entity;

import com.yksys.isystem.common.core.utils.TimeUtil;
import lombok.Data;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;

/**
 * @ClassName processDefinition
 * @Description 流程定义实体
 * @Author YuKai Fan
 * @Date 2019/8/7 21:26
 * @Version 1.0
 **/
@Data
public class HistoryProcessInstanceEntity {
    private String processInstanceId;
    private String businessKey;
    private String startTime;
    private String endTime;
    private String startUserId;
    private String duration;
    private int status;


    public HistoryProcessInstanceEntity(HistoricProcessInstanceEntity e) {
        this.processInstanceId = e.getProcessInstanceId();
        this.startTime = TimeUtil.parseTime(e.getStartTime());
        this.startUserId = e.getStartUserId();
        this.duration = e.getDescription();
        this.businessKey = e.getBusinessKey();
        this.endTime = TimeUtil.parseTime(e.getEndTime());
    }
}
