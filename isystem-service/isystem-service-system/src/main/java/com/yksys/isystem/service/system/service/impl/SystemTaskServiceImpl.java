package com.yksys.isystem.service.system.service.impl;

import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.constants.ComConstants;
import com.yksys.isystem.common.core.constants.HotNewsTypeEnum;
import com.yksys.isystem.common.core.constants.HotTypeEnum;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.pojo.ActionLog;
import com.yksys.isystem.common.pojo.HotNews;
import com.yksys.isystem.service.system.mapper.ActionLogMapper;
import com.yksys.isystem.service.system.service.ActionLogService;
import com.yksys.isystem.service.system.service.SystemTaskService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: yk-isystem
 * @description: 系统任务
 * @author: YuKai Fan
 * @create: 2020-03-25 21:10
 **/
@Service
@Slf4j
public class SystemTaskServiceImpl implements SystemTaskService {
    @Autowired
    private ActionLogService actionLogService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addActionLogByRedis() {
        Set<String> keys = redisUtil.getByPrefix(RedisConstants.ACTION_LOG);
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        keys.forEach(key -> {
            Map<String, Object> map = (Map<String, Object>) redisUtil.get(key);
            if (!CollectionUtils.isEmpty(map)) {
                ActionLog actionLog = MapUtil.mapToObject(ActionLog.class, map, false);
                actionLogService.addActionLog(actionLog);

                redisUtil.del(key);
            }
        });
    }

    @Override
    public void updateHotNewsToRedis() {
        //通过微博爬取热点关键词新闻
        // 通过jsoup将对应url转为document
        try {
            Document doc = Jsoup.parse(new URL(ComConstants.REPTILE_HOT_NEWS_KEYWORD_FROM_WEIBO), 10000);
            //获取script标签对应的Element list
            Elements elements = doc.select("div[class=data] tbody tr");
            if (CollectionUtils.isEmpty(elements)) {
                return;
            }
            List<HotNews> list = Lists.newArrayList();
            for (Element element : elements) {
                HotNews hotNews = new HotNews();
                String keyword = element.select("td[class=td-02] a").text();
                String searchNum = element.select("td[class=td-02] span").text();
                String href = element.select("td[class=td-02] a").attr("href");
                String hotTypeStr = element.select("td[class=td-03] i").text();

                hotNews.setTitle(keyword);
                hotNews.setUrl(ComConstants.WEIBO_NEWS_URL + href);
                hotNews.setHotType(HotTypeEnum.getType(hotTypeStr));
                hotNews.setLikeNum(StringUtil.isBlank(searchNum)?0:Integer.valueOf(searchNum));
                hotNews.setType(HotNewsTypeEnum.WEIBO.getType());

                list.add(hotNews);
            }
            //存入redis
            redisUtil.set(RedisConstants.HOT_NEWS + AppUtil.randomId(), list);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("爬取热点新闻错误: {}", e.getMessage());
        }
    }
}