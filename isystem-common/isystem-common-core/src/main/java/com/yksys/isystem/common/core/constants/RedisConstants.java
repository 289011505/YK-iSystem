package com.yksys.isystem.common.core.constants;

/**
 * @program: YK-iSystem
 * @description: redis常量
 * @author: YuKai Fan
 * @create: 2020-03-23 09:15
 **/
public class RedisConstants {

    //验证码redis过期时间 5分钟
    public final static long CHECK_CODE_EXPIRE_TIME = 5 * 60;

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

    //热点新闻key前缀
    public final static String HOT_NEWS = "hot_news:";

    //用户通道关系key前缀
    public final static String USER_CHANNEL = "user_channel:";

    //账号通道关系key前缀
    public final static String ACCOUNT_CHANNEL = "account_channel:";

    //单聊记录(未签收)
    public final static String SINGLE_CHAT_RECORD = "single_chat_record:";

    //单聊记录(已签收)
    public final static String SINGLE_CHAT_RECORD_SIGNED = "single_chat_record_signed:";

    //群聊记录(未签收)
    public final static String GROUP_CHAT_RECORD = "group_chat_record:";

    //群聊记录(已签收)
    public final static String GROUP_CHAT_RECORD_SIGNED = "group_chat_record_signed:";

    //系统通知记录(未签收)
    public final static String SYSTEM_NOTICE_RECORD = "system_notice_recordL";

    //系统通知记录(已签收)
    public final static String SYSTEM_NOTICE_RECORD_SIGNED = "system_notice_record_signed:";

    //系统用户信息集合key前缀
    public final static String SYSTEM_USER_INFO_LIST = "system_user_info_list:";
}