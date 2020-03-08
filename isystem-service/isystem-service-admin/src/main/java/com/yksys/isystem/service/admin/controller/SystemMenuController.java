package com.yksys.isystem.service.admin.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.SystemApi;
import com.yksys.isystem.common.pojo.SystemAuthority;
import com.yksys.isystem.common.pojo.SystemMenu;
import com.yksys.isystem.common.vo.SystemMenuVo;
import com.yksys.isystem.service.admin.service.SystemApiService;
import com.yksys.isystem.service.admin.service.SystemAuthorityService;
import com.yksys.isystem.service.admin.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 系统菜单表
 * @author: YuKai Fan
 * @create: 2020-03-08 13:52:22
 *
 */
@RestController
@RequestMapping("/api/systemMenu")
public class SystemMenuController {
    @Autowired
    private SystemMenuService systemMenuService;
    @Autowired
    private SystemApiService systemApiService;
    @Autowired
    private SystemAuthorityService systemAuthorityService;

    /**
     * 新增系统菜单表
     * @return
     */
    @PostMapping("/addSystemMenu")
    public Result addSystemMenu(@RequestBody SystemMenuVo systemMenuVo) {
        //新增菜单
        SystemMenu systemMenu = systemMenuVo.convert();
        systemMenu = systemMenuService.addSystemMenu(systemMenu);
        //新增api
        SystemApi systemApi = systemMenuVo.getSystemApiVo().convert();
        systemApi = systemApiService.addSystemApi(systemApi);
        //新增authority
        SystemAuthority systemAuthority = systemMenuVo.getSystemAuthorityVo().convert();
        systemAuthority.setApiId(systemApi.getId());
        systemAuthority.setMenuId(systemMenu.getId());
        systemAuthorityService.addSystemAuthority(systemAuthority);
        return new Result(HttpStatus.OK.value(), "新增成功");
    }

    /**
     * 根据id查询系统菜单表
     * @param id
     * @return
     */
    @GetMapping("/getSystemMenuById")
    public Result getSystemMenuById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", systemMenuService.getSystemMenuById(id));
    }

    /**
     * 更新系统菜单表
     *
     * @return
     */
    @PutMapping("/editSystemMenu")
    public Result editSystemMenu(@RequestBody SystemMenuVo systemMenuVo) {
        SystemMenu systemMenu = systemMenuVo.convert();
        systemMenuService.editSystemMenu(systemMenu);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除系统菜单表
     * @param id
     * @return
     */
    @DeleteMapping("/delSystemMenu")
    public Result delSystemMenuById(@RequestParam String id) {
        systemMenuService.delSystemMenuById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除系统菜单表
     * @param ids
     * @return
     */
    @DeleteMapping("/delSystemMenu/{ids}")
    public Result delSystemMenuByIds(@PathVariable("ids") String[] ids) {
        systemMenuService.delSystemMenuByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的系统菜单表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemMenus/noPage")
    public Result getSystemMenus(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", systemMenuService.getSystemMenus(map));
    }

    /**
     * 获取所有系统菜单表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getSystemMenus")
    public Result getSystemMenus(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = systemMenuService.getSystemMenus(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
