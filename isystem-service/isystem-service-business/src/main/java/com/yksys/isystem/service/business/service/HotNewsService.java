package com.yksys.isystem.service.business.service;
import com.yksys.isystem.common.pojo.HotNews;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: YK-iSystem
 * @description: 热点新闻表 service
 * @author: YuKai Fan
 * @create: 2020-04-13 15:32:29
 **/
public interface HotNewsService {
    /**
     * 新增热点新闻表
     * @param hotNews
     * @return
     */
    HotNews addHotNews(HotNews hotNews);

    /**
     * 新增热点新闻表
     * @param list
     * @return
     */
    void addHotNewsBatch(Set<HotNews> list);

    /**
     * 根据id查询热点新闻表
     * @param id
     * @return
     */
    Map<String, Object> getHotNewsById(String id);

    /**
     * 获取所有热点新闻表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getHotNews(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有热点新闻表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getHotNews(Map<String, Object> map);

    /**
     * 修改热点新闻表
     * @param hotNews
     */
    void editHotNews(HotNews hotNews);

    /**
     * 根据id删除热点新闻表(软删除)
     * @param id
     */
    void delHotNewsById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delHotNewsByIs(List<String> ids);

    /**
     * 根据id删除热点新闻表(真删除)
     * @param id
     */
    void delHotNewsRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delHotNewsRealByIds(List<String> ids);
}
