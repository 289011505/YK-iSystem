package com.yksys.isystem.service.admin.service;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.SystemUser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 系统用户表 service
 * @author: YuKai Fan
 * @create: 2020-01-16 14:11:47
 **/
public interface SystemUserService {
    /**
     * 新增系统用户表
     * @param systemUser
     * @return
     */
    SystemUser addSystemUser(SystemUser systemUser);

    /**
     * 根据id查询系统用户表
     * @param id
     * @return
     */
    Map<String, Object> getSystemUserById(String id);

    /**
     * 获取所有系统用户表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemUsers(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有系统用户表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getSystemUsers(Map<String, Object> map);

    /**
     * 修改系统用户表
     * @param systemUser
     */
    void editSystemUser(SystemUser systemUser);

    /**
     * 根据id删除系统用户表(软删除)
     * @param id
     */
    void delSystemUserById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delSystemUserByIs(List<String> ids);

    /**
     * 根据id删除系统用户表(真删除)
     * @param id
     */
    void delSystemUserRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delSystemUserRealByIds(List<String> ids);

    /**
     * 更新用户头像
     * @param request
     * @return
     */
    Result updateUserIcon(HttpServletRequest request) throws IOException;
}
