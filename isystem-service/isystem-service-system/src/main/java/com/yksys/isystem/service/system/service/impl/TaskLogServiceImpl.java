package com.yksys.isystem.service.system.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.TaskLog;
import com.yksys.isystem.service.system.mapper.TaskLogMapper;
import com.yksys.isystem.service.system.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class TaskLogServiceImpl implements TaskLogService {
    @Autowired
    private TaskLogMapper taskLogMapper;

    @Override
    public TaskLog addTaskLog(TaskLog taskLog) {
        taskLog.setId(AppUtil.randomId());
        taskLog.setStatus(1);
        taskLogMapper.addTaskLog(taskLog);
        return taskLog;
    }

    @Override
    public Map<String, Object> getTaskLogById(String id) {
        return taskLogMapper.getTaskLogById(id);
    }

    @Override
    public List<Map<String, Object>> getTaskLogs(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getTaskLogs(map);
    }

    @Override
    public List<Map<String, Object>> getTaskLogs(Map<String, Object> map) {
        return taskLogMapper.getTaskLogs(map);
    }

    @Override
    public void editTaskLog(TaskLog taskLog) {
        taskLogMapper.editTaskLogById(taskLog);
    }

    @Override
    public void delTaskLogById(String id) {
        taskLogMapper.delTaskLogById(id);
    }

    @Override
    public void delTaskLogByIs(List<String> ids) {
        taskLogMapper.delTaskLogByIds(ids);
    }

    @Override
    public void delTaskLogRealById(String id) {
        taskLogMapper.delTaskLogRealById(id);
    }

    @Override
    public void delTaskLogRealByIds(List<String> ids) {
        taskLogMapper.delTaskLogRealByIds(ids);
    }

}
