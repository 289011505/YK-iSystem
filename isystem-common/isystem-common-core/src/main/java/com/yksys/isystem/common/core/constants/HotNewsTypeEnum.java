package com.yksys.isystem.common.core.constants;

import lombok.Getter;

/**
 * @program: YK-iSystem
 * @description: 热点新闻类型
 * @author: YuKai Fan
 * @create: 2020-04-13 15:52
 **/
@Getter
public enum HotNewsTypeEnum {
    WEIBO(1, "微博热搜"),
    TOUTIAO(2, "今日头条"),
    BAIDU(3, "百度热搜"),
    ;

    private Integer type;

    private String name;

    HotNewsTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}