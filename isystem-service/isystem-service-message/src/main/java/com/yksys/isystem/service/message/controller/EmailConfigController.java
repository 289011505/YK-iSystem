package com.yksys.isystem.service.message.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.EmailConfig;
import com.yksys.isystem.common.vo.EmailConfigVo;
import com.yksys.isystem.service.message.service.EmailConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 邮箱配置表
 * @author: YuKai Fan
 * @create: 2020-03-30 20:49:36
 *
 */
@Api(tags = "邮件配置管理")
@RestController
@RequestMapping("/api/emailConfig")
public class EmailConfigController {
    @Autowired
    private EmailConfigService emailConfigService;

    /**
     * 新增邮箱配置表
     * @return
     */
    @ApiOperation("新增邮箱配置")
    @PostMapping("/addEmailConfig")
    public Result<EmailConfig> addEmailConfig(@RequestBody @ApiParam(name = "邮件配置vo对象", required = true) EmailConfigVo emailConfigVo) {
        EmailConfig emailConfig = emailConfigVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", emailConfigService.addEmailConfig(emailConfig));
    }

    /**
     * 根据id查询邮箱配置表
     * @param id
     * @return
     */
    @ApiOperation("根据id查询邮箱配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "配置id", paramType = "string")
    })
    @GetMapping("/getEmailConfigById")
    public Result getEmailConfigById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", emailConfigService.getEmailConfigById(id));
    }

    /**
     * 更新邮箱配置表
     *
     * @return
     */
    @ApiOperation("更新邮箱配置")
    @PutMapping("/editEmailConfig")
    public Result editEmailConfig(@RequestBody @ApiParam(name = "邮件配置vo对象", required = true) EmailConfigVo emailConfigVo) {
        EmailConfig emailConfig = emailConfigVo.convert();
        emailConfigService.editEmailConfig(emailConfig);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除邮箱配置表
     * @param id
     * @return
     */
    @ApiOperation("根据id删除邮箱配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "配置id", paramType = "string")
    })
    @DeleteMapping("/delEmailConfig")
    public Result delEmailConfigById(@RequestParam String id) {
        emailConfigService.delEmailConfigById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除邮箱配置表
     * @param ids
     * @return
     */
    @ApiOperation("根据ids批量删除邮箱配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "配置id数组", paramType = "string[]")
    })
    @DeleteMapping("/delEmailConfig/{ids}")
    public Result delEmailConfigByIds(@PathVariable("ids") String[] ids) {
        emailConfigService.delEmailConfigByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的邮箱配置表(不分页)
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有的邮箱配置(不分页)")
    @GetMapping("/getEmailConfigs/noPage")
    public Result getEmailConfigs(@RequestParam(required = false) Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", emailConfigService.getEmailConfigs(map));
    }

    /**
     * 获取所有邮箱配置表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @ApiOperation(value = "获取所有的邮箱配置", notes = "获取分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", required = true, value = "开始记录", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "分页大小", paramType = "int")
    })
    @GetMapping("/getEmailConfigs")
    public Result getEmailConfigs(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam(required = false) Map<String, Object> map) {
        List<Map<String, Object>> list = emailConfigService.getEmailConfigs(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
