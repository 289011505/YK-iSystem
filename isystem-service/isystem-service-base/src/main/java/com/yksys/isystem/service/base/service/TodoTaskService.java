package com.yksys.isystem.service.base.service;
import com.yksys.isystem.common.pojo.TodoTask;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 待办事项表 service
 * @author: YuKai Fan
 * @create: 2020-04-16 11:37:57
 **/
public interface TodoTaskService {
    /**
     * 新增待办事项表
     * @param todoTask
     * @return
     */
    TodoTask addTodoTask(TodoTask todoTask);

    /**
     * 根据id查询待办事项表
     * @param id
     * @return
     */
    Map<String, Object> getTodoTaskById(String id);

    /**
     * 获取所有待办事项表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getTodoTasks(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有待办事项表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getTodoTasks(Map<String, Object> map);

    /**
     * 修改待办事项表
     * @param todoTask
     */
    void editTodoTask(TodoTask todoTask);

    /**
     * 根据id删除待办事项表(软删除)
     * @param id
     */
    void delTodoTaskById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delTodoTaskByIs(List<String> ids);

    /**
     * 根据id删除待办事项表(真删除)
     * @param id
     */
    void delTodoTaskRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delTodoTaskRealByIds(List<String> ids);
}
