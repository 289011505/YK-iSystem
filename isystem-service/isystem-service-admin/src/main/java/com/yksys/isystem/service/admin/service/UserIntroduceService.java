package com.yksys.isystem.service.admin.service;
import com.yksys.isystem.common.pojo.UserIntroduce;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 用户简介表 service
 * @author: YuKai Fan
 * @create: 2020-04-16 21:06:52
 **/
public interface UserIntroduceService {
    /**
     * 新增用户简介表
     * @param userIntroduce
     * @return
     */
    UserIntroduce addUserIntroduce(UserIntroduce userIntroduce);

    /**
     * 根据id查询用户简介表
     * @param id
     * @return
     */
    Map<String, Object> getUserIntroduceById(String id);

    /**
     * 获取所有用户简介表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getUserIntroduces(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有用户简介表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getUserIntroduces(Map<String, Object> map);

    /**
     * 修改用户简介表
     * @param userIntroduce
     */
    void editUserIntroduce(UserIntroduce userIntroduce);

    /**
     * 根据id删除用户简介表(软删除)
     * @param id
     */
    void delUserIntroduceById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delUserIntroduceByIs(List<String> ids);

    /**
     * 根据id删除用户简介表(真删除)
     * @param id
     */
    void delUserIntroduceRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delUserIntroduceRealByIds(List<String> ids);
}
