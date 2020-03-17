package com.yksys.isystem.service.workflow.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.UserLeave;
import com.yksys.isystem.service.workflow.mapper.UserLeaveMapper;
import com.yksys.isystem.service.workflow.service.UserLeaveService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class UserLeaveServiceImpl implements UserLeaveService {
    @Autowired
    private UserLeaveMapper userLeaveMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public UserLeave addUserLeave(UserLeave userLeave) {
        userLeave.setId(AppUtil.randomId());
        userLeave.setStatus(1);
        userLeave.setUserId(AppSession.getCurrentUserId());
        userLeave.setUrlPath(userLeave.getUrlPath() + "/" + userLeave.getId());

        //根据processDefinitionKey启动流程实例
        Map<String, Object> map = Maps.newHashMap();
        map.put("baseTask", userLeave);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leaveProcess", map);
        userLeave.setProcessInstanceId(processInstance.getId());

        userLeaveMapper.addUserLeave(userLeave);
        return userLeave;
    }

    @Override
    public Map<String, Object> getUserLeaveById(String id) {
        return userLeaveMapper.getUserLeaveById(id);
    }

    @Override
    public List<Map<String, Object>> getUserLeaves(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getUserLeaves(map);
    }

    @Override
    public List<Map<String, Object>> getUserLeaves(Map<String, Object> map) {
        List<Map<String, Object>> userLeaves = userLeaveMapper.getUserLeaves(map);
        if (!CollectionUtils.isEmpty(userLeaves)) {
            userLeaves.forEach(userLeave -> {
                String processInstanceId = userLeave.get("processInstanceId").toString();
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(processInstanceId).singleResult();

                //判断是否运行中
                if (processInstance != null) {
                    Task task = taskService.createTaskQuery()
                            .processInstanceId(processInstanceId).singleResult();

                    if (task != null) {
                        //当前任务的审批阶段
                        userLeave.put("taskName", task.getName());
                    }
                }

            });
        }
        return userLeaves;
    }

    @Override
    public void editUserLeave(UserLeave userLeave) {
        userLeaveMapper.editUserLeaveById(userLeave);
    }

    @Override
    public void delUserLeaveById(String id) {
        userLeaveMapper.delUserLeaveById(id);
    }

    @Override
    public void delUserLeaveByIs(List<String> ids) {
        userLeaveMapper.delUserLeaveByIds(ids);
    }

    @Override
    public void delUserLeaveRealById(String id) {
        userLeaveMapper.delUserLeaveRealById(id);
    }

    @Override
    public void delUserLeaveRealByIds(List<String> ids) {
        userLeaveMapper.delUserLeaveRealByIds(ids);
    }

}
