package com.yksys.isystem.common.vo;

import com.google.common.base.Converter;
import com.yksys.isystem.common.model.TaskInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Map;


/**
 * 定时任务调度表 vo类
 *
 * @author YuKai Fan
 * @create 2020-03-24 13:56:49
 */
@Data
public class TaskInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //任务标识
    private String id;
    //任务名称
    private String jobName;
    //任务描述
    private String jobDescription;
    //任务类名
    private String jobClassName;
    //任务分组名称
    private String jobGroupName;
    //任务类型 simple-简单任务, cron-表达式
    private String jobTrigger;
    //任务性质 远程or本地
    private String taskProp;
    //计划策略 0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行
    private String misfirePolicy;
    //任务表达式
    private String cronExpression;
    //间隔时间(毫秒)
    private Integer repeatInterval;
    //重复次数
    private Integer repeatCount;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //执行数据
    private String data;
    //执行数据
    private Map<String, Object> dataMap;
    //是否并发执行（1允许 0禁止）
    private String concurrent;
    //状态:0  已禁用 1 正在使用
    private Integer status;
    //备注
    private String remark;

    public TaskInfo convert() {
        TaskInfoVoConvert taskInfoVoConvert = new TaskInfoVoConvert();
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
