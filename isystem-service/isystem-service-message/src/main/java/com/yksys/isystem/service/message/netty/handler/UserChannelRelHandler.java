package com.yksys.isystem.service.message.netty.handler;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 用户通道关系
 * @author: YuKai Fan
 * @create: 2020-04-15 09:22
 **/
@Component
public class UserChannelRelHandler {
    private static Map<String, Channel> manager = Maps.newConcurrentMap();

    /**
     * 保存用户id, channel
     * @param key
     * @param channel
     */
    public void put(String key, Channel channel) {
        manager.put(key, channel);
    }

    /**
     * 根据userId获取channel
     * @param key
     * @return
     */
    public Channel get(String key) {
        return manager.get(key);
    }

    /**
     * 移除用户通道
     * @param key
     */
    public void remove(String key) {
        manager.remove(key);
    }

    /**
     * 判断用户是否已连接
     * @param key
     * @return
     */
    public boolean checkUserIsExistRelByUserId(String key) {
        return manager.containsKey(key);
    }

}