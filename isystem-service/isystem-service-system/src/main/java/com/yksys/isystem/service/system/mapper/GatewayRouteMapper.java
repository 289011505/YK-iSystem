package com.yksys.isystem.service.system.mapper;

import com.yksys.isystem.common.pojo.GatewayRoute;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统网关路由表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface GatewayRouteMapper {
    /**
     * 新增网关路由表
     * @param gatewayRoute 网关路由表实体
     * @return
     */
    int addGatewayRoute(GatewayRoute gatewayRoute);

    /**
     * 批量新增网关路由表
     * @param list 网关路由表集合
     */
    void addGatewayRoutes(@Param(value = "list") List<GatewayRoute> list);

    /**
     * 根据id查询指定网关路由表
     * @param id  主键
     * @return
     */
    Map<String, Object> getGatewayRouteById(String id);

    /**
     * 根据id修改网关路由表
     * @param user 网关路由表实体
     * @return
     */
    int editGatewayRouteById(GatewayRoute gatewayRoute);

    /**
     * 批量修改网关路由表
     *
     * @param gatewayRoute 网关路由表实体
     * @param ids 主键集合
     */
    void editGatewayRouteByIds(@Param("map") GatewayRoute gatewayRoute, @Param("list") List<String> ids);

    /**
     * 根据id删除网关路由表
     * @param id
     * @return
     */
    int delGatewayRouteById(String id);

    /**
     * 批量删除网关路由表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delGatewayRouteByIds(List<String> ids);

    /**
     * 真删除网关路由表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delGatewayRouteRealById(String id);

    /**
     * 真批量删除网关路由表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delGatewayRouteRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllGatewayRouteReal();

    /**
     * 获取所有网关路由表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getGatewayRoutes(Map<String, Object> map);
}
