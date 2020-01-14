package com.yksys.isystem.service.admin.service;

import com.yksys.isystem.common.pojo.SystemUser;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 系统用户service
 * @author: YuKai Fan
 * @create: 2019-12-03 20:04
 **/
public interface SystemUserService {
    /**
     * 新增用户
     * @param systemUser
     * @return
     */
    SystemUser addSystemUser(SystemUser systemUser);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Map<String, Object> getSystemUserById(String id);

    /**
     * 获取所有用户(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemUsers(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有用户
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemUsers(Map<String, Object> map);

    /**
     * 修改用户
     * @param systemUser
     */
    void editSystemUser(SystemUser systemUser);

    /**
     * 根据id删除用户(软删除)
     * @param id
     */
    void delSystemUserById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delSystemUserByIs(List<String> ids);

    /**
     * 根据id删除用户(真删除)
     * @param id
     */
    void delSystemUserRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delSystemUserRealByIds(List<String> ids);
}