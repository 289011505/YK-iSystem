package com.yksys.isystem.common.core.constants;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-09 09:45
 **/
public class RabbitConstant {
    //http异步通知队列
    public static final String HTTP_NOTIFY_QUEUE = "isystem.notify.http.queue";

    //注册回调邮件异步通知队列
    public static final String REGISTER_CALLBACK_EMAIL_NOTIFY_QUEUE = "isystem.register.callback.email.notify.queue";

    //注册回调邮件异步通知队列路由key
    public static final String REGISTER_CALLBACK_EMAIL_NOTIFY_QUEUE_RK = "isystem.register.success.email.notify.queue.rk";

    //注册回调邮件异步通知交换器
    public static final String REGISTER_CALLBACK_EMAIL_NOTIFY_EXCHANGE = "isystem.register.success.email.notify.exchange";
}