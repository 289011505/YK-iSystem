package com.yksys.isystem.service.message.netty.service.impl;

import com.yksys.isystem.common.core.constants.MsgActionConstant;
import com.yksys.isystem.common.core.constants.MsgTypeEnum;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.JsonUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import com.yksys.isystem.common.model.message.ChatMessageContent;
import com.yksys.isystem.common.model.message.MessageContent;
import com.yksys.isystem.service.message.netty.handler.UserChannelRelHandler;
import com.yksys.isystem.service.message.netty.service.ActionService;
import com.yksys.isystem.service.message.netty.service.MessageService;
import com.yksys.isystem.service.message.netty.service.factory.ActionStrategyFactory;
import com.yksys.isystem.service.message.netty.handler.MessageHandler;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: yk-isystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-14 23:00
 **/
@Slf4j
@Service
public class ChatActionImpl implements ActionService, InitializingBean {
    @Autowired
    private UserChannelRelHandler userChannelRelHandler;
    @Autowired
    private MessageService messageService;

    @Override
    public MessageContent getMessageContent(String text) {
        return JsonUtil.jsonToPojo(text, ChatMessageContent.class);
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
            case MsgActionConstant.CONNECT:
                this.doConnect(messageContent, channel);
                break;
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
        ChatMessageContent chatMessageContent = (ChatMessageContent) messageContent;
        //判断用户id是否为空 todo

        //判断用户是否存在 todo

        userChannelRelHandler.put(chatMessageContent.getSenderId(), channel);

        log.info("用户: {}, 单聊连接成功", chatMessageContent.getSenderId());

        //连接成功, 给用户发送未签收的消息
        List notSignRecordList = messageService.getMessageRecord(chatMessageContent.getSenderId(), RedisConstants.SINGLE_CHAT_RECORD);

        //发送客户端签收
        if (CollectionUtils.isEmpty(notSignRecordList)) {
            return true;
        }

        log.info("对用户 【{}】 发送签收消息", chatMessageContent.getSenderId());
        chatMessageContent.setAction(MsgActionConstant.SIGNED);
        chatMessageContent.setContent(notSignRecordList);
        chatMessageContent.setReceiverId(chatMessageContent.getSenderId());
        doSend(chatMessageContent);

        return true;
    }

    @Override
    public boolean doSend(MessageContent messageContent) {
        ChatMessageContent chatMessageContent = (ChatMessageContent) messageContent;
        String receiverId = chatMessageContent.getReceiverId(); // 接受者id
        String senderId = chatMessageContent.getSenderId(); // 发送者id

        try {
            if (!userChannelRelHandler.checkUserIsExistRelByUserId(senderId)) {
                log.error("发送失败, 当前用户 【{}】 未连接成功", senderId);
                return false;
            }

            //发送消息
            //获取接受者channel
            Channel receiverChannel = userChannelRelHandler.get(receiverId);
            if (receiverChannel != null) { // 如果接受者不在线, 就保存消息到缓存 todo
                //如果receiverChannel不为空，从channelGroup中去查找对应的channel是否存在
                Channel channel = MessageHandler.getChannel(receiverChannel.id());
                if (channel != null) { // 如果接受者不在线, 就保存消息到缓存
                    chatMessageContent.setSenderTime(TimeUtil.getCurrentTimestamp());
                    channel.writeAndFlush(new TextWebSocketFrame(JsonUtil.objectToJson(chatMessageContent)));

                    log.info("用户 【{}】 发送消息成功", senderId);
                }
            }

            if (MsgActionConstant.SEND.equals(chatMessageContent.getAction())) {
                //保存消息到缓存
                messageService.saveNotSignedMessageRecord(receiverId, chatMessageContent, RedisConstants.SINGLE_CHAT_RECORD);
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
        ChatMessageContent chatMessageContent = (ChatMessageContent) messageContent;
        try {
            //给用户发送未签收的消息
            List notSignRecordList = messageService.getMessageRecord(chatMessageContent.getSenderId(), RedisConstants.SINGLE_CHAT_RECORD);

            //将未签收消息, 放入已签收消息中
            boolean b = messageService.saveSignedMessageRecord(chatMessageContent.getSenderId(), notSignRecordList, RedisConstants.SINGLE_CHAT_RECORD_SIGNED);

            //签收成功, 删除未签收的消息
            if (b) {
                log.info("用户 【{}】 签收消息成功", chatMessageContent.getSenderId());
                return messageService.delMessageRecord(chatMessageContent.getSenderId(), RedisConstants.SINGLE_CHAT_RECORD);
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户 【{}】 签收消息失败", chatMessageContent.getSenderId());
            return false;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ActionStrategyFactory.register(MsgTypeEnum.SINGLE_CHAT.getMsgType(), this);
    }
}