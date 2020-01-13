package com.yksys.isystem.service.generate.service;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-01-13 11:44
 **/
public interface SysGeneratorService {

    /**
     * 获取所有数据库表(分页)
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    List<Map<String, String>> getDBTables(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有数据库表
     * @param map
     * @return
     */
    List<Map<String, String>> getDBTables(Map<String, Object> map);

    /**
     * 代码生成并下载
     * @param tables
     * @return
     */
    byte[] generateCode(String[] tables);
}