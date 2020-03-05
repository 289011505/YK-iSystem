package com.yksys.isystem.service.business.mapper;

import com.yksys.isystem.common.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 系统文章管理表mapper
 * @author: YuKai Fan
 * @create: 2019-12-03 14:56
 **/
public interface ArticleMapper {
    /**
     * 新增文章管理表
     * @param article 文章管理表实体
     * @return
     */
    int addArticle(Article article);

    /**
     * 批量新增文章管理表
     * @param list 文章管理表集合
     */
    void addArticles(@Param(value = "list") List<Article> list);

    /**
     * 根据id查询指定文章管理表
     * @param id  主键
     * @return
     */
    Map<String, Object> getArticleById(String id);

    /**
     * 根据id修改文章管理表
     * @param article 文章管理表实体
     * @return
     */
    int editArticleById(Article article);

    /**
     * 批量修改文章管理表
     *
     * @param article 文章管理表实体
     * @param ids 主键集合
     */
    void editArticleByIds(@Param("map") Article article, @Param("list") List<String> ids);

    /**
     * 根据id删除文章管理表
     * @param id
     * @return
     */
    int delArticleById(String id);

    /**
     * 批量删除文章管理表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delArticleByIds(List<String> ids);

    /**
     * 真删除文章管理表
     *
     * @param id 主键
     * @return dao成功失败标志
     */
    int delArticleRealById(String id);

    /**
     * 真批量删除文章管理表
     *
     * @param ids 主键集合
     * @return dao成功失败标志
     */
    int delArticleRealByIds(List<String> ids);

    /**
     * 全部真删除
     * @return
     */
    int delAllArticleReal();

    /**
     * 获取所有文章管理表.
     * @param map 页面表单
     * @return 结果集合
     */
    List<Map<String, Object>> getArticles(Map<String, Object> map);
}
