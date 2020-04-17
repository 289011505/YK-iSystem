package com.yksys.isystem.service.admin.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.pojo.UserIntroduce;
import com.yksys.isystem.common.vo.UserIntroduceVo;
import com.yksys.isystem.service.admin.service.UserIntroduceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 用户简介表
 * @author: YuKai Fan
 * @create: 2020-04-16 21:06:52
 *
 */
@Api(tags = "用户简介表")
@RestController
@RequestMapping("/api/userIntroduce")
public class UserIntroduceController {
    @Autowired
    private UserIntroduceService userIntroduceService;

    /**
     * 新增用户简介表
     * @return
     */
    @ApiOperation("新增用户简介表")
    @PostMapping("/addUserIntroduce")
    public Result addUserIntroduce(@RequestBody @ApiParam(name = "用户简介表vo对象", required = true) UserIntroduceVo userIntroduceVo) {
        UserIntroduce userIntroduce = userIntroduceVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", userIntroduceService.addUserIntroduce(userIntroduce));
    }

    /**
     * 根据id查询用户简介表
     * @param id
     * @return
     */
    @ApiOperation("根据id查询用户简介表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @GetMapping("/getUserIntroduceById")
    public Result getUserIntroduceById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", userIntroduceService.getUserIntroduceById(id));
    }

    /**
     * 更新用户简介表
     *
     * @return
     */
    @ApiOperation("更新用户简介表")
    @PutMapping("/editUserIntroduce")
    public Result editUserIntroduce(@RequestBody @ApiParam(name = "用户简介表vo对象", required = true) UserIntroduceVo userIntroduceVo) {
        UserIntroduce userIntroduce = userIntroduceVo.convert();
        userIntroduceService.editUserIntroduce(userIntroduce);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除用户简介表
     * @param id
     * @return
     */
    @ApiOperation("根据id删除用户简介表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @DeleteMapping("/delUserIntroduce")
    public Result delUserIntroduceById(@RequestParam String id) {
        userIntroduceService.delUserIntroduceById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除用户简介表
     * @param ids
     * @return
     */
    @ApiOperation("根据ids批量删除用户简介表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "id数组", paramType = "string[]")
    })
    @DeleteMapping("/delUserIntroduce/{ids}")
    public Result delUserIntroduceByIds(@PathVariable("ids") String[] ids) {
        userIntroduceService.delUserIntroduceByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的用户简介表(不分页)
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有的用户简介表(不分页)")
    @GetMapping("/getUserIntroduces/noPage")
    public Result getUserIntroduces(@RequestParam Map<String, Object> map) {
        map.put("userId", AppSession.getCurrentUserId());
        return new Result(HttpStatus.OK.value(), "获取成功", userIntroduceService.getUserIntroduces(map));
    }

    /**
     * 获取所有用户简介表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有用户简介表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", required = true, value = "开始记录", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "分页大小", paramType = "int")
    })
    @GetMapping("/getUserIntroduces")
    public Result getUserIntroduces(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = userIntroduceService.getUserIntroduces(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
