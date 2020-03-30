package com.yksys.isystem.service.message.task;

import com.yksys.isystem.common.model.BaseMessage;
import com.yksys.isystem.service.message.exchanger.MessageExchanger;

import java.util.concurrent.Callable;

/**
 * @program: YK-iSystem
 * @description: 消息任务
 * @author: YuKai Fan
 * @create: 2020-03-30 17:14
 **/
public class MessageTask implements Callable<Boolean> {

    private MessageExchanger exchanger;

    private BaseMessage message;

    public MessageTask(MessageExchanger exchanger, BaseMessage message) {
        this.exchanger = exchanger;
        this.message = message;
    }

    @Override
    public Boolean call() throws Exception {
        return exchanger.exchange(message);
    }
}