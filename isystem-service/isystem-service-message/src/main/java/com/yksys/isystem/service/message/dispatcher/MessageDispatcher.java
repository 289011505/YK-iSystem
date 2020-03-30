package com.yksys.isystem.service.message.dispatcher;

import com.yksys.isystem.common.model.BaseMessage;
import com.yksys.isystem.service.message.exchanger.MessageExchanger;
import com.yksys.isystem.service.message.task.MessageTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: YK-iSystem
 * @description: 消息转发器
 * @author: YuKai Fan
 * @create: 2020-03-30 11:36
 **/
@Component
@Slf4j
public class MessageDispatcher implements ApplicationContextAware {
    private Collection<MessageExchanger> exchangers;

    private ExecutorService executorService;

    public MessageDispatcher() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int threadsNum = availableProcessors * 2;
        executorService = new ThreadPoolExecutor(threadsNum, threadsNum, 0 , TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        log.info("Init Notify ExecutorService , numOfThread : " + threadsNum);
    }

    public void dispatch(BaseMessage message) {
        if (message != null && exchangers != null) {
            exchangers.stream()
                    .filter(exchanger -> exchanger.support(message))
            .forEach(exchanger -> executorService.submit(new MessageTask(exchanger, message)));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, MessageExchanger> beansOfType = applicationContext.getBeansOfType(MessageExchanger.class);
        this.exchangers = beansOfType.values();
    }
}