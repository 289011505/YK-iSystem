package com.yksys.isystem.common.core.constants;

import lombok.Getter;

/**
 * @program: YK-iSystem
 * @description: 热点类型枚举
 * @author: YuKai Fan
 * @create: 2020-04-13 15:44
 **/
@Getter
public enum HotTypeEnum {
    HOT(1, "热"),
    RECOMMEND(3, "荐"),
    NEW(2, "新"),
    ;

    private Integer type;

    private String name;

    HotTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static Integer getType(String name) {
        HotTypeEnum[] hotTypeEnums = values();
        for (HotTypeEnum hotTypeEnum : hotTypeEnums) {
            if (hotTypeEnum.name().equals(name)) {
                return hotTypeEnum.type();
            }
        }

        return null;
    }

    private Integer type() {
        return this.type;
    }
}