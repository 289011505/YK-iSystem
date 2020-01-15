package com.yksys.isystem.service.system.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.GatewayRoute;
import com.yksys.isystem.service.system.mapper.GatewayRouteMapper;
import com.yksys.isystem.service.system.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class GatewayRouteServiceImpl implements GatewayRouteService {
    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    @Override
    public GatewayRoute addGatewayRoute(GatewayRoute gatewayRoute) {
        gatewayRoute.setId(AppUtil.randomId());
        gatewayRoute.setStatus(1);
        gatewayRoute.setServiceId(gatewayRoute.getRouteName());
        gatewayRouteMapper.addGatewayRoute(gatewayRoute);
        return gatewayRoute;
    }

    @Override
    public Map<String, Object> getGatewayRouteById(String id) {
        return gatewayRouteMapper.getGatewayRouteById(id);
    }

    @Override
    public List<Map<String, Object>> getGatewayRoutes(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getGatewayRoutes(map);
    }

    @Override
    public List<Map<String, Object>> getGatewayRoutes(Map<String, Object> map) {
        return gatewayRouteMapper.getGatewayRoutes(map);
    }

    @Override
    public void editGatewayRoute(GatewayRoute gatewayRoute) {
        gatewayRouteMapper.editGatewayRouteById(gatewayRoute);
    }

    @Override
    public void delGatewayRouteById(String id) {
        gatewayRouteMapper.delGatewayRouteById(id);
    }

    @Override
    public void delGatewayRouteByIs(List<String> ids) {
        gatewayRouteMapper.delGatewayRouteByIds(ids);
    }

    @Override
    public void delGatewayRouteRealById(String id) {
        gatewayRouteMapper.delGatewayRouteRealById(id);
    }

    @Override
    public void delGatewayRouteRealByIds(List<String> ids) {
        gatewayRouteMapper.delGatewayRouteRealByIds(ids);
    }

}
