package com.yksys.isystem.service.admin.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.SystemApi;
import com.yksys.isystem.common.vo.SystemApiVo;
import com.yksys.isystem.service.admin.service.SystemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 网关api接口表
 * @author: YuKai Fan
 * @create: 2020-01-15 20:26:43
 *
 */
@RestController
@RequestMapping("/api/systemApi")
public class SystemApiController {
    @Autowired
    private SystemApiService systemApiService;

    /**
     * 新增网关api接口表
     * @return
     */
    @PostMapping("/addSystemApi")
    public Result addSystemApi(@RequestBody SystemApiVo systemApiVo) {
        SystemApi systemApi = systemApiVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", systemApiService.addSystemApi(systemApi));
    }

    /**
     * 根据id查询网关api接口表
     * @param id
     * @return
     */
    @PostMapping("/getSystemApiById")
    public Result getSystemApiById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", systemApiService.getSystemApiById(id));
    }

    /**
     * 更新网关api接口表
     *
     * @return
     */
    @PutMapping("/editSystemApi")
    public Result editSystemApi(@RequestBody SystemApiVo systemApiVo) {
        SystemApi systemApi = systemApiVo.convert();
        systemApiService.editSystemApi(systemApi);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除网关api接口表
     * @param id
     * @return
     */
    @DeleteMapping("/delSystemApi")
    public Result delSystemApiById(@RequestParam String id) {
        systemApiService.delSystemApiById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除网关api接口表
     * @param ids
     * @return
     */
    @DeleteMapping("/delSystemApi/{ids}")
    public Result delSystemApiByIds(@PathVariable("ids") String[] ids) {
        systemApiService.delSystemApiByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的网关api接口表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemApis/noPage")
    public Result getSystemApis(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", systemApiService.getSystemApis(map));
    }

    /**
     * 获取所有网关api接口表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemApis")
    public Result getSystemApis(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = systemApiService.getSystemApis(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
