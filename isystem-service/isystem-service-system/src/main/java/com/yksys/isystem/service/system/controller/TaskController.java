package com.yksys.isystem.service.system.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.model.TaskInfo;
import com.yksys.isystem.common.vo.TaskInfoVo;
import com.yksys.isystem.service.system.service.TaskService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 定时任务controller
 * @author: YuKai Fan
 * @create: 2020-03-23 14:13
 **/
@RestController
@RequestMapping("/api/taskScheduler")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 获取任务执行日志列表
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/jobLogs")
    public Result getJobLogs(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                             @RequestParam Map<String, Object> map) {
        List<TaskInfo> jobs = taskService.getJobs(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage<>(jobs));
    }

    /**
     * 获取定时任务列表
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/jobs")
    public Result getJobs(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                          @RequestParam Map<String, Object> map) {
        List<TaskInfo> jobs = taskService.getJobs(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage<>(jobs));
    }

    /**
     * 添加定时任务
     * @param taskInfoVo
     * @return
     */
    @PostMapping("/addJob")
    public Result addJob(@RequestBody TaskInfoVo taskInfoVo) {
        TaskInfo taskInfo = taskInfoVo.convert();
        if ("simple".equals(taskInfoVo.getJobType())) {
            taskInfo = taskService.addSimpleJob(taskInfo);
        } else {
            taskInfo = taskService.addCronJob(taskInfo);
        }
        return new Result(HttpStatus.OK.value(), "添加成功", taskInfo);
    }

    /**
     * 修改定时任务
     * @param taskInfoVo
     * @return
     */
    @PutMapping("/editJob")
    public Result editJob(@RequestBody TaskInfoVo taskInfoVo) {
        TaskInfo taskInfo = taskInfoVo.convert();
        if ("simple".equals(taskInfoVo.getJobType())) {
            taskInfo = taskService.editSimpleJob(taskInfo);
        } else {
            taskInfo = taskService.editCronJob(taskInfo);
        }
        return new Result(HttpStatus.OK.value(), "修改成功", taskInfo);
    }

    /**
     * 删除任务
     * @param jobName
     * @param jobGroupName
     * @return
     */
    @DeleteMapping("/deleteJob/{jobName}/{jobGroupName}")
    public Result deleteJob(@PathVariable("jobName") String jobName, @PathVariable("jobGroupName") String jobGroupName) {
        taskService.deleteJob(jobName, jobGroupName);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroupName
     * @return
     */
    @PutMapping("/pauseJob/{jobName}/{jobGroupName}")
    public Result pauseJob(@PathVariable("jobName") String jobName, @PathVariable("jobGroupName") String jobGroupName) {
        taskService.pauseJob(jobName, jobGroupName);
        return new Result(HttpStatus.OK.value(), "暂停成功");
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroupName
     * @return
     */
    @PutMapping("/resumeJob/{jobName}/{jobGroupName}")
    public Result resumeJob(@PathVariable("jobName") String jobName, @PathVariable("jobGroupName") String jobGroupName) {
        taskService.resumeJob(jobName, jobGroupName);
        return new Result(HttpStatus.OK.value(), "恢复成功");
    }
}