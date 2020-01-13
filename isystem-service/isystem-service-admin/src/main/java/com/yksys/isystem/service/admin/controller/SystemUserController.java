package com.yksys.isystem.service.admin.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.common.vo.SystemUserVo;
import com.yksys.isystem.service.admin.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-04 09:06
 **/
@RestController
@RequestMapping("/api/systemUser")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    /**
     * 新增用户
     * @return
     */
    @PostMapping("/addSystemUser")
    public Result addSystemUser(@RequestBody SystemUserVo systemUserVo) {
        SystemUser systemUser = systemUserVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", systemUserService.addSystemUser(systemUser));
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @PostMapping("/getSystemUserById")
    public Result getSystemUserById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", systemUserService.getSystemUserById(id));
    }

    /**
     * 更新用户
     *
     * @return
     */
    @PutMapping("/editSystemUser")
    public Result editSystemUser(@RequestBody SystemUserVo systemUserVo) {
        SystemUser systemUser = systemUserVo.convert();
        systemUserService.editSystemUser(systemUser);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delSystemUser")
    public Result delSystemUserById(@RequestParam String id) {
        systemUserService.delSystemUserById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据id删除用户
     * @param ids
     * @return
     */
    @DeleteMapping("/delSystemUser/{ids}")
    public Result delSystemUserByIds(@PathVariable("ids") String[] ids) {
        systemUserService.delSystemUserByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的用户(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemUsers/noPage")
    public Result getSystemUsers(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", systemUserService.getSystemUsers(map));
    }

    /**
     * 获取所有用户
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemUsers")
    public Result getSystemUsers(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = systemUserService.getSystemUsers(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}