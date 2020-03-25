package com.yksys.isystem.service.system.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.ActionLog;
import com.yksys.isystem.common.vo.ActionLogVo;
import com.yksys.isystem.service.system.service.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 操作日志表
 * @author: YuKai Fan
 * @create: 2020-03-25 21:00:39
 *
 */
@RestController
@RequestMapping("/api/actionLog")
public class ActionLogController {
    @Autowired
    private ActionLogService actionLogService;

    /**
     * 新增操作日志表
     * @return
     */
    @PostMapping("/addActionLog")
    public Result addActionLog(@RequestBody ActionLogVo actionLogVo) {
        ActionLog actionLog = actionLogVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", actionLogService.addActionLog(actionLog));
    }

    /**
     * 根据id查询操作日志表
     * @param id
     * @return
     */
    @GetMapping("/getActionLogById")
    public Result getActionLogById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", actionLogService.getActionLogById(id));
    }

    /**
     * 更新操作日志表
     *
     * @return
     */
    @PutMapping("/editActionLog")
    public Result editActionLog(@RequestBody ActionLogVo actionLogVo) {
        ActionLog actionLog = actionLogVo.convert();
        actionLogService.editActionLog(actionLog);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除操作日志表
     * @param id
     * @return
     */
    @DeleteMapping("/delActionLog")
    public Result delActionLogById(@RequestParam String id) {
        actionLogService.delActionLogById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除操作日志表
     * @param ids
     * @return
     */
    @DeleteMapping("/delActionLog/{ids}")
    public Result delActionLogByIds(@PathVariable("ids") String[] ids) {
        actionLogService.delActionLogByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的操作日志表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getActionLogs/noPage")
    public Result getActionLogs(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", actionLogService.getActionLogs(map));
    }

    /**
     * 获取所有操作日志表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getActionLogs")
    public Result getActionLogs(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = actionLogService.getActionLogs(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
