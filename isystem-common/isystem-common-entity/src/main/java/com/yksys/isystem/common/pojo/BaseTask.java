package com.yksys.isystem.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description: 基础任务结构实体
 * @author: YuKai Fan
 * @create: 2020-03-18 17:14
 **/
@Data
public abstract class BaseTask implements Serializable {

    protected String id;
    protected String userId;
    protected String processInstanceId;
    protected Integer status;
    protected String reason;
    protected String taskName;//实时节点信息
    protected String urlPath;
    private String createTime;
    private String createUserId;
    private String updateTime;
    private String updateUserId;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;

}