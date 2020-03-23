package com.yksys.isystem.service.system.controller;

import com.yksys.isystem.common.core.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 定时任务controller
 * @author: YuKai Fan
 * @create: 2020-03-23 14:13
 **/
@RestController
@RequestMapping("/api/task")
public class TaskController {

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
        return new Result(HttpStatus.OK.value(), "获取成功");
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
        return new Result(HttpStatus.OK.value(), "获取成功");
    }

    @PostMapping("/addJob")
    public Result addJob() {
        return new Result(HttpStatus.OK.value(), "添加成功");
    }

    @PutMapping("/editJob")
    public Result editJob() {
        return new Result(HttpStatus.OK.value(), "修改成功");
    }

    @DeleteMapping("/deleteJob")
    public Result deleteJob() {
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    @PostMapping("/pauseJob")
    public Result pauseJob() {
        return new Result(HttpStatus.OK.value(), "成功");
    }

    @PostMapping("/resumeJob")
    public Result resumeJob() {
        return new Result(HttpStatus.OK.value(), "删除成功");
    }
}