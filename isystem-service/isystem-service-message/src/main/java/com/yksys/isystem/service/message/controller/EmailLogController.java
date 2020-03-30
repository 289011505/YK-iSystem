package com.yksys.isystem.service.message.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.EmailLog;
import com.yksys.isystem.common.vo.EmailLogVo;
import com.yksys.isystem.service.message.service.EmailLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 邮件日志表
 * @author: YuKai Fan
 * @create: 2020-03-30 20:49:36
 *
 */
@RestController
@RequestMapping("/api/emailLog")
public class EmailLogController {
    @Autowired
    private EmailLogService emailLogService;

    /**
     * 新增邮件日志表
     * @return
     */
    @PostMapping("/addEmailLog")
    public Result addEmailLog(@RequestBody EmailLogVo emailLogVo) {
        EmailLog emailLog = emailLogVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", emailLogService.addEmailLog(emailLog));
    }

    /**
     * 根据id查询邮件日志表
     * @param id
     * @return
     */
    @GetMapping("/getEmailLogById")
    public Result getEmailLogById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", emailLogService.getEmailLogById(id));
    }

    /**
     * 更新邮件日志表
     *
     * @return
     */
    @PutMapping("/editEmailLog")
    public Result editEmailLog(@RequestBody EmailLogVo emailLogVo) {
        EmailLog emailLog = emailLogVo.convert();
        emailLogService.editEmailLog(emailLog);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除邮件日志表
     * @param id
     * @return
     */
    @DeleteMapping("/delEmailLog")
    public Result delEmailLogById(@RequestParam String id) {
        emailLogService.delEmailLogById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除邮件日志表
     * @param ids
     * @return
     */
    @DeleteMapping("/delEmailLog/{ids}")
    public Result delEmailLogByIds(@PathVariable("ids") String[] ids) {
        emailLogService.delEmailLogByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的邮件日志表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getEmailLogs/noPage")
    public Result getEmailLogs(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", emailLogService.getEmailLogs(map));
    }

    /**
     * 获取所有邮件日志表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getEmailLogs")
    public Result getEmailLogs(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = emailLogService.getEmailLogs(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
