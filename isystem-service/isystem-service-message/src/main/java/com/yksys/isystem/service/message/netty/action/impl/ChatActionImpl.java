package com.yksys.isystem.service.message.netty.action.impl;

import com.yksys.isystem.common.core.constants.MsgActionConstant;
import com.yksys.isystem.common.core.utils.JsonUtil;
import com.yksys.isystem.common.model.message.ChatMessageContent;
import com.yksys.isystem.common.model.message.MessageContent;
import com.yksys.isystem.service.message.netty.action.ActionService;
import com.yksys.isystem.service.message.netty.action.ActionStrategyFactory;
import io.netty.channel.Channel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-14 23:00
 **/
@Service
public class ChatActionImpl implements ActionService, InitializingBean {
    @Override
    public MessageContent getMessageContent(String text) {
        return JsonUtil.jsonToPojo(text, ChatMessageContent.class);
    }

    @Override
    public void doHandle(MessageContent messageContent, Channel channel) {
        String action = messageContent.getAction();
        //判断操作类型
        switch (action) {
            case MsgActionConstant.CONNECT:
                this.doConnect(messageContent, channel);
                break;
            case MsgActionConstant.SEND:
                this.doSend(messageContent);
                break;
            case MsgActionConstant.SIGNED:
                this.doSend(messageContent);
                break;
        }
    }

    @Override
    public boolean doConnect(MessageContent messageContent, Channel channel) {
        return false;
    }

    @Override
    public boolean doSend(MessageContent messageContent) {
        return false;
    }

    @Override
    public boolean doSign(MessageContent messageContent) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ActionStrategyFactory.register("1", this);
    }
}