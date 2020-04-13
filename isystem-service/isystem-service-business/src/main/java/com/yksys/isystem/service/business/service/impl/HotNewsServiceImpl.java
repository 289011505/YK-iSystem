package com.yksys.isystem.service.business.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.HotNews;
import com.yksys.isystem.service.business.mapper.HotNewsMapper;
import com.yksys.isystem.service.business.service.HotNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class HotNewsServiceImpl implements HotNewsService {
    @Autowired
    private HotNewsMapper hotNewsMapper;

    @Override
    public HotNews addHotNews(HotNews hotNews) {
        hotNews.setId(AppUtil.randomId());
        hotNews.setStatus(1);
        hotNewsMapper.addHotNews(hotNews);
        return hotNews;
    }

    @Override
    public void addHotNewsBatch(Set<HotNews> list) {
        hotNewsMapper.addHotNewsBatch(list);
    }


    @Override
    public Map<String, Object> getHotNewsById(String id) {
        return hotNewsMapper.getHotNewsById(id);
    }

    @Override
    public List<Map<String, Object>> getHotNews(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getHotNews(map);
    }

    @Override
    public List<Map<String, Object>> getHotNews(Map<String, Object> map) {
        return hotNewsMapper.getHotNews(map);
    }

    @Override
    public void editHotNews(HotNews hotNews) {
        hotNewsMapper.editHotNewsById(hotNews);
    }

    @Override
    public void delHotNewsById(String id) {
        hotNewsMapper.delHotNewsById(id);
    }

    @Override
    public void delHotNewsByIs(List<String> ids) {
        hotNewsMapper.delHotNewsByIds(ids);
    }

    @Override
    public void delHotNewsRealById(String id) {
        hotNewsMapper.delHotNewsRealById(id);
    }

    @Override
    public void delHotNewsRealByIds(List<String> ids) {
        hotNewsMapper.delHotNewsRealByIds(ids);
    }

}
