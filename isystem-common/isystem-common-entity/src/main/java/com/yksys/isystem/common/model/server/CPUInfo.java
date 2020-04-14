package com.yksys.isystem.common.model.server;

import lombok.Data;

/**
 * @program: YK-iSystem
 * @description: cpu信息
 * @author: YuKai Fan
 * @create: 2020-04-14 10:59
 **/
@Data
public class CPUInfo {
    //cpu的总量MHZ
    private Integer mhz;
    //cpu的厂商
    private String vendor;
    //cpu的型号类别
    private String model;
    //缓冲缓存数量
    private Long cacheSize;

    //用户使用率
    private String userRate;
    //系统使用率
    private String sysRate;
    //当前等待率
    private String waitRate;
    //当前空闲率
    private String idleRate;
    //当前错误率
    private String niceRate;
    //总使用率
    private String combinedRate;
}