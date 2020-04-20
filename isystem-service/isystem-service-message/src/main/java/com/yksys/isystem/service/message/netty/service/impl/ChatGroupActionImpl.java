package com.yksys.isystem.service.message.netty.service.impl;

import com.yksys.isystem.common.core.constants.MsgActionConstant;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.JsonUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.common.model.message.ChatGroupMessageContent;
import com.yksys.isystem.common.model.message.MessageContent;
import com.yksys.isystem.service.message.netty.handler.GroupChannelRelHandler;
import com.yksys.isystem.service.message.netty.handler.MessageHandler;
import com.yksys.isystem.service.message.netty.handler.UserChannelRelHandler;
import com.yksys.isystem.service.message.netty.service.ActionService;
import com.yksys.isystem.service.message.netty.service.MessageService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-20 14:22
 **/
@Service
@Slf4j
public class ChatGroupActionImpl implements ActionService {
    @Autowired
    private MessageService messageService;
    @Autowired
    private GroupChannelRelHandler groupChannelRelHandler;

    @Override
    public MessageContent getMessageContent(String text) {
        return JsonUtil.jsonToPojo(text, ChatGroupMessageContent.class);
    }

    @Override
    public void doHandle(MessageContent messageContent, Channel channel) {

    }

    @Override
    public boolean doConnect(MessageContent messageContent, Channel channel) {
        //发送用户群组未签收消息
        ChatGroupMessageContent chatGroupMessageContent = (ChatGroupMessageContent) messageContent;

        return false;
    }

    @Override
    public boolean doSend(MessageContent messageContent) {
        ChatGroupMessageContent chatGroupMessageContent = (ChatGroupMessageContent) messageContent;
        String senderId = chatGroupMessageContent.getSenderId(); // 发送者id
        String groupId = chatGroupMessageContent.getGroupId(); //群组id
        try {
            //判断用户是否在群组中 todo

            //获取群组channel
            Channel groupChannel = groupChannelRelHandler.get(groupId);
            if (groupChannel != null) {
                //如果receiverChannel不为空，从channelGroup中去查找对应的channel是否存在
                Channel channel = MessageHandler.getChannel(groupChannel.id());
                if (channel != null) { // 如果接受者不在线, 就保存消息到缓存

                    log.info("用户 【{}】 发送消息成功", senderId);
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
        return false;
    }
}