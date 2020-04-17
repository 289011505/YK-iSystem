package com.yksys.isystem.service.business.controller.task;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Sets;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.pojo.HotNews;
import com.yksys.isystem.service.business.service.HotNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: YK-iSystem
 * @description: 定时任务controller
 * @author: YuKai Fan
 * @create: 2020-04-13 16:08
 **/
@RestController
@RequestMapping("/api/systemBusinessTask")
public class SystemBusinessTaskController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HotNewsService hotNewsService;

    /**
     * 每天凌晨 01：00 定时存入DB
     */
    @PostMapping("/updateHotNewsFromRedisToDb")
    public void updateHotNewsFromRedisToDb() {
        Set<String> keys = redisUtil.getByPrefix(RedisConstants.HOT_NEWS);
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }

        Set<HotNews> set = Sets.newHashSet();
        keys.forEach(key -> {
            List<HotNews> list = (List<HotNews>) redisUtil.get(key);
            set.addAll(list);

            redisUtil.del(key);
        });

        //存入数据库
        for (HotNews hotNews : set) {
            List<Map<String, Object>> list = hotNewsService.getHotNews(ImmutableBiMap.of("title", hotNews.getTitle()));
            if (CollectionUtils.isEmpty(list)) {
                hotNewsService.addHotNews(hotNews);
            } else {
                hotNews.setId(list.get(0).get("id").toString());
                hotNewsService.editHotNews(hotNews);
            }
        }
    }
}