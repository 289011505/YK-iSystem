package com.yksys.isystem.service.workflow.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.constants.ActivitiConstant;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.pojo.UserLeave;
import com.yksys.isystem.service.workflow.entity.TaskEntity;
import com.yksys.isystem.service.workflow.service.ActivitiTaskService;
import com.yksys.isystem.service.workflow.service.UserLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 任务controller
 * @author: YuKai Fan
 * @create: 2020-03-17 16:23
 **/
@RestController
@RequestMapping("/api/activiti")
public class ActivitiTaskController {
    @Autowired
    private ActivitiTaskService activitiTaskService;
    @Autowired
    private UserLeaveService userLeaveService;

    /**
     * 获取任务列表
     *
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/getTasks")
    public Result getTasks(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                           @RequestParam Map<String, Object> map) {
        map.put("userId", AppSession.getCurrentUserId());
        PageInfo<TaskEntity> pageList = activitiTaskService.getTasks(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", pageList);
    }

    /**
     * 获取待办任务列表
     *
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/getUpcomingTasks")
    public Result getUpcomingTasks(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                           @RequestParam Map<String, Object> map) {
        map.put("userId", AppSession.getCurrentUserId());
        PageInfo<TaskEntity> pageList = activitiTaskService.getUpcomingTasks(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", pageList);
    }

    /**
     * 获取已办任务列表
     *
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/getDoneTasks")
    public Result getDoneTasks(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                           @RequestParam Map<String, Object> map) {
        map.put("userId", AppSession.getCurrentUserId());
        PageInfo<TaskEntity> pageList = activitiTaskService.getDoneTasks(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", pageList);
    }

    /**
     * 重新提交更新任务
     *
     * @param map
     * @param taskId      任务id
     * @param type        业务id
     * @param pass        是否通过标识
     * @return
     */
    @PutMapping("/updateTask/{taskId}/{type}/{flag}")
    public Result getTasks(@RequestBody Map<String, Object> map, @PathVariable("taskId") String taskId,
                           @PathVariable("type") String type, @PathVariable("pass") boolean pass) {

        Map<String, Object> variable = Maps.newHashMap();
        switch (type) {
            case "leave":
                userLeaveService.editUserLeave(MapUtil.mapToObject(UserLeave.class, map, false));
                variable.put("pass", pass);
                break;
        }

        //受理后, 任务列表数据减少
        activitiTaskService.completeTask(taskId, variable);
        return new Result(HttpStatus.OK.value(), "更新任务成功");
    }

    /**
     * 完成审批任务
     * @param taskId
     * @param pass
     * @param map
     * @return
     */
    @PostMapping("/completeTask/{taskId}/{pass}")
    public Result completeTask(@PathVariable("taskId") String taskId,
                               @PathVariable("pass") boolean pass,
                               @RequestBody Map<String, Object> map) {

        activitiTaskService.handleTask(taskId, pass, map);
        Map<String, Object> variables = activitiTaskService.getVariables(taskId);

        //处理当前节点信息
        map.put("createTime", new Date());
        map.put("userId", AppSession.getCurrentUserId());
        map.put("userName", AppSession.getCurrentUser().getUsername());
        map.put("pass", pass);

        Map<String, Object> taskInfo = Maps.newHashMap();
        taskInfo.put("pass", pass);
        //判断是否已经拒绝过一次
        Object alreadyNo = variables.get("alreadyNo");
        if (alreadyNo != null && (boolean) alreadyNo && (!pass)) {//如果之前已经拒绝过,再次申请，直接结束流程
            taskInfo.put("approve", -1);
        } else {
            if (pass) {
                taskInfo.put("approve", 1); //通过下一节点
            } else {
                taskInfo.put("approve", 0); //不通过
            }
        }

        List<Map<String, Object>> approveMsgList = Lists.newArrayList();
        Object o = variables.get(ActivitiConstant.APPROVAL_MESSAGE);
        if (o != null) {
            approveMsgList = (List<Map<String, Object>>) o;
        }
        approveMsgList.add(map);

        taskInfo.put(ActivitiConstant.APPROVAL_MESSAGE, approveMsgList);

        activitiTaskService.completeTask(taskId, taskInfo);
        return new Result(HttpStatus.OK.value(), pass?"通过":"驳回");
    }

    /**
     * 获取流程高亮图片
     * @param request
     * @param response
     * @param processInstanceId
     * @return
     */
    @GetMapping("/getHighLightProcImage/{processInstanceId}")
    public Result getHighLightProcImage(HttpServletRequest request, HttpServletResponse response, @PathVariable("processInstanceId") String processInstanceId) {
        Map<String, Object> highLightProcImage = activitiTaskService.getHighLightProcImage(request, response, processInstanceId);
        return new Result(HttpStatus.OK.value(), "获取成功", highLightProcImage);
    }

    /**
     * 根据流程实例id获取审批信息
     * @param processInstanceId
     * @return
     */
    @GetMapping("/getApproveInfo/{processInstanceId}")
    public Result getApproveInfo(@PathVariable("processInstanceId") String processInstanceId) {
        List<Map<String, Object>> list = activitiTaskService.getApproveInfo(processInstanceId);
        return new Result(HttpStatus.OK.value(), "获取成功", list);
    }
}