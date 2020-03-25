package com.yksys.isystem.service.system.service.impl;

import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.pojo.ActionLog;
import com.yksys.isystem.service.system.mapper.ActionLogMapper;
import com.yksys.isystem.service.system.service.SystemTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;

/**
 * @program: yk-isystem
 * @description: 系统任务
 * @author: YuKai Fan
 * @create: 2020-03-25 21:10
 **/
@Service
public class SystemTaskServiceImpl implements SystemTaskService {
    @Autowired
    private ActionLogMapper actionLogMapper;
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
                actionLog.setId(AppUtil.randomId());
                actionLogMapper.addActionLog(actionLog);
            }
        });
    }
}