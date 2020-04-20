package com.yksys.isystem.common.model.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description: 群聊model
 * @author: YuKai Fan
 * @create: 2020-04-20 14:23
 **/
@Data
public class ChatGroupMessageContent extends MessageContent implements Serializable {
    private static final long serialVersionUID = -4866841483695298547L;

    //群id
    private String groupId;
    //发送者id
    private String senderId;
    //发送者名称
    private String userName;
    //接收者头像
    private String groupHeadPic;
    //发送者头像
    private String senderHeadPic;
}