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
    ;

    private String type;

    private String name;

    LogTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
}