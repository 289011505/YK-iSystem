package com.yksys.isystem.service.system.task;

import org.springframework.stereotype.Component;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-24 10:35
 **/
@Component("taskTest")
public class TaskTest {
    public void taskMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println("执行多参方法");
    }

    public void taskParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void taskNoParams()
    {
        System.out.println("执行无参方法");
    }
}