package com.yksys.isystem.common.model.server;

import lombok.Data;

/**
 * @program: YK-iSystem
 * @description: 内存信息
 * @author: YuKai Fan
 * @create: 2020-04-14 11:00
 **/
@Data
public class MemoryInfo {
    //内存总量
    private Long total;
    //内存使用量
    private Long used;
    //内存剩余量
    private Long free;
    //内存利用率
    private String usedRate;
    //内存剩余率
    private String freeRate;

    //交换区总量
    private Long swapTotal;
    //交换区使用量
    private Long swapUsed;
    //交换区剩余量
    private Long swapFree;

}