package com.yksys.isystem.service.admin.mapper;

import com.yksys.isystem.common.pojo.UserActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统用户动态表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface UserActivityMapper {
    /**
     * 新增用户动态表
     * @param userActivity 用户动态表实体
     * @return
     */
    int addUserActivity(UserActivity userActivity);

    /**
     * 批量新增用户动态表
     * @param list 用户动态表集合
     */
    void addUserActivitys(@Param(value = "list") List<UserActivity> list);

    /**
     * 根据id查询指定用户动态表
     * @param id  主键
     * @return
     */
    Map<String, Object> getUserActivityById(String id);

    /**
     * 根据id修改用户动态表
     * @param userActivity 用户动态表实体
     * @return
     */
    int editUserActivityById(UserActivity userActivity);

    /**
     * 批量修改用户动态表
     *
     * @param userActivity 用户动态表实体
     * @param ids 主键集合
     */
    void editUserActivityByIds(@Param("map") UserActivity userActivity, @Param("list") List<String> ids);

    /**
     * 根据id删除用户动态表
     * @param id
     * @return
     */
    int delUserActivityById(String id);

    /**
     * 批量删除用户动态表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delUserActivityByIds(List<String> ids);

    /**
     * 真删除用户动态表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delUserActivityRealById(String id);

    /**
     * 真批量删除用户动态表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delUserActivityRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllUserActivityReal();

    /**
     * 获取所有用户动态表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getUserActivitys(Map<String, Object> map);
}
