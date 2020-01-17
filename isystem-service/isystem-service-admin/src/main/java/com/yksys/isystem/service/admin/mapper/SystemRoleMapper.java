package com.yksys.isystem.service.admin.mapper;

import com.yksys.isystem.common.pojo.AuthorityRole;
import com.yksys.isystem.common.pojo.SystemRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统角色表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface SystemRoleMapper {
    /**
     * 新增角色表
     * @param systemRole 角色表实体
     * @return
     */
    int addSystemRole(SystemRole systemRole);

    /**
     * 批量新增角色表
     * @param list 角色表集合
     */
    void addSystemRoles(@Param(value = "list") List<SystemRole> list);

    /**
     * 根据id查询指定角色表
     * @param id  主键
     * @return
     */
    Map<String, Object> getSystemRoleById(String id);

    /**
     * 根据id修改角色表
     * @param systemRole 角色表实体
     * @return
     */
    int editSystemRoleById(SystemRole systemRole);

    /**
     * 批量修改角色表
     *
     * @param systemRole 角色表实体
     * @param ids 主键集合
     */
    void editSystemRoleByIds(@Param("map") SystemRole systemRole, @Param("list") List<String> ids);

    /**
     * 根据id删除角色表
     * @param id
     * @return
     */
    int delSystemRoleById(String id);

    /**
     * 批量删除角色表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemRoleByIds(List<String> ids);

    /**
     * 真删除角色表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delSystemRoleRealById(String id);

    /**
     * 真批量删除角色表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemRoleRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllSystemRoleReal();

    /**
     * 获取所有角色表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getSystemRoles(Map<String, Object> map);

    /**
     * 分配角色
     * @param authorityRole
     */
    void assignRoleAuth(AuthorityRole authorityRole);

    /**
     * 删除权限
     * @param roleId
     */
    void delRoleAuth(String roleId);
}
