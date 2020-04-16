package com.yksys.isystem.service.base.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableBiMap;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.MapUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.pojo.UserIntroduce;
import com.yksys.isystem.service.base.mapper.UserIntroduceMapper;
import com.yksys.isystem.service.base.service.UserIntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class UserIntroduceServiceImpl implements UserIntroduceService {
    @Autowired
    private UserIntroduceMapper userIntroduceMapper;

    @Override
    public UserIntroduce addUserIntroduce(UserIntroduce userIntroduce) {
        String currentUserId = AppSession.getCurrentUserId();
        //判断当前用户是否已经创建简介
        List<Map<String, Object>> list = this.getUserIntroduces(ImmutableBiMap.of("userId", currentUserId));
        if (!CollectionUtils.isEmpty(userIntroduce.getSkillsList())) {
            userIntroduce.setSkills(JSONObject.toJSONString(userIntroduce.getSkillsList()));
        }
        if (CollectionUtils.isEmpty(list)) {
            userIntroduce.setId(AppUtil.randomId());
            userIntroduce.setStatus(1);
            userIntroduce.setUserId(currentUserId);
            userIntroduceMapper.addUserIntroduce(userIntroduce);
        } else {
            Map<String, Object> map = list.get(0);
            userIntroduce.setId(map.get("id").toString());
            userIntroduceMapper.editUserIntroduceById(userIntroduce);
        }
        return userIntroduce;
    }

    @Override
    public Map<String, Object> getUserIntroduceById(String id) {
        return userIntroduceMapper.getUserIntroduceById(id);
    }

    @Override
    public List<Map<String, Object>> getUserIntroduces(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getUserIntroduces(map);
    }

    @Override
    public List<Map<String, Object>> getUserIntroduces(Map<String, Object> map) {
        List<Map<String, Object>> userIntroduces = userIntroduceMapper.getUserIntroduces(map);
        if (!CollectionUtils.isEmpty(userIntroduces)) {
            userIntroduces.stream().filter(userIntroduce -> StringUtil.isNotBlank(userIntroduce.get("skills")))
                    .forEach(userIntroduce -> userIntroduce.put("skillsList", JSONObject.parseArray(userIntroduce.get("skills").toString())));
        }
        return userIntroduces;
    }

    @Override
    public void editUserIntroduce(UserIntroduce userIntroduce) {
        userIntroduceMapper.editUserIntroduceById(userIntroduce);
    }

    @Override
    public void delUserIntroduceById(String id) {
        userIntroduceMapper.delUserIntroduceById(id);
    }

    @Override
    public void delUserIntroduceByIs(List<String> ids) {
        userIntroduceMapper.delUserIntroduceByIds(ids);
    }

    @Override
    public void delUserIntroduceRealById(String id) {
        userIntroduceMapper.delUserIntroduceRealById(id);
    }

    @Override
    public void delUserIntroduceRealByIds(List<String> ids) {
        userIntroduceMapper.delUserIntroduceRealByIds(ids);
    }

}
