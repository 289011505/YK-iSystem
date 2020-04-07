package com.yksys.isystem.service.system.controller;

import com.yksys.isystem.common.core.constants.ScheduleConstant;
import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.model.TaskInfo;
import com.yksys.isystem.common.vo.TaskInfoVo;
import com.yksys.isystem.service.system.http.HttpExecuteJob;
import com.yksys.isystem.service.system.service.TaskInfoService;
import com.yksys.isystem.service.system.service.TaskService;
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
    @Autowired
    private TaskInfoService taskInfoService;

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
    @PostMapping("/addLocalJob")
    public Result addLocalJob(@RequestBody TaskInfoVo taskInfoVo) {
        TaskInfo taskInfo = taskInfoVo.convert();
        taskInfo = taskInfoService.addTaskInfo(taskInfo);
        taskInfo.setTaskProp(ScheduleConstant.LOCAL_TASK);
        taskInfo = taskService.addLocalJob(taskInfo);

        return new Result(HttpStatus.OK.value(), "添加成功", taskInfo);
    }

    /**
     * 修改定时任务
     * @param taskInfoVo
     * @return
     */
    @PutMapping("/editLocalJob")
    public Result editLocalJob(@RequestBody TaskInfoVo taskInfoVo) {
        TaskInfo taskInfo = taskInfoVo.convert();
        taskInfoService.editTaskInfo(taskInfo);
        taskInfo = taskService.editLocalJob(taskInfo);
        return new Result(HttpStatus.OK.value(), "修改成功", taskInfo);
    }

    /**
     * 添加远程定时任务
     * @param taskInfoVo
     * @return
     */
    @PostMapping("/addHttpJob")
    public Result addHttpJob(@RequestBody TaskInfoVo taskInfoVo) {
        TaskInfo taskInfo = taskInfoVo.convert();
        taskInfo.setJobClassName(HttpExecuteJob.class.getName());
        taskInfo = taskInfoService.addTaskInfo(taskInfo);
        taskInfo.setTaskProp(ScheduleConstant.HTTP_TASK);
        taskInfo = taskService.addHttpJob(taskInfo);
        return new Result(HttpStatus.OK.value(), "添加成功", taskInfo);
    }

    /**
     * 修改远程定时任务
     * @param taskInfoVo
     * @return
     */
    @PutMapping("/editHttpJob")
    public Result editHttpJob(@RequestBody TaskInfoVo taskInfoVo) {
        TaskInfo taskInfo = taskInfoVo.convert();
        taskInfo.setJobClassName(HttpExecuteJob.class.getName());
        taskInfoService.editTaskInfo(taskInfo);
        taskInfo = taskService.editHttpJob(taskInfo);
        return new Result(HttpStatus.OK.value(), "修改成功", taskInfo);
    }

    /**
     * 删除任务
     * @param jobName
     * @param jobGroupName
     * @return
     */
    @DeleteMapping("/deleteJob/{jobName}/{jobGroupName}/{id}")
    public Result deleteJob(@PathVariable("jobName") String jobName, @PathVariable("jobGroupName") String jobGroupName,
                            @PathVariable("id") String id) {
        taskInfoService.delTaskInfoById(id);
        taskService.deleteJob(jobName, jobGroupName);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroupName
     * @return
     */
    @PutMapping("/pauseJob/{jobName}/{jobGroupName}/{id}")
    public Result pauseJob(@PathVariable("jobName") String jobName, @PathVariable("jobGroupName") String jobGroupName,
                           @PathVariable("id") String id) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setId(id);
        taskInfo.setStatus(2);//暂停任务
        taskInfoService.editTaskInfo(taskInfo);
        taskService.pauseJob(jobName, jobGroupName);
        return new Result(HttpStatus.OK.value(), "暂停成功");
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroupName
     * @return
     */
    @PutMapping("/resumeJob/{jobName}/{jobGroupName}/{id}")
    public Result resumeJob(@PathVariable("jobName") String jobName, @PathVariable("jobGroupName") String jobGroupName,
                            @PathVariable("id") String id) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setId(id);
        taskInfo.setStatus(1);//恢复任务
        taskInfoService.editTaskInfo(taskInfo);
        taskService.resumeJob(jobName, jobGroupName);
        return new Result(HttpStatus.OK.value(), "恢复成功");
    }
}