package com.yksys.isystem.service.admin.mapper;

import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.common.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统系统用户表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface SystemUserMapper {
    /**
     * 新增系统用户表
     * @param systemUser 系统用户表实体
     * @return
     */
    int addSystemUser(SystemUser systemUser);

    /**
     * 批量新增系统用户表
     * @param list 系统用户表集合
     */
    void addSystemUsers(@Param(value = "list") List<SystemUser> list);

    /**
     * 根据id查询指定系统用户表
     * @param id  主键
     * @return
     */
    Map<String, Object> getSystemUserById(String id);

    /**
     * 根据id修改系统用户表
     * @param user 系统用户表实体
     * @return
     */
    int editSystemUserById(SystemUser systemUser);

    /**
     * 批量修改系统用户表
     *
     * @param systemUser 系统用户表实体
     * @param ids 主键集合
     */
    void editSystemUserByIds(@Param("map") SystemUser systemUser, @Param("list") List<String> ids);

    /**
     * 根据id删除系统用户表
     * @param id
     * @return
     */
    int delSystemUserById(String id);

    /**
     * 批量删除系统用户表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemUserByIds(List<String> ids);

    /**
     * 真删除系统用户表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delSystemUserRealById(String id);

    /**
     * 真批量删除系统用户表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemUserRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllSystemUserReal();

    /**
     * 获取所有系统用户表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getSystemUsers(Map<String, Object> map);

    /**
     * 新增用户角色关系
     * @param list
     */
    void addUserRoles(@Param(value = "list") List<UserRole> list);

    /**
     * 根据userId删除用户角色关系
     * @param userId
     */
    void delUserRolesByUserId(String userId);

    /**
     * 根据userId获取角色列表
     * @param userId
     * @return
     */
    List<String> getUserRolesByUserId(String userId);
}
