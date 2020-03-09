package com.yksys.isystem.service.admin.mapper;

import com.yksys.isystem.common.model.tree.SystemMenuTreeNode;
import com.yksys.isystem.common.pojo.SystemMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统系统菜单表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface SystemMenuMapper {
    /**
     * 新增系统菜单表
     * @param systemMenu 系统菜单表实体
     * @return
     */
    int addSystemMenu(SystemMenu systemMenu);

    /**
     * 批量新增系统菜单表
     * @param list 系统菜单表集合
     */
    void addSystemMenus(@Param(value = "list") List<SystemMenu> list);

    /**
     * 根据id查询指定系统菜单表
     * @param id  主键
     * @return
     */
    Map<String, Object> getSystemMenuById(String id);

    /**
     * 根据id修改系统菜单表
     * @param systemMenu 系统菜单表实体
     * @return
     */
    int editSystemMenuById(SystemMenu systemMenu);

    /**
     * 批量修改系统菜单表
     *
     * @param systemMenu 系统菜单表实体
     * @param ids 主键集合
     */
    void editSystemMenuByIds(@Param("map") SystemMenu systemMenu, @Param("list") List<String> ids);

    /**
     * 根据id删除系统菜单表
     * @param id
     * @return
     */
    int delSystemMenuById(String id);

    /**
     * 批量删除系统菜单表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemMenuByIds(List<String> ids);

    /**
     * 真删除系统菜单表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delSystemMenuRealById(String id);

    /**
     * 真批量删除系统菜单表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemMenuRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllSystemMenuReal();

    /**
     * 获取所有系统菜单表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<SystemMenuTreeNode> getSystemMenus(Map<String, Object> map);
}
