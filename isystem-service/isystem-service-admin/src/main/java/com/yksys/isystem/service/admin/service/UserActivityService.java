package com.yksys.isystem.service.admin.service;
import com.yksys.isystem.common.pojo.UserActivity;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 用户动态表 service
 * @author: YuKai Fan
 * @create: 2020-04-17 09:45:03
 **/
public interface UserActivityService {
    /**
     * 新增用户动态表
     * @param userActivity
     * @return
     */
    UserActivity addUserActivity(UserActivity userActivity);

    /**
     * 根据id查询用户动态表
     * @param id
     * @return
     */
    Map<String, Object> getUserActivityById(String id);

    /**
     * 获取所有用户动态表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getUserActivitys(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有用户动态表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getUserActivitys(Map<String, Object> map);

    /**
     * 修改用户动态表
     * @param userActivity
     */
    void editUserActivity(UserActivity userActivity);

    /**
     * 根据id删除用户动态表(软删除)
     * @param id
     */
    void delUserActivityById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delUserActivityByIs(List<String> ids);

    /**
     * 根据id删除用户动态表(真删除)
     * @param id
     */
    void delUserActivityRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delUserActivityRealByIds(List<String> ids);
}
