package com.yksys.isystem.common.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yksys.isystem.common.core.utils.file.FileModel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description: rabbit处理消息
 * @author: YuKai Fan
 * @create: 2020-04-09 16:21
 **/
@Slf4j
public class ObjectConvertUtil {

    /**
     * 对象转为字符串
     * @param clazz
     * @return
     */
    public static String handleObjectToString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String str = objectMapper.writeValueAsString(object);

            return str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Rabbit对象转为字符串错误: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 对象转为byte数组
     * @param object
     * @return
     */
    public static byte[] handleObjectToBytes(Object object) {
        String str = handleObjectToString(object);
        return str.getBytes();
    }

    /**
     * byte数组转为对象
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T handleBytesToObject(byte[] bytes, Class clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            T t = (T) objectMapper.readValue(bytes, clazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Rabbit byte数组转为对象错误: {}", e.getMessage());
        }

        return null;
    }

    /**
     * 字符串转为对象
     * @param body
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T handleStringToObject(String body, Class clazz) {
        T t = (T)handleBytesToObject(body.getBytes(), clazz);
        return t;
    }
}