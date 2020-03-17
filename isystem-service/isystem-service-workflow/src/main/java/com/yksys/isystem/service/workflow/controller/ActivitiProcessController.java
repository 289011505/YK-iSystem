package com.yksys.isystem.service.workflow.controller;

import com.github.pagehelper.PageInfo;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.service.workflow.entity.HistoryProcessInstanceEntity;
import com.yksys.isystem.service.workflow.entity.ProcessInstanceEntity;
import com.yksys.isystem.service.workflow.service.ActivitiProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 流程管理controller
 * @author: YuKai Fan
 * @create: 2020-03-16 11:22
 **/
@RestController
@RequestMapping("/api/activiti")
public class ActivitiProcessController {
    @Autowired
    private ActivitiProcessService activitiProcessService;

    /**
     * 流程管理列表
     *
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/getProcessDeploys")
    public Result getProcessDeploys(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                            @RequestParam Map<String, Object> map) {
        PageInfo<ProcessInstanceEntity> list = activitiProcessService.getActProcessDeploys(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", list);
    }

    /**
     * 根据流程部署id启动流程
     *
     * @param deploymentId
     * @return
     * @deprecated
     */
    @PostMapping("/startProcess/{deploymentId}")
    public Result startProcess(@PathVariable("deploymentId") String deploymentId) {
        String processInstanceId = activitiProcessService.startProcess(deploymentId);
        return new Result(HttpStatus.OK.value(), "启动成功", processInstanceId);
    }

    /**
     * 根据流程部署id挂起流程
     *
     * @param processInstanceId
     * @return
     */
    @PostMapping("/pendProcess/{processInstanceId}")
    public Result pendProcess(@PathVariable("processInstanceId") String processInstanceId) {
        activitiProcessService.pendProcess(processInstanceId);
        return new Result(HttpStatus.OK.value(), "挂起成功");
    }

    /**
     * 根据流程部署id挂起流程
     *
     * @param processInstanceId
     * @return
     */
    @PostMapping("/activateProcess/{processInstanceId}")
    public Result activateProcess(@PathVariable("processInstanceId") String processInstanceId) {
        activitiProcessService.activateProcess(processInstanceId);
        return new Result(HttpStatus.OK.value(), "激活成功");
    }


    /**
     * 根据流程部署id删除流程定义 级联 删除流程节点绑定信息
     *
     * @param processInstanceId
     * @param reason
     * @return
     */
    @DeleteMapping("/deleteProcessInstance")
    public Result deleteProcessInstance(@RequestParam String processInstanceId, @RequestParam String reason) {
        activitiProcessService.deleteProcessInstance(processInstanceId, reason);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 历史流程管理列表
     *
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/getHistoryProcess")
    public Result getHistoryProcess(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                    @RequestParam Map<String, Object> map) {
        PageInfo<HistoryProcessInstanceEntity> list = activitiProcessService.getHistoryProcess(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", list);
    }
}