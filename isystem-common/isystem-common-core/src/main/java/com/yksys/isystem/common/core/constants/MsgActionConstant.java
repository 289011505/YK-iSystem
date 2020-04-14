package com.yksys.isystem.common.core.constants;

/**
 * @program: yk-isystem
 * @description: 消息操作类型
 * @author: YuKai Fan
 * @create: 2020-04-14 23:28
 **/
public class MsgActionConstant {
    //第一次(或重连)初始化连接
    public static final String CONNECT = "connect";

    //消息发送
    public static final String SEND = "send";

    //消息签收
    public static final String SIGNED = "signed";

    //客户端保持心跳
    public static final String KEEP_ALIVE = "keep_alive";
}