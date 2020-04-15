package com.yksys.isystem.common.model.message;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: yk-isystem
 * @description: 消息内容model
 * @author: YuKai Fan
 * @create: 2020-04-14 21:23
 **/
@Data
public class MessageContent implements Serializable {
    private static final long serialVersionUID = -2507653942138134146L;

    //操作类型
    private String action;
    //类型1: 单聊, 2: 群聊, 3: 聊天室, 4: 系统消息, 5: 实时信息消息
    private String msgType;
    //消息标识
    private String msgId;
    //消息内容类型1:文本, 2：图片, 3: 语音
    private String msgContentType;
    //发送时间
    private Long senderTime;
    //接收时间
    private String receiverTime;
    //聊天内容
    private Object content;
    //扩展字段
    private Map<String, Object> extension;
}