package com.yksys.isystem.service.message.exchanger;

import com.yksys.isystem.common.model.BaseMessage;

/**
 * @program: YK-iSystem
 * @description: 消息转换器
 * @author: YuKai Fan
 * @create: 2020-03-30 11:37
 **/
public interface MessageExchanger {

    /**
     * 判断消息类型是否支持转换
     * @param message
     * @return
     */
    boolean support(Object message);

    /**
     * 消息转换 (邮件消息， 短信消息 转换成发送模板)
     * @param message
     * @return
     */
    boolean exchange(BaseMessage message);

}