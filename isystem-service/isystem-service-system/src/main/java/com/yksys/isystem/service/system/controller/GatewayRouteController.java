package com.yksys.isystem.service.system.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.http.YkRestTemplate;
import com.yksys.isystem.common.pojo.GatewayRoute;
import com.yksys.isystem.common.vo.GatewayRouteVo;
import com.yksys.isystem.service.system.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 网关路由表
 * @author: YuKai Fan
 * @create: 2020-01-14 15:16:26
 *
 */
@RestController
@RequestMapping("/api/gatewayRoute")
public class GatewayRouteController {
    @Autowired
    private GatewayRouteService gatewayRouteService;
    @Autowired
    private YkRestTemplate ykRestTemplate;

    /**
     * 新增网关路由表
     * @return
     */
    @PostMapping("/addGatewayRoute")
    public Result addGatewayRoute(@RequestBody GatewayRouteVo gatewayRouteVo) {
        GatewayRoute gatewayRoute = gatewayRouteVo.convert();
        GatewayRoute result = gatewayRouteService.addGatewayRoute(gatewayRoute);
        //刷新网关
        ykRestTemplate.refreshGateway();
        return new Result(HttpStatus.OK.value(), "新增成功", result);
    }

    /**
     * 根据id查询网关路由表
     * @param id
     * @return
     */
    @PostMapping("/getGatewayRouteById")
    public Result getGatewayRouteById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", gatewayRouteService.getGatewayRouteById(id));
    }

    /**
     * 更新网关路由表
     *
     * @return
     */
    @PutMapping("/editGatewayRoute")
    public Result editGatewayRoute(@RequestBody GatewayRouteVo gatewayRouteVo) {
        GatewayRoute gatewayRoute = gatewayRouteVo.convert();
        gatewayRouteService.editGatewayRoute(gatewayRoute);
        //刷新网关
        ykRestTemplate.refreshGateway();
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除网关路由表
     * @param id
     * @return
     */
    @DeleteMapping("/delGatewayRoute")
    public Result delGatewayRouteById(@RequestParam String id) {
        gatewayRouteService.delGatewayRouteById(id);
        //刷新网关
        ykRestTemplate.refreshGateway();
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除网关路由表
     * @param ids
     * @return
     */
    @DeleteMapping("/delGatewayRoute/{ids}")
    public Result delGatewayRouteByIds(@PathVariable("ids") String[] ids) {
        gatewayRouteService.delGatewayRouteByIs(Arrays.asList(ids));
        //刷新网关
        ykRestTemplate.refreshGateway();
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的网关路由表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getGatewayRoutes/noPage")
    public Result getGatewayRoutes(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", gatewayRouteService.getGatewayRoutes(map));
    }

    /**
     * 获取所有网关路由表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getGatewayRoutes")
    public Result getGatewayRoutes(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = gatewayRouteService.getGatewayRoutes(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
