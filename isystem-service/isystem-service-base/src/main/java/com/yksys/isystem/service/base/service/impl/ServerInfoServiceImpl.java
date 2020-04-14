package com.yksys.isystem.service.base.service.impl;

import com.google.common.collect.Lists;
import com.yksys.isystem.common.model.server.CPUInfo;
import com.yksys.isystem.common.model.server.JVMInfo;
import com.yksys.isystem.common.model.server.MemoryInfo;
import com.yksys.isystem.service.base.service.ServerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.hyperic.sigar.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-14 11:06
 **/
@Service
@Slf4j
public class ServerInfoServiceImpl implements ServerInfoService {
    @Override
    public List<CPUInfo> getCpuInfo() {
        Sigar sigar = new Sigar();
        List<CPUInfo> list = Lists.newArrayList();
        try {
            CpuInfo[] infos = sigar.getCpuInfoList();
            CpuPerc[] cpuList = sigar.getCpuPercList();

            // 适用单块cpu和多cpu
            for (int i = 0; i < infos.length; i++) {
                CpuInfo cpuInfos = infos[i];
                CPUInfo cpuInfo = new CPUInfo();
                cpuInfo.setMhz(cpuInfos.getMhz());
                cpuInfo.setVendor(cpuInfos.getVendor());
                cpuInfo.setModel(cpuInfos.getModel());
                cpuInfo.setCacheSize(cpuInfos.getCacheSize());

                CpuPerc cpuPerc = cpuList[i];
                cpuInfo.setUserRate(cpuPerc.format(cpuPerc.getUser()).replace("%", ""));
                cpuInfo.setSysRate(cpuPerc.format(cpuPerc.getSys()).replace("%", ""));
                cpuInfo.setWaitRate(cpuPerc.format(cpuPerc.getWait()).replace("%", ""));
                cpuInfo.setNiceRate(cpuPerc.format(cpuPerc.getNice()).replace("%", ""));
                cpuInfo.setIdleRate(cpuPerc.format(cpuPerc.getIdle()).replace("%", ""));
                cpuInfo.setCombinedRate(cpuPerc.format(cpuPerc.getCombined()).replace("%", ""));
                list.add(cpuInfo);
            }

            log.info("获取cpu信息: {}", list);
        } catch (SigarException e) {
            e.printStackTrace();
            log.error("获取cpu信息失败: {}", e.getMessage());
        }

        return list;
    }

    @Override
    public JVMInfo getJVMInfo() {
        Runtime runtime = Runtime.getRuntime();
        Properties props = System.getProperties();
        JVMInfo jvmInfo = new JVMInfo();

        jvmInfo.setTotalMemory(runtime.totalMemory());
        jvmInfo.setFreeMemory(runtime.freeMemory());
        jvmInfo.setAvailable(runtime.availableProcessors());
        jvmInfo.setJavaVersion(props.getProperty("java.version"));
        jvmInfo.setVendor(props.getProperty("java.vendor"));
        jvmInfo.setJavaHome(props.getProperty("java.home"));
        jvmInfo.setSpecificationVersion(props.getProperty("java.specification.version"));
        jvmInfo.setClassPath(props.getProperty("java.class.path"));
        jvmInfo.setLibraryPath(props.getProperty("java.library.path"));
        jvmInfo.setIoTmpdir(props.getProperty("java.io.tmpdir"));
        jvmInfo.setExtDirs(props.getProperty("java.ext.dirs"));
        return jvmInfo;
    }

    @Override
    public MemoryInfo getMemoryInfo() {
        Sigar sigar = new Sigar();
        MemoryInfo memoryInfo = new MemoryInfo();
        try {
            Mem mem = sigar.getMem();
            memoryInfo.setTotal(mem.getTotal() / (1024 * 1024L));
            memoryInfo.setUsed(mem.getUsed() / (1024 * 1024L));
            memoryInfo.setFree(mem.getFree() / (1024 * 1024L));
            memoryInfo.setUsedRate(String.valueOf(mem.getUsedPercent()));
            memoryInfo.setFreeRate(String.valueOf(mem.getFreePercent()));

            Swap swap = sigar.getSwap();
            memoryInfo.setSwapTotal(swap.getTotal() / (1024 * 1024L));
            memoryInfo.setSwapUsed(swap.getUsed() / (1024 * 1024L));
            memoryInfo.setSwapFree(swap.getFree() / (1024 * 1024L));

        } catch (SigarException e) {
            e.printStackTrace();
        }
        return memoryInfo;
    }
}