package com.yksys.isystem.service.business.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.Article;
import com.yksys.isystem.common.vo.ArticleVo;
import com.yksys.isystem.service.business.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 文章管理表
 * @author: YuKai Fan
 * @create: 2020-03-05 16:19:34
 *
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 新增文章管理表
     * @return
     */
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody ArticleVo articleVo) {
        Article article = articleVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", articleService.addArticle(article));
    }

    /**
     * 根据id查询文章管理表
     * @param id
     * @return
     */
    @PostMapping("/getArticleById")
    public Result getArticleById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", articleService.getArticleById(id));
    }

    /**
     * 更新文章管理表
     *
     * @return
     */
    @PutMapping("/editArticle")
    public Result editArticle(@RequestBody ArticleVo articleVo) {
        Article article = articleVo.convert();
        articleService.editArticle(article);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除文章管理表
     * @param id
     * @return
     */
    @DeleteMapping("/delArticle")
    public Result delArticleById(@RequestParam String id) {
        articleService.delArticleById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除文章管理表
     * @param ids
     * @return
     */
    @DeleteMapping("/delArticle/{ids}")
    public Result delArticleByIds(@PathVariable("ids") String[] ids) {
        articleService.delArticleByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的文章管理表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getArticles/noPage")
    public Result getArticles(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", articleService.getArticles(map));
    }

    /**
     * 获取所有文章管理表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getArticles")
    public Result getArticles(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = articleService.getArticles(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
