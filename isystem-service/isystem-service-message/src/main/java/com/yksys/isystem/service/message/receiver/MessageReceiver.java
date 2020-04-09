package com.yksys.isystem.service.message.receiver;

import org.springframework.amqp.core.Message;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-09 15:39
 **/
public interface MessageReceiver {

    /**
     * mq消息接收处理
     * @param message
     * @return
     */
    void process(Message message);
}