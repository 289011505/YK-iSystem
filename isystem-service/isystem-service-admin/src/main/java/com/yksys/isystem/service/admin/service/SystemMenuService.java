package com.yksys.isystem.service.admin.service;
import com.yksys.isystem.common.pojo.SystemMenu;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 系统菜单表 service
 * @author: YuKai Fan
 * @create: 2020-03-08 13:52:22
 **/
public interface SystemMenuService {
    /**
     * 新增系统菜单表
     * @param systemMenu
     * @return
     */
    SystemMenu addSystemMenu(SystemMenu systemMenu);

    /**
     * 根据id查询系统菜单表
     * @param id
     * @return
     */
    Map<String, Object> getSystemMenuById(String id);

    /**
     * 获取所有系统菜单表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemMenus(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有系统菜单表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemMenus(Map<String, Object> map);

    /**
     * 修改系统菜单表
     * @param systemMenu
     */
    void editSystemMenu(SystemMenu systemMenu);

    /**
     * 根据id删除系统菜单表(软删除)
     * @param id
     */
    void delSystemMenuById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delSystemMenuByIs(List<String> ids);

    /**
     * 根据id删除系统菜单表(真删除)
     * @param id
     */
    void delSystemMenuRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delSystemMenuRealByIds(List<String> ids);
}
