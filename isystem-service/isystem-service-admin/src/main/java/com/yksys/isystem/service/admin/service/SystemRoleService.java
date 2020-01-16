package com.yksys.isystem.service.admin.service;
import com.yksys.isystem.common.pojo.SystemRole;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 角色表 service
 * @author: YuKai Fan
 * @create: 2020-01-16 14:11:47
 **/
public interface SystemRoleService {
    /**
     * 新增角色表
     * @param systemRole
     * @return
     */
    SystemRole addSystemRole(SystemRole systemRole);

    /**
     * 根据id查询角色表
     * @param id
     * @return
     */
    Map<String, Object> getSystemRoleById(String id);

    /**
     * 获取所有角色表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemRoles(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有角色表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemRoles(Map<String, Object> map);

    /**
     * 修改角色表
     * @param systemRole
     */
    void editSystemRole(SystemRole systemRole);

    /**
     * 根据id删除角色表(软删除)
     * @param id
     */
    void delSystemRoleById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delSystemRoleByIs(List<String> ids);

    /**
     * 根据id删除角色表(真删除)
     * @param id
     */
    void delSystemRoleRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delSystemRoleRealByIds(List<String> ids);
}
