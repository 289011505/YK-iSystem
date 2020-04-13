package com.yksys.isystem.common.core.constants;

import lombok.Getter;

/**
 * @program: yk-isystem
 * @description: 日志类型
 * @author: YuKai Fan
 * @create: 2020-03-21 17:29
 **/
@Getter
public enum LogTypeEnum {

    USER_LOGIN("user_login", "用户登录"),
    USER_PROFILE("user_profile", "获取当前登录用户信息"),
    USER_LOGOUT("user_logout", "用户退出登录"),
    USER_EMAIL_REGISTERED("user_email_registered", "用户邮箱注册"),
    USER_SEND_REGISTER_EMAIL("user_send_register_email", "用户发送邮箱注册验证码"),
    ;

    private String type;

    private String name;

    LogTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
}