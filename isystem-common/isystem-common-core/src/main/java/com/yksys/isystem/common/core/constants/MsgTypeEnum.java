package com.yksys.isystem.common.core.constants;

import lombok.Getter;

/**
 * @program: YK-iSystem
 * @description: 消息类型枚举
 * @author: YuKai Fan
 * @create: 2020-04-15 08:59
 **/
@Getter
public enum MsgTypeEnum {

    SINGLE_CHAT("single_chat", "单聊"),

    GROUP_CHAT("GROUP_CHAT","群聊"),

    CHAT_ROOM("chat_room","聊天室"),

    SYSTEM_NOTICE("system_notice","系统通知"),
            ;

    private String msgType;

    private String content;

    MsgTypeEnum(String msgType, String content) {
        this.msgType = msgType;
        this.content = content;
    }
}