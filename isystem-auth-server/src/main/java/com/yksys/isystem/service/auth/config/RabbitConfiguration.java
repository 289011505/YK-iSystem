package com.yksys.isystem.service.auth.config;

import com.yksys.isystem.common.core.constants.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: YK-iSystem
 * @description: rabbit配置
 * @author: YuKai Fan
 * @create: 2020-04-09 09:41
 **/
@Configuration
public class RabbitConfiguration {

    /**
     * 注册回调邮件异步通知队列
     * @return
     */
    @Bean
    public Queue registerCallbackEmailNotifyQueue() {
        return new Queue(RabbitConstant.REGISTER_CALLBACK_EMAIL_NOTIFY_QUEUE, true);
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange registerCallbackEmailNotifyExchange() {
        return new DirectExchange(RabbitConstant.REGISTER_CALLBACK_EMAIL_NOTIFY_EXCHANGE);
    }

    /**
     * 根据route_key绑定队列和交换器
     * @return
     */
    @Bean
    public Binding bindingRegisterFailEmail() {
        return BindingBuilder.bind(registerCallbackEmailNotifyQueue()).to(registerCallbackEmailNotifyExchange())
                .with(RabbitConstant.REGISTER_CALLBACK_EMAIL_NOTIFY_QUEUE_RK);
    }
}