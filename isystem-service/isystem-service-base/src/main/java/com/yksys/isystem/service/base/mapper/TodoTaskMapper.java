package com.yksys.isystem.service.base.mapper;

import com.yksys.isystem.common.pojo.TodoTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统待办事项表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface TodoTaskMapper {
    /**
     * 新增待办事项表
     * @param todoTask 待办事项表实体
     * @return
     */
    int addTodoTask(TodoTask todoTask);

    /**
     * 批量新增待办事项表
     * @param list 待办事项表集合
     */
    void addTodoTasks(@Param(value = "list") List<TodoTask> list);

    /**
     * 根据id查询指定待办事项表
     * @param id  主键
     * @return
     */
    Map<String, Object> getTodoTaskById(String id);

    /**
     * 根据id修改待办事项表
     * @param todoTask 待办事项表实体
     * @return
     */
    int editTodoTaskById(TodoTask todoTask);

    /**
     * 批量修改待办事项表
     *
     * @param todoTask 待办事项表实体
     * @param ids 主键集合
     */
    void editTodoTaskByIds(@Param("map") TodoTask todoTask, @Param("list") List<String> ids);

    /**
     * 根据id删除待办事项表
     * @param id
     * @return
     */
    int delTodoTaskById(String id);

    /**
     * 批量删除待办事项表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delTodoTaskByIds(List<String> ids);

    /**
     * 真删除待办事项表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delTodoTaskRealById(String id);

    /**
     * 真批量删除待办事项表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delTodoTaskRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllTodoTaskReal();

    /**
     * 获取所有待办事项表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getTodoTasks(Map<String, Object> map);
}
