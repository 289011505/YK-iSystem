package com.yksys.isystem.service.message.netty.handler;

import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.RedisUtil;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 群组通道关系
 * @author: YuKai Fan
 * @create: 2020-04-15 09:22
 **/
@Component
public class GroupChannelRelHandler {
    private static Map<String, Channel> manager = Maps.newConcurrentMap();

    /**
     * 保存群组id, channel
     * @param groupId
     * @param channel
     */
    public void put(String groupId, Channel channel) {
        manager.put(groupId, channel);
    }

    /**
     * 根据groupId获取channel
     * @param groupId
     * @return
     */
    public Channel get(String groupId) {
        return manager.get(groupId);
    }

    /**
     * 移除群组通道
     * @param groupId
     */
    public void remove(String groupId) {
        manager.remove(groupId);
    }

    /**
     * 判断群组是否已连接
     * @param groupId
     * @return
     */
    public boolean checkUserIsExistRelByUserId(String groupId) {
        return manager.containsKey(groupId);
    }

}