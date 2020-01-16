package com.yksys.isystem.service.admin.mapper;

import com.yksys.isystem.common.pojo.SystemAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统系统权限表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface SystemAuthorityMapper {
    /**
     * 新增系统权限表
     * @param systemAuthority 系统权限表实体
     * @return
     */
    int addSystemAuthority(SystemAuthority systemAuthority);

    /**
     * 批量新增系统权限表
     * @param list 系统权限表集合
     */
    void addSystemAuthorities(@Param(value = "list") List<SystemAuthority> list);

    /**
     * 根据id查询指定系统权限表
     * @param id  主键
     * @return
     */
    Map<String, Object> getSystemAuthorityById(String id);

    /**
     * 根据id修改系统权限表
     * @param systemAuthority 系统权限表实体
     * @return
     */
    int editSystemAuthorityById(SystemAuthority systemAuthority);

    /**
     * 批量修改系统权限表
     *
     * @param systemAuthority 系统权限表实体
     * @param ids 主键集合
     */
    void editSystemAuthorityByIds(@Param("map") SystemAuthority systemAuthority, @Param("list") List<String> ids);

    /**
     * 根据id删除系统权限表
     * @param id
     * @return
     */
    int delSystemAuthorityById(String id);

    /**
     * 批量删除系统权限表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemAuthorityByIds(List<String> ids);

    /**
     * 真删除系统权限表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delSystemAuthorityRealById(String id);

    /**
     * 真批量删除系统权限表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delSystemAuthorityRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllSystemAuthorityReal();

    /**
     * 获取所有系统权限表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getSystemAuthorities(Map<String, Object> map);
}
