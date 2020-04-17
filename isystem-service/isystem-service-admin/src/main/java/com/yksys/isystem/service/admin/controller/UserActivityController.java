package com.yksys.isystem.service.admin.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.UserActivity;
import com.yksys.isystem.common.vo.UserActivityVo;
import com.yksys.isystem.service.admin.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 用户动态表
 * @author: YuKai Fan
 * @create: 2020-04-17 09:45:03
 *
 */
@Api(tags = "用户动态表")
@RestController
@RequestMapping("/api/userActivity")
public class UserActivityController {
    @Autowired
    private UserActivityService userActivityService;

    /**
     * 新增用户动态表
     * @return
     */
    @ApiOperation("新增用户动态表")
    @PostMapping("/addUserActivity")
    public Result addUserActivity(@RequestBody @ApiParam(name = "用户动态表vo对象", required = true) UserActivityVo userActivityVo) {
        UserActivity userActivity = userActivityVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", userActivityService.addUserActivity(userActivity));
    }

    /**
     * 根据id查询用户动态表
     * @param id
     * @return
     */
    @ApiOperation("根据id查询用户动态表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @GetMapping("/getUserActivityById")
    public Result getUserActivityById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", userActivityService.getUserActivityById(id));
    }

    /**
     * 更新用户动态表
     *
     * @return
     */
    @ApiOperation("更新用户动态表")
    @PutMapping("/editUserActivity")
    public Result editUserActivity(@RequestBody @ApiParam(name = "用户动态表vo对象", required = true) UserActivityVo userActivityVo) {
        UserActivity userActivity = userActivityVo.convert();
        userActivityService.editUserActivity(userActivity);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除用户动态表
     * @param id
     * @return
     */
    @ApiOperation("根据id删除用户动态表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @DeleteMapping("/delUserActivity")
    public Result delUserActivityById(@RequestParam String id) {
        userActivityService.delUserActivityById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除用户动态表
     * @param ids
     * @return
     */
    @ApiOperation("根据ids批量删除用户动态表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "id数组", paramType = "string[]")
    })
    @DeleteMapping("/delUserActivity/{ids}")
    public Result delUserActivityByIds(@PathVariable("ids") String[] ids) {
        userActivityService.delUserActivityByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的用户动态表(不分页)
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有的用户动态表(不分页)")
    @GetMapping("/getUserActivitys/noPage")
    public Result getUserActivitys(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", userActivityService.getUserActivitys(map));
    }

    /**
     * 获取所有用户动态表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有用户动态表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", required = true, value = "开始记录", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "分页大小", paramType = "int")
    })
    @GetMapping("/getUserActivitys")
    public Result getUserActivitys(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = userActivityService.getUserActivitys(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
