package com.yksys.isystem.common.core.constants;

import lombok.Getter;

/**
 * @program: YK-iSystem
 * @description: 模板枚举
 * @author: YuKai Fan
 * @create: 2020-04-03 09:00
 **/
@Getter
public enum TemplateEnum {
    TXT_TPL(1 ,"文本模板"),
    VELOCITY_TPL(2 ,"velocity模板"),
    FREEMARKER_TPL(3 ,"freemarker模板"),
    ;
    private Integer type;

    private String content;

    TemplateEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }
}