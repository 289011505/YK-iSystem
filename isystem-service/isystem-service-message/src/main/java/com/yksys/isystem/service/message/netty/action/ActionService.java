package com.yksys.isystem.service.message.netty.action;

import com.yksys.isystem.common.model.message.MessageContent;
import io.netty.channel.Channel;

/**
 * @program: yk-isystem
 * @description: 抽象操作类
 * @author: YuKai Fan
 * @create: 2020-04-14 22:56
 **/
public interface ActionService {

    /**
     * 获取对应的实体
     * @return
     */
    MessageContent getMessageContent(String text);

    /**
     * 处理消息
     * @param messageContent
     * @param channel
     */
    void doHandle(MessageContent messageContent, Channel channel);

    /**
     * 客户端连接
     * @param messageContent
     * @param channel
     */
    boolean doConnect(MessageContent messageContent, Channel channel);

    /**
     * 发送消息
     * @param messageContent
     */
    boolean doSend(MessageContent messageContent);

    /**
     * 消息签收
     * @param messageContent
     * @return
     */
    boolean doSign(MessageContent messageContent);
}