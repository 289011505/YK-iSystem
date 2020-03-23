package com.yksys.isystem.common.core.annotation;

import com.yksys.isystem.common.core.constants.LogTypeEnum;

import java.lang.annotation.*;

/**
 * @program: yk-isystem
 * @description: 操作日志接口
 * @author: YuKai Fan
 * @create: 2020-03-21 17:16
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {
    /**
     * 日志类型
     * @return
     */
    LogTypeEnum logType();

}