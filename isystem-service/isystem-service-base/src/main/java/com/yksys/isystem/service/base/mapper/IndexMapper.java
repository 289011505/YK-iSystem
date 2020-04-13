package com.yksys.isystem.service.base.mapper;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-13 11:13
 **/
public interface IndexMapper {
    /**
     * 获取操作访问量统计数据
     * @param map
     * @return
     */
    List<Map<String, Object>> getActionCountData(Map<String, Object> map);

    /**
     * 获取统计总数
     * @return
     */
    List<Map<String, Object>> getTotalCountData();
}