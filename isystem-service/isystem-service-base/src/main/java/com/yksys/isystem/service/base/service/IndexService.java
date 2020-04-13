package com.yksys.isystem.service.base.service;

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
}