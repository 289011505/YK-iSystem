package com.yksys.isystem.service.business.service;
import com.yksys.isystem.common.pojo.Article;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 文章管理表 service
 * @author: YuKai Fan
 * @create: 2020-03-05 16:19:34
 **/
public interface ArticleService {
    /**
     * 新增文章管理表
     * @param article
     * @return
     */
    Article addArticle(Article article);

    /**
     * 根据id查询文章管理表
     * @param id
     * @return
     */
    Map<String, Object> getArticleById(String id);

    /**
     * 获取所有文章管理表(分页)
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getArticles(int start, int pageSize, Map<String, Object> map);

    /**
     * 获取所有文章管理表
     * @param map 参数
     * @return
     */
    List<Map<String, Object>> getArticles(Map<String, Object> map);

    /**
     * 修改文章管理表
     * @param article
     */
    void editArticle(Article article);

    /**
     * 根据id删除文章管理表(软删除)
     * @param id
     */
    void delArticleById(String id);

    /**
     * 批量删除(软删除)
     * @param ids
     */
    void delArticleByIs(List<String> ids);

    /**
     * 根据id删除文章管理表(真删除)
     * @param id
     */
    void delArticleRealById(String id);

    /**
     * 批量删除(真删除)
     * @param ids
     */
    void delArticleRealByIds(List<String> ids);
}
