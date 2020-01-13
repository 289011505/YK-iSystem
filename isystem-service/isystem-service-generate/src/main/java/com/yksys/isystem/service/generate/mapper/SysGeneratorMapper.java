package com.yksys.isystem.service.generate.mapper;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-01-13 11:50
 **/
public interface SysGeneratorMapper {
    /**
     * 获取所有数据库表
     * @param map
     * @return
     */
    List<Map<String, String>> getDBTables(Map<String, Object> map);

    /**
     * 根据表名查询列
     * @param tableName
     * @return
     */
    List<Map<String, String>> getColumns(String tableName);
}