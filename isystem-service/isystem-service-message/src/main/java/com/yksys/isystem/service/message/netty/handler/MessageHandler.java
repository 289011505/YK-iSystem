package com.yksys.isystem.service.message.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: YK-iSystem
 * @description: 消息处理
 * @author: YuKai Fan
 * @create: 2020-04-15 09:54
 **/
public class MessageHandler {
    //用户记录和管理所有客户端的channel
    private static ChannelGroup manager = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 保存channel
     * @param channel
     */
    public static void saveChannel(Channel channel) {
        manager.add(channel);
    }

    /**
     * 根据channelId获取Channel
     * @param channelId
     * @return
     */
    public static Channel getChannel(ChannelId channelId) {
        return manager.find(channelId);
    }

    /**
     * 移除channel
     * @param channel
     */
    public static void removeChannel(Channel channel) {
        manager.remove(channel);
        channel.close();
    }

}