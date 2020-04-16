package com.yksys.isystem.service.base.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.TodoTask;
import com.yksys.isystem.service.base.mapper.TodoTaskMapper;
import com.yksys.isystem.service.base.service.TodoTaskService;
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
public class TodoTaskServiceImpl implements TodoTaskService {
    @Autowired
    private TodoTaskMapper todoTaskMapper;

    @Override
    public TodoTask addTodoTask(TodoTask todoTask) {

        //判断当前用户正在执行的待办事项是否超过8个
        Map<String, Object> map = Maps.newHashMap();
        String currentUserId = AppSession.getCurrentUserId();
        map.put("userId", currentUserId);
        map.put("status", 1);
        List<Map<String, Object>> todoTasks = todoTaskMapper.getTodoTasks(map);
        if (todoTasks.size() > 7) {
            throw new ParameterException("您的待办事项已超过8个, 请完成后在添加!");
        }
        todoTask.setId(AppUtil.randomId());
        todoTask.setStatus(1);
        todoTask.setUserId(currentUserId);
        todoTaskMapper.addTodoTask(todoTask);
        return todoTask;
    }

    @Override
    public Map<String, Object> getTodoTaskById(String id) {
        return todoTaskMapper.getTodoTaskById(id);
    }

    @Override
    public List<Map<String, Object>> getTodoTasks(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getTodoTasks(map);
    }

    @Override
    public List<Map<String, Object>> getTodoTasks(Map<String, Object> map) {
        return todoTaskMapper.getTodoTasks(map);
    }

    @Override
    public void editTodoTask(TodoTask todoTask) {
        //如果是完成操作, 就判断已完成的事项是否超过8个
        Map<String, Object> map = Maps.newHashMap();
        String currentUserId = AppSession.getCurrentUserId();
        map.put("userId", currentUserId);
        map.put("status", todoTask.getStatus());
        List<Map<String, Object>> todoTasks = todoTaskMapper.getTodoTasks(map);
        if (todoTask.getStatus() == 2) {
            if (todoTasks.size() > 7) {
                Map<String, Object> todoTaskMap = todoTasks.get(todoTasks.size() - 1);
                this.delTodoTaskById(todoTaskMap.get("id").toString());
            }
        } else if (todoTask.getStatus() == 1) {
            if (todoTasks.size() > 7) {
                throw new ParameterException("您的待办事项已超过8个, 请完成后在添加!");
            }
        }
        todoTaskMapper.editTodoTaskById(todoTask);
    }

    @Override
    public void delTodoTaskById(String id) {
        todoTaskMapper.delTodoTaskById(id);
    }

    @Override
    public void delTodoTaskByIs(List<String> ids) {
        todoTaskMapper.delTodoTaskByIds(ids);
    }

    @Override
    public void delTodoTaskRealById(String id) {
        todoTaskMapper.delTodoTaskRealById(id);
    }

    @Override
    public void delTodoTaskRealByIds(List<String> ids) {
        todoTaskMapper.delTodoTaskRealByIds(ids);
    }

}
