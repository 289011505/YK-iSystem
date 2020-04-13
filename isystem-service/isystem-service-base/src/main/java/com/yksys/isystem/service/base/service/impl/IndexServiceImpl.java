package com.yksys.isystem.service.base.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.service.base.mapper.IndexMapper;
import com.yksys.isystem.service.base.service.IndexService;
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
        return null;
    }
}