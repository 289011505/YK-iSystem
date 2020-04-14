package com.yksys.isystem.common.model.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: yk-isystem
 * @description: 聊天内容model
 * @author: YuKai Fan
 * @create: 2020-04-14 21:40
 **/
@Data
public class ChatMessageContent extends MessageContent implements Serializable {
    private static final long serialVersionUID = -7696649859983775088L;

    //发送者用户标识
    private String senderId;
    //接受者用户标识
    private String receiverId;
    //发送者名称
    private String userName;
    //接收者头像
    private String receiverHeadPic;
    //发送者头像
    private String senderHeadPic;
}