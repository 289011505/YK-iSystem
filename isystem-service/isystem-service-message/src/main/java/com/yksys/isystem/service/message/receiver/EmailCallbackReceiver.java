package com.yksys.isystem.service.message.receiver;

import com.yksys.isystem.common.core.constants.RabbitConstant;
import com.yksys.isystem.common.core.utils.ObjectConvertUtil;
import com.yksys.isystem.common.model.EmailTplMessage;
import com.yksys.isystem.service.message.dispatcher.MessageDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-09 15:39
 **/
@Component
@Slf4j
public class EmailCallbackReceiver implements MessageReceiver {
    @Autowired
    private MessageDispatcher messageDispatcher;

    @Override
    @RabbitListener(queues = RabbitConstant.REGISTER_CALLBACK_EMAIL_NOTIFY_QUEUE)
    public void process(Message message) {
        byte[] body = message.getBody();
        if (body != null) {
            EmailTplMessage emailTplMessage = ObjectConvertUtil.handleBytesToObject(body, EmailTplMessage.class);
            if (emailTplMessage != null) {
                messageDispatcher.dispatch(emailTplMessage);
            }
        }
    }
}