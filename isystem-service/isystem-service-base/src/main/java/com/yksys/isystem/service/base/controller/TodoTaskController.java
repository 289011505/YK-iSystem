package com.yksys.isystem.service.base.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.TodoTask;
import com.yksys.isystem.common.vo.TodoTaskVo;
import com.yksys.isystem.service.base.service.TodoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 待办事项表
 * @author: YuKai Fan
 * @create: 2020-04-16 11:37:57
 *
 */
@Api(tags = "待办事项表")
@RestController
@RequestMapping("/api/todoTask")
public class TodoTaskController {
    @Autowired
    private TodoTaskService todoTaskService;

    /**
     * 新增待办事项表
     * @return
     */
    @ApiOperation("新增待办事项表")
    @PostMapping("/addTodoTask")
    public Result addTodoTask(@RequestBody @ApiParam(name = "待办事项表vo对象", required = true) TodoTaskVo todoTaskVo) {
        TodoTask todoTask = todoTaskVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", todoTaskService.addTodoTask(todoTask));
    }

    /**
     * 根据id查询待办事项表
     * @param id
     * @return
     */
    @ApiOperation("根据id查询待办事项表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @GetMapping("/getTodoTaskById")
    public Result getTodoTaskById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", todoTaskService.getTodoTaskById(id));
    }

    /**
     * 更新待办事项表
     *
     * @return
     */
    @ApiOperation("更新待办事项表")
    @PutMapping("/editTodoTask")
    public Result editTodoTask(@RequestBody @ApiParam(name = "待办事项表vo对象", required = true) TodoTaskVo todoTaskVo) {
        TodoTask todoTask = todoTaskVo.convert();
        todoTaskService.editTodoTask(todoTask);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除待办事项表
     * @param id
     * @return
     */
    @ApiOperation("根据id删除待办事项表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @DeleteMapping("/delTodoTask")
    public Result delTodoTaskById(@RequestParam String id) {
        todoTaskService.delTodoTaskById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除待办事项表
     * @param ids
     * @return
     */
    @ApiOperation("根据ids批量删除待办事项表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "id数组", paramType = "string[]")
    })
    @DeleteMapping("/delTodoTask/{ids}")
    public Result delTodoTaskByIds(@PathVariable("ids") String[] ids) {
        todoTaskService.delTodoTaskByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的待办事项表(不分页)
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有的待办事项表(不分页)")
    @GetMapping("/getTodoTasks/noPage")
    public Result getTodoTasks(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", todoTaskService.getTodoTasks(map));
    }

    /**
     * 获取所有待办事项表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有待办事项表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", required = true, value = "开始记录", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "分页大小", paramType = "int")
    })
    @GetMapping("/getTodoTasks")
    public Result getTodoTasks(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = todoTaskService.getTodoTasks(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
