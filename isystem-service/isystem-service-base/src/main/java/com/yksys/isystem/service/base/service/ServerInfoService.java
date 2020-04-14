package com.yksys.isystem.service.base.service;

import com.yksys.isystem.common.model.server.CPUInfo;
import com.yksys.isystem.common.model.server.JVMInfo;
import com.yksys.isystem.common.model.server.MemoryInfo;

import java.util.List;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-14 10:57
 **/
public interface ServerInfoService {

    /**
     * 获取cpu信息
     * @return
     */
    List<CPUInfo> getCpuInfo();

    /**
     * 获取jvm信息
     * @return
     */
    JVMInfo getJVMInfo();

    /**
     * 获取内存信息
     * @return
     */
    MemoryInfo getMemoryInfo();
}