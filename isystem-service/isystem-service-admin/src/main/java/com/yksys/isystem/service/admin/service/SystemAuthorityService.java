package com.yksys.isystem.service.admin.service;
import com.yksys.isystem.common.pojo.SystemAuthority;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 系统权限表 service
 * @author: YuKai Fan
 * @create: 2020-01-16 14:11:47
 **/
public interface SystemAuthorityService {
    /**
     * 新增系统权限表
     * @param systemAuthority
     * @return
     */
    SystemAuthority addSystemAuthority(SystemAuthority systemAuthority);

    /**
     * 根据id查询系统权限表
     * @param id
     * @return
     */
    Map<String, Object> getSystemAuthorityById(String id);

    /**
     * 获取所有系统权限表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemAuthorities(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有系统权限表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemAuthorities(Map<String, Object> map);

    /**
     * 修改系统权限表
     * @param systemAuthority
     */
    void editSystemAuthority(SystemAuthority systemAuthority);

    /**
     * 根据id删除系统权限表(软删除)
     * @param id
     */
    void delSystemAuthorityById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delSystemAuthorityByIs(List<String> ids);

    /**
     * 根据id删除系统权限表(真删除)
     * @param id
     */
    void delSystemAuthorityRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delSystemAuthorityRealByIds(List<String> ids);
}
