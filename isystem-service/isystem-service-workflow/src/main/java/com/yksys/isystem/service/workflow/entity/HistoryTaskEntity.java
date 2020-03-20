package com.yksys.isystem.service.workflow.entity;

import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import lombok.Data;
import org.activiti.engine.history.HistoricTaskInstance;

/**
 * @program: project_base
 * @description:
 * @author: YuKai Fan
 * @create: 2019-08-08 14:50
 **/
@Data
public class HistoryTaskEntity extends TaskEntity {
    private String executionId;


    public HistoryTaskEntity(HistoricTaskInstance t) {
        super(t);
        this.executionId = t.getExecutionId();
    }

}