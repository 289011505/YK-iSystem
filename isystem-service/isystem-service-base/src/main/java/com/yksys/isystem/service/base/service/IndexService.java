package com.yksys.isystem.service.base.service;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-13 11:13
 **/
public interface IndexService {

    /**
     * 获取首页数据(操作访问量, 消息, 新闻统计)
     * @param map
     * @return
     */
    Map<String, Object> getIndexCountData(Map<String, Object> map);

    /**
     * 获取首页数据(新闻热搜, todo列表, 个人信息数据)
     * @param map
     * @return
     */
    Map<String, Object> getIndexInfoData(Map<String, Object> map);

    /**
     * 获取首页数据(疫情情况图, 编程语言图, 服务性能图)
     * @param map
     * @return
     */
    Map<String, Object> getIndexChartData(Map<String, Object> map);

    /**
     * 获取所有热点新闻表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getHotNews(int start, int pageSize, Map<String, Object> map);
}