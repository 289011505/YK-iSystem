package com.yksys.isystem.service.system.service;
import com.yksys.isystem.common.pojo.GatewayRoute;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 网关路由表 service
 * @author: YuKai Fan
 * @create: 2020-01-14 15:16:26
 **/
public interface GatewayRouteService {
    /**
     * 新增网关路由表
     * @param gatewayRoute
     * @return
     */
    GatewayRoute addGatewayRoute(GatewayRoute gatewayRoute);

    /**
     * 根据id查询网关路由表
     * @param id
     * @return
     */
    Map<String, Object> getGatewayRouteById(String id);

    /**
     * 获取所有网关路由表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getGatewayRoutes(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有网关路由表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getGatewayRoutes(Map<String, Object> map);

    /**
     * 修改网关路由表
     * @param gatewayRoute
     */
    void editGatewayRoute(GatewayRoute gatewayRoute);

    /**
     * 根据id删除网关路由表(软删除)
     * @param id
     */
    void delGatewayRouteById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delGatewayRouteByIs(List<String> ids);

    /**
     * 根据id删除网关路由表(真删除)
     * @param id
     */
    void delGatewayRouteRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delGatewayRouteRealByIds(List<String> ids);
}
