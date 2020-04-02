package com.yksys.isystem.common.core.constants;

/**
 * @program: YK-iSystem
 * @description: redis常量
 * @author: YuKai Fan
 * @create: 2020-03-23 09:15
 **/
public class RedisConstants {
    // 操作日志key前缀
    public final static String ACTION_LOG = "action_log:";

    //邮件配置集合key前缀
    public final static String EMAIL_CONFIG = "email_config:";

    //邮件模板集合key前缀
    public final static String EMAIL_TEMPLATE = "email_template:";

    //redis 保存操作
    public final static String REDIS_OPERATE_TYPE_SAVE = "redis_save";

    //redis 删除操作
    public final static String REDIS_OPERATE_TYPE_DEL = "redis_del";
}