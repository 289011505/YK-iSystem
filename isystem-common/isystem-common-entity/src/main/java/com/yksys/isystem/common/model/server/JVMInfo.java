package com.yksys.isystem.common.model.server;

import lombok.Data;

/**
 * @program: YK-iSystem
 * @description: JVM信息
 * @author: YuKai Fan
 * @create: 2020-04-14 10:59
 **/
@Data
public class JVMInfo {
    //JVM可以使用的总内存
    private Long totalMemory;
    //JVM可以使用的剩余内存
    private Long freeMemory;
    //JVM可以使用的处理器个数
    private Integer available;
    //Java运行的环境版本
    private String javaVersion;
    //Java的运行环境供应商
    private String vendor;
    //Java的安装路径
    private String javaHome;
    //Java运行时环境规范版本
    private String specificationVersion;
    //Java的类路径
    private String classPath;
    //Java加载库时搜索的路径列表
    private String libraryPath;
    //默认的临时文件路径
    private String ioTmpdir;
    //扩展目录的路径
    private String extDirs;
}