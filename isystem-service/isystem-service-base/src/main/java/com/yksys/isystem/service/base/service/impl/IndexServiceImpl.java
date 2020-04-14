package com.yksys.isystem.service.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.model.server.CPUInfo;
import com.yksys.isystem.common.model.server.JVMInfo;
import com.yksys.isystem.common.model.server.MemoryInfo;
import com.yksys.isystem.service.base.mapper.IndexMapper;
import com.yksys.isystem.service.base.service.IndexService;
import com.yksys.isystem.service.base.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-13 11:13
 **/
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;
    @Autowired
    private ServerInfoService serverInfoService;

    @Override
    public Map<String, Object> getIndexCountData(Map<String, Object> map) {
        if (!map.containsKey("type")) {
            throw new ParameterException("请传入类型!");
        }
        String type = map.get("type").toString();
        Map<String, Object> result = Maps.newHashMap();
        List<Map<String, Object>> list = Lists.newArrayList();
        if ("actionCount".equals(type)) {
            list = indexMapper.getActionCountData(map);
        }

        if ("messageCount".equals(type)) {

        }

        if ("newsCount".equals(type)) {

        }

        List<Map<String, Object>> totalCountData = indexMapper.getTotalCountData();
        totalCountData.forEach(item -> {
            if ("actionCount".equals(item.get("type"))) {
                result.put("actionCount", item.get("allNum"));

            } else if ("messageCount".equals(item.get("type"))) {
                result.put("messageCount", item.get("allNum"));

            } else if ("newsCount".equals(item.get("type"))) {
                result.put("newsCount", item.get("allNum"));
            }
        });
        result.put("list", list);
        return result;
    }

    @Override
    public Map<String, Object> getIndexInfoData(Map<String, Object> map) {
        List<Map<String, Object>> hotNews = this.getHotNews(0, 7, map);
        Map<String, Object> result = Maps.newHashMap();
        result.put("hotNews", hotNews);
        return result;
    }

    @Override
    public Map<String, Object> getIndexChartData(Map<String, Object> map) {
        List<CPUInfo> cpuInfo = serverInfoService.getCpuInfo();
        JVMInfo jvmInfo = serverInfoService.getJVMInfo();
        MemoryInfo memoryInfo = serverInfoService.getMemoryInfo();

        Map<String, Object> result = Maps.newHashMap();
        result.put("cpuInfo", cpuInfo);
        result.put("jvmInfo", jvmInfo);
        result.put("memoryInfo", memoryInfo);
        return result;
    }

    @Override
    public List<Map<String, Object>> getHotNews(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return indexMapper.getHotNews(map);
    }
}