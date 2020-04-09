package com.yksys.isystem.common.core.utils;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-04 09:04
 **/
public class AppUtil {

    /**
     * 获取随机全局唯一id
     * @return
     */
    public static String randomId() {
        IdWorker idWorker = new IdWorker(1, 1);
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 随机生成6位数字
     * @return
     */
    public static String getCheckCode() {
        return String.valueOf((int) ((Math.random() * 9.0D + 1.0D) * 100000.0D));
    }
}