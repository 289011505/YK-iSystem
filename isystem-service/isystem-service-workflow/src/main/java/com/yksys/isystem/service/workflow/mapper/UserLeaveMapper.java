package com.yksys.isystem.service.workflow.mapper;

import com.yksys.isystem.common.pojo.UserLeave;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统用户请假申请表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface UserLeaveMapper {
    /**
     * 新增用户请假申请表
     * @param userLeave 用户请假申请表实体
     * @return
     */
    int addUserLeave(UserLeave userLeave);

    /**
     * 批量新增用户请假申请表
     * @param list 用户请假申请表集合
     */
    void addUserLeaves(@Param(value = "list") List<UserLeave> list);

    /**
     * 根据id查询指定用户请假申请表
     * @param id  主键
     * @return
     */
    Map<String, Object> getUserLeaveById(String id);

    /**
     * 根据id修改用户请假申请表
     * @param userLeave 用户请假申请表实体
     * @return
     */
    int editUserLeaveById(UserLeave userLeave);

    /**
     * 批量修改用户请假申请表
     *
     * @param userLeave 用户请假申请表实体
     * @param ids 主键集合
     */
    void editUserLeaveByIds(@Param("map") UserLeave userLeave, @Param("list") List<String> ids);

    /**
     * 根据id删除用户请假申请表
     * @param id
     * @return
     */
    int delUserLeaveById(String id);

    /**
     * 批量删除用户请假申请表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delUserLeaveByIds(List<String> ids);

    /**
     * 真删除用户请假申请表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delUserLeaveRealById(String id);

    /**
     * 真批量删除用户请假申请表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delUserLeaveRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllUserLeaveReal();

    /**
     * 获取所有用户请假申请表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getUserLeaves(Map<String, Object> map);
}
