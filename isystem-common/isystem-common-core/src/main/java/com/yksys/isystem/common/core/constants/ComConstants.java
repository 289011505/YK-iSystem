package com.yksys.isystem.common.core.constants;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-24 15:25
 **/
public class ComConstants {
    // 默认超级管理员账号
    public final static String ROOT_ADMIN = "admin";

    //超级管理员邮箱
    public final static String ADMIN_EMAIL = "michaelkai@aliyun.com";

    /**
     * 账号状态
     * 0: 禁用, 1: 正常, 2: 锁定
     */
    public final static Integer ACCOUNT_STATUS_DISABLE = 0;
    public final static Integer ACCOUNT_STATUS_NORMAL = 1;
    public final static Integer ACCOUNT_STATUS_LOCKED = 2;

    /**
     * 微服务之间传递的唯一标识
     */
    public final static String REQUEST_HEADER_ID = "Request-Header-Id";

    /**
     * http前缀
     */
    public final static String HTTP = "http://";

    //默认角色id 超级管理员
    public final static String ADMIN_ID = "1";
    //默认角色id 客户
    public final static String CUSTOMER_ID = "2";
    //默认角色id 注册用户
    public final static String REGISTER_ID = "3";

    //爬取数据链接 微博链接
    public final static String REPTILE_HOT_NEWS_KEYWORD_FROM_WEIBO = "https://s.weibo.com/top/summary?cate=realtimehot";
    public final static String WEIBO_NEWS_URL = "https://s.weibo.com";
}