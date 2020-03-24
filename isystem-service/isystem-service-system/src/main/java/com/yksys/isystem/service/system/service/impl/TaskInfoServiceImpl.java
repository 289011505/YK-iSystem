package com.yksys.isystem.service.system.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.model.TaskInfo;
import com.yksys.isystem.service.system.mapper.TaskInfoMapper;
import com.yksys.isystem.service.system.service.TaskInfoService;
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
public class TaskInfoServiceImpl implements TaskInfoService {
    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Override
    public TaskInfo addTaskInfo(TaskInfo taskInfo) {
        taskInfo.setId(AppUtil.randomId());
        taskInfo.setStatus(1);
        taskInfoMapper.addTaskInfo(taskInfo);
        return taskInfo;
    }

    @Override
    public Map<String, Object> getTaskInfoById(String id) {
        return taskInfoMapper.getTaskInfoById(id);
    }

    @Override
    public List<TaskInfo> getTaskInfos(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getTaskInfos(map);
    }

    @Override
    public List<TaskInfo> getTaskInfos(Map<String, Object> map) {
        return taskInfoMapper.getTaskInfos(map);
    }

    @Override
    public void editTaskInfo(TaskInfo taskInfo) {
        taskInfoMapper.editTaskInfoById(taskInfo);
    }

    @Override
    public void delTaskInfoById(String id) {
        taskInfoMapper.delTaskInfoById(id);
    }

    @Override
    public void delTaskInfoByIs(List<String> ids) {
        taskInfoMapper.delTaskInfoByIds(ids);
    }

    @Override
    public void delTaskInfoRealById(String id) {
        taskInfoMapper.delTaskInfoRealById(id);
    }

    @Override
    public void delTaskInfoRealByIds(List<String> ids) {
        taskInfoMapper.delTaskInfoRealByIds(ids);
    }

}
