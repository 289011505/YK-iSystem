package com.yksys.isystem.service.admin.mapper;

import com.yksys.isystem.common.pojo.UserIntroduce;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统用户简介表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface UserIntroduceMapper {
    /**
     * 新增用户简介表
     * @param userIntroduce 用户简介表实体
     * @return
     */
    int addUserIntroduce(UserIntroduce userIntroduce);

    /**
     * 批量新增用户简介表
     * @param list 用户简介表集合
     */
    void addUserIntroduces(@Param(value = "list") List<UserIntroduce> list);

    /**
     * 根据id查询指定用户简介表
     * @param id  主键
     * @return
     */
    Map<String, Object> getUserIntroduceById(String id);

    /**
     * 根据id修改用户简介表
     * @param userIntroduce 用户简介表实体
     * @return
     */
    int editUserIntroduceById(UserIntroduce userIntroduce);

    /**
     * 批量修改用户简介表
     *
     * @param userIntroduce 用户简介表实体
     * @param ids 主键集合
     */
    void editUserIntroduceByIds(@Param("map") UserIntroduce userIntroduce, @Param("list") List<String> ids);

    /**
     * 根据id删除用户简介表
     * @param id
     * @return
     */
    int delUserIntroduceById(String id);

    /**
     * 批量删除用户简介表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delUserIntroduceByIds(List<String> ids);

    /**
     * 真删除用户简介表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delUserIntroduceRealById(String id);

    /**
     * 真批量删除用户简介表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delUserIntroduceRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllUserIntroduceReal();

    /**
     * 获取所有用户简介表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getUserIntroduces(Map<String, Object> map);
}
