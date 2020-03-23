package com.yksys.isystem.common.vo;

import com.google.common.base.Converter;
import com.yksys.isystem.common.model.TaskInfo;
import com.yksys.isystem.common.pojo.SystemUser;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 定时任务详情
 * @author: YuKai Fan
 * @create: 2020-03-23 14:43
 **/
@Data
public class TaskInfoVo implements Serializable {
    private static final long serialVersionUID = -1071950623325765774L;

    /**
     * 任务id
     */
    private String id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务描述
     */
    private String jobDescription;
    /**
     * 任务类名
     */
    private String jobClassName;
    /**
     * 任务分组名称
     */
    private String jobGroupName;
    /**
     * 任务状态
     */
    private String jobStatus;
    /**
     * 任务类型 SimpleTrigger-简单任务, CronTrigger-表达式
     */
    private String jobTrigger;
    /**
     * 任务表达式
     */
    private String cronExpression;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 间隔时间(毫秒)
     */
    private Long repeatInterval;
    /**
     * 重复次数
     */
    private Integer repeatCount;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 终止时间
     */
    private String endTime;

    /**
     * 执行数据
     */
    private Map<String, Object> data;

    public TaskInfo convert() {
        TaskInfoVo.TaskInfoVoConvert taskInfoVoConvert = new TaskInfoVoConvert();
        TaskInfo taskInfo = taskInfoVoConvert.convert(this);
        return taskInfo;
    }

    private static class TaskInfoVoConvert extends Converter<TaskInfoVo, TaskInfo> {

        @Override
        protected TaskInfo doForward(TaskInfoVo taskInfoVo) {
            TaskInfo taskInfo = new TaskInfo();
            BeanUtils.copyProperties(taskInfoVo, taskInfo);
            return taskInfo;
        }

        @Override
        protected TaskInfoVo doBackward(TaskInfo taskInfo) {
            return null;
        }
    }


}