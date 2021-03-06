package com.yksys.isystem.service.admin.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.SystemAuthority;
import com.yksys.isystem.common.vo.SystemAuthorityVo;
import com.yksys.isystem.service.admin.service.SystemAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 系统权限表
 * @author: YuKai Fan
 * @create: 2020-01-16 14:11:47
 *
 */
@RestController
@RequestMapping("/api/systemAuthority")
public class SystemAuthorityController {
    @Autowired
    private SystemAuthorityService systemAuthorityService;

    /**
     * 新增系统权限表
     * @return
     */
    @PostMapping("/addSystemAuthority")
    public Result addSystemAuthority(@RequestBody SystemAuthorityVo systemAuthorityVo) {
        SystemAuthority systemAuthority = systemAuthorityVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", systemAuthorityService.addSystemAuthority(systemAuthority));
    }

    /**
     * 根据id查询系统权限表
     * @param id
     * @return
     */
    @PostMapping("/getSystemAuthorityById")
    public Result getSystemAuthorityById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", systemAuthorityService.getSystemAuthorityById(id));
    }

    /**
     * 更新系统权限表
     *
     * @return
     */
    @PutMapping("/editSystemAuthority")
    public Result editSystemAuthority(@RequestBody SystemAuthorityVo systemAuthorityVo) {
        SystemAuthority systemAuthority = systemAuthorityVo.convert();
        systemAuthorityService.editSystemAuthority(systemAuthority);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除系统权限表
     * @param id
     * @return
     */
    @DeleteMapping("/delSystemAuthority")
    public Result delSystemAuthorityById(@RequestParam String id) {
        systemAuthorityService.delSystemAuthorityById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除系统权限表
     * @param ids
     * @return
     */
    @DeleteMapping("/delSystemAuthority/{ids}")
    public Result delSystemAuthorityByIds(@PathVariable("ids") String[] ids) {
        systemAuthorityService.delSystemAuthorityByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的系统权限表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemAuthorities/noPage")
    public Result getSystemAuthorities(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", systemAuthorityService.getSystemAuthorities(map));
    }

    /**
     * 获取所有系统权限表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemAuthorities")
    public Result getSystemAuthorities(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = systemAuthorityService.getSystemAuthorities(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
