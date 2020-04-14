package com.yksys.isystem.service.message.netty.action;

import com.google.common.collect.Maps;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @program: yk-isystem
 * @description: action策略工厂类
 * @author: YuKai Fan
 * @create: 2020-04-14 23:00
 **/
public class ActionStrategyFactory {
    private static Map<String, ActionService> services = Maps.newConcurrentMap();

    /**
     * 根据类型获取对应的Action实例
     * @param type
     * @return
     */
    public static ActionService getActionByType(String type) {
        return services.get(type);
    }

    public static void register(String type, ActionService actionService) {
        Assert.notNull(type, "消息类型不能为空");
        services.put(type, actionService);
    }
}