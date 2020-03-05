package com.yksys.isystem.service.business.service.impl;


import com.github.pagehelper.PageHelper;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.pojo.Article;
import com.yksys.isystem.service.business.mapper.ArticleMapper;
import com.yksys.isystem.service.business.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @program: YK-iSystem
* @description:
* @author: YuKai Fan
* @create: 2019-12-03 20:05
**/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article addArticle(Article article) {
        article.setId(AppUtil.randomId());
        article.setStatus(1);
        articleMapper.addArticle(article);
        return article;
    }

    @Override
    public Map<String, Object> getArticleById(String id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public List<Map<String, Object>> getArticles(int start, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(start, pageSize);
        return this.getArticles(map);
    }

    @Override
    public List<Map<String, Object>> getArticles(Map<String, Object> map) {
        return articleMapper.getArticles(map);
    }

    @Override
    public void editArticle(Article article) {
        articleMapper.editArticleById(article);
    }

    @Override
    public void delArticleById(String id) {
        articleMapper.delArticleById(id);
    }

    @Override
    public void delArticleByIs(List<String> ids) {
        articleMapper.delArticleByIds(ids);
    }

    @Override
    public void delArticleRealById(String id) {
        articleMapper.delArticleRealById(id);
    }

    @Override
    public void delArticleRealByIds(List<String> ids) {
        articleMapper.delArticleRealByIds(ids);
    }

}
