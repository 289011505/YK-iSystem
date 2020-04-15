package com.yksys.isystem.service.message.netty.service;

import java.util.List;

/**
 * @program: YK-iSystem
 * @description: 消息处理service
 * @author: YuKai Fan
 * @create: 2020-04-15 15:09
 **/
public interface MessageService {
    /**
     * 保存聊天未签收消息记录
     * @param userId
     * @param o
     * @return
     */
    boolean saveNotSignedMessageRecord(String userId, Object o, String prefix);

    /**
     * 保存聊天已签收消息记录
     * @param userId
     * @param o
     * @return
     */
    boolean saveSignedMessageRecord(String userId, Object o, String prefix);

    /**
     * 获取消息记录
     * @param userId 用户id
     * @param prefix 前缀
     */
    List getMessageRecord(String userId, String prefix);

    /**
     * 删除消息记录
     * @param userId 用户id
     * @param prefix 前缀
     */
    boolean delMessageRecord(String userId, String prefix);
}