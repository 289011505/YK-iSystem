package com.yksys.isystem.service.message.netty.service.impl;

import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.model.message.ChatMessageContent;
import com.yksys.isystem.service.message.netty.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-15 16:33
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean saveNotSignedMessageRecord(String userId, Object o, String prefix) {
        List list = Lists.newArrayList();

        if (StringUtil.isBlank(prefix)) {
            return false;
        }

        if (redisUtil.get(prefix + userId) != null) {
            list = (List) redisUtil.get(prefix + userId);
        }

        list.add(o);
        redisUtil.set(prefix + userId, list);
        return true;
    }

    @Override
    public boolean saveSignedMessageRecord(String userId, Object o, String prefix) {
        List list = Lists.newArrayList();

        if (StringUtil.isBlank(prefix)) {
            return false;
        }

        List notSignedList = (List) o;
        if (redisUtil.get(prefix + userId) != null) {
            list = (List<ChatMessageContent>) redisUtil.get(prefix + userId);
        }

        list.addAll(notSignedList);
        redisUtil.set(prefix + userId, list);
        return true;
    }

    @Override
    public List getMessageRecord(String userId, String prefix) {
        List list = (List) redisUtil.get(prefix + userId);
        return list;
    }

    @Override
    public boolean delMessageRecord(String userId, String prefix) {
        redisUtil.del(prefix + userId);
        return true;
    }
}