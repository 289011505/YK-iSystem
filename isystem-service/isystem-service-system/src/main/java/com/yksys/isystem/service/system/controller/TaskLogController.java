package com.yksys.isystem.service.system.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.TaskLog;
import com.yksys.isystem.common.vo.TaskLogVo;
import com.yksys.isystem.service.system.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 定时任务调度日志表
 * @author: YuKai Fan
 * @create: 2020-03-27 10:37:08
 *
 */
@RestController
@RequestMapping("/api/taskLog")
public class TaskLogController {
    @Autowired
    private TaskLogService taskLogService;

    /**
     * 新增定时任务调度日志表
     * @return
     */
    @PostMapping("/addTaskLog")
    public Result addTaskLog(@RequestBody TaskLogVo taskLogVo) {
        TaskLog taskLog = taskLogVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", taskLogService.addTaskLog(taskLog));
    }

    /**
     * 根据id查询定时任务调度日志表
     * @param id
     * @return
     */
    @GetMapping("/getTaskLogById")
    public Result getTaskLogById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", taskLogService.getTaskLogById(id));
    }

    /**
     * 更新定时任务调度日志表
     *
     * @return
     */
    @PutMapping("/editTaskLog")
    public Result editTaskLog(@RequestBody TaskLogVo taskLogVo) {
        TaskLog taskLog = taskLogVo.convert();
        taskLogService.editTaskLog(taskLog);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除定时任务调度日志表
     * @param id
     * @return
     */
    @DeleteMapping("/delTaskLog")
    public Result delTaskLogById(@RequestParam String id) {
        taskLogService.delTaskLogById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除定时任务调度日志表
     * @param ids
     * @return
     */
    @DeleteMapping("/delTaskLog/{ids}")
    public Result delTaskLogByIds(@PathVariable("ids") String[] ids) {
        taskLogService.delTaskLogByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的定时任务调度日志表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getTaskLogs/noPage")
    public Result getTaskLogs(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", taskLogService.getTaskLogs(map));
    }

    /**
     * 获取所有定时任务调度日志表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getTaskLogs")
    public Result getTaskLogs(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = taskLogService.getTaskLogs(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
