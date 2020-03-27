package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.TaskLog;
import org.springframework.beans.BeanUtils;


/**
 * 定时任务调度日志表 vo类
 *
 * @author YuKai Fan
 * @create 2020-03-27 10:37:08
 */
@Data
public class TaskLogVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //任务日志标识
    private String id;
    //任务名称
    private String jobName;
    //任务类名
    private String jobClassName;
    //任务分组名称
    private String jobGroupName;
    //日志信息
    private String jobMessage;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //异常信息
    private String exceptionInfo;
    //状态:0  已禁用 1 正在使用
    private Integer status;
    //备注
    private String remark;

    public TaskLog convert() {
        TaskLogVoConvert taskLogVoConvert = new TaskLogVoConvert();
        TaskLog taskLog = taskLogVoConvert.convert(this);
        return taskLog;
    }

    private static class TaskLogVoConvert extends Converter<TaskLogVo, TaskLog> {

        @Override
        protected TaskLog doForward(TaskLogVo taskLogVo) {
            TaskLog taskLog = new TaskLog();
            BeanUtils.copyProperties(taskLogVo, taskLog);
            return taskLog;
        }

        @Override
        protected TaskLogVo doBackward(TaskLog taskLog) {
            return null;
        }
    }

}
