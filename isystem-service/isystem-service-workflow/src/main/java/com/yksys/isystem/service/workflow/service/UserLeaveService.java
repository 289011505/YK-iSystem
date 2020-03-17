package com.yksys.isystem.service.workflow.service;
import com.yksys.isystem.common.pojo.UserLeave;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 用户请假申请表 service
 * @author: YuKai Fan
 * @create: 2020-03-17 16:08:06
 **/
public interface UserLeaveService {
    /**
     * 新增用户请假申请表
     * @param userLeave
     * @return
     */
    UserLeave addUserLeave(UserLeave userLeave);

    /**
     * 根据id查询用户请假申请表
     * @param id
     * @return
     */
    Map<String, Object> getUserLeaveById(String id);

    /**
     * 获取所有用户请假申请表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getUserLeaves(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有用户请假申请表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getUserLeaves(Map<String, Object> map);

    /**
     * 修改用户请假申请表
     * @param userLeave
     */
    void editUserLeave(UserLeave userLeave);

    /**
     * 根据id删除用户请假申请表(软删除)
     * @param id
     */
    void delUserLeaveById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delUserLeaveByIs(List<String> ids);

    /**
     * 根据id删除用户请假申请表(真删除)
     * @param id
     */
    void delUserLeaveRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delUserLeaveRealByIds(List<String> ids);
}
