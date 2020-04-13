package com.yksys.isystem.service.business.mapper;

import com.yksys.isystem.common.pojo.HotNews;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @program: YK-iSystem
 * @description: 系统热点新闻表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface HotNewsMapper {
    /**
     * 新增热点新闻表
     * @param hotNews 热点新闻表实体
     * @return
     */
    int addHotNews(HotNews hotNews);

    /**
     * 批量新增热点新闻表
     * @param list 热点新闻表集合
     */
    void addHotNewsBatch(@Param(value = "set") Set<HotNews> list);

    /**
     * 根据id查询指定热点新闻表
     * @param id  主键
     * @return
     */
    Map<String, Object> getHotNewsById(String id);

    /**
     * 根据id修改热点新闻表
     * @param hotNews 热点新闻表实体
     * @return
     */
    int editHotNewsById(HotNews hotNews);

    /**
     * 批量修改热点新闻表
     *
     * @param hotNews 热点新闻表实体
     * @param ids 主键集合
     */
    void editHotNewsByIds(@Param("map") HotNews hotNews, @Param("list") List<String> ids);

    /**
     * 根据id删除热点新闻表
     * @param id
     * @return
     */
    int delHotNewsById(String id);

    /**
     * 批量删除热点新闻表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delHotNewsByIds(List<String> ids);

    /**
     * 真删除热点新闻表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delHotNewsRealById(String id);

    /**
     * 真批量删除热点新闻表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delHotNewsRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllHotNewsReal();

    /**
     * 获取所有热点新闻表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getHotNews(Map<String, Object> map);
}
