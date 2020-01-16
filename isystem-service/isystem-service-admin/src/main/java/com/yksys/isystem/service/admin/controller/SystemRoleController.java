package com.yksys.isystem.service.admin.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.SystemRole;
import com.yksys.isystem.common.vo.SystemRoleVo;
import com.yksys.isystem.service.admin.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 角色表
 * @author: YuKai Fan
 * @create: 2020-01-16 14:11:47
 *
 */
@RestController
@RequestMapping("/api/systemRole")
public class SystemRoleController {
    @Autowired
    private SystemRoleService systemRoleService;

    /**
     * 新增角色表
     * @return
     */
    @PostMapping("/addSystemRole")
    public Result addSystemRole(@RequestBody SystemRoleVo systemRoleVo) {
        SystemRole systemRole = systemRoleVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", systemRoleService.addSystemRole(systemRole));
    }

    /**
     * 根据id查询角色表
     * @param id
     * @return
     */
    @PostMapping("/getSystemRoleById")
    public Result getSystemRoleById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", systemRoleService.getSystemRoleById(id));
    }

    /**
     * 更新角色表
     *
     * @return
     */
    @PutMapping("/editSystemRole")
    public Result editSystemRole(@RequestBody SystemRoleVo systemRoleVo) {
        SystemRole systemRole = systemRoleVo.convert();
        systemRoleService.editSystemRole(systemRole);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除角色表
     * @param id
     * @return
     */
    @DeleteMapping("/delSystemRole")
    public Result delSystemRoleById(@RequestParam String id) {
        systemRoleService.delSystemRoleById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除角色表
     * @param ids
     * @return
     */
    @DeleteMapping("/delSystemRole/{ids}")
    public Result delSystemRoleByIds(@PathVariable("ids") String[] ids) {
        systemRoleService.delSystemRoleByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的角色表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemRoles/noPage")
    public Result getSystemRoles(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", systemRoleService.getSystemRoles(map));
    }

    /**
     * 获取所有角色表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemRoles")
    public Result getSystemRoles(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = systemRoleService.getSystemRoles(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
