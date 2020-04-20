package com.yksys.isystem.service.message.netty.service.impl;

import com.yksys.isystem.common.core.constants.MsgActionConstant;
import com.yksys.isystem.common.core.constants.MsgTypeEnum;
import com.yksys.isystem.common.core.utils.JsonUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.common.model.message.MessageContent;
import com.yksys.isystem.common.model.message.SystemNoticeContent;
import com.yksys.isystem.service.message.netty.handler.UserChannelRelHandler;
import com.yksys.isystem.service.message.netty.service.ActionService;
import com.yksys.isystem.service.message.netty.handler.MessageHandler;
import com.yksys.isystem.service.message.netty.service.factory.ActionStrategyFactory;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-15 11:21
 **/
@Service
@Slf4j
public class SystemNoticeActionImpl implements ActionService, InitializingBean{
    @Autowired
    private UserChannelRelHandler userChannelRelHandler;
    @Override
    public MessageContent getMessageContent(String text) {
        return JsonUtil.jsonToPojo(text, SystemNoticeContent.class);
    }

    @Override
    public void doHandle(MessageContent messageContent, Channel channel) {
        if (messageContent == null) {
            log.error("发送消息错误, 消息内容为空!! messageContent: {}", messageContent);
            return;
        }
        String action = messageContent.getAction();
        //判断操作类型
        switch (action) {
            case MsgActionConstant.SEND:
                this.doSend(messageContent);
                break;
            case MsgActionConstant.SIGNED:
                this.doSign(messageContent);
                break;
        }
    }

    @Override
    public boolean doConnect(MessageContent messageContent, Channel channel) {
        return true;
    }

    @Override
    public boolean doSend(MessageContent messageContent) {
        SystemNoticeContent systemNoticeContent = (SystemNoticeContent) messageContent;
        String receiverId = systemNoticeContent.getReceiverId(); // 接受者id

        try {
            //发送消息
            //获取接受者channel
            Channel receiverChannel = userChannelRelHandler.get(receiverId);
            if (receiverChannel != null) {
                //如果receiverChannel不为空，从channelGroup中去查找对应的channel是否存在
                Channel channel = MessageHandler.getChannel(receiverChannel.id());
                if (channel != null) {
                    systemNoticeContent.setSenderTime(TimeUtil.getCurrentTimestamp());
                    channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.objectToJson(systemNoticeContent)));

                    log.info("发送系统通知消息成功, 接受用户为【{}】", receiverId);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送消息错误, {}", e.getMessage());

            return false;
        }
    }

    @Override
    public boolean doSign(MessageContent messageContent) {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ActionStrategyFactory.register(MsgTypeEnum.SYSTEM_NOTICE.getMsgType(), this);
    }
}