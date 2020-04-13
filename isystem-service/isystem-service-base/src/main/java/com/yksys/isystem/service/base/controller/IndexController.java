package com.yksys.isystem.service.base.controller;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.service.base.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 首页controller
 * @author: YuKai Fan
 * @create: 2020-04-13 10:49
 **/
@RestController
@RequestMapping("/api/index")
public class IndexController {
    @Autowired
    private IndexService indexService;


    /**
     * 获取首页数据(操作访问量, 消息, 新闻统计)
     * @param map
     * @return
     */
    @GetMapping("getIndexCountData")
    public Result getIndexCountData(@RequestParam Map<String, Object> map) {
        Map<String, Object> indexCountData = indexService.getIndexCountData(map);
        return new Result(HttpStatus.OK.value(), "获取成功", indexCountData);
    }

    /**
     * 获取首页数据(疫情情况图, 编程语言图, 服务性能图)
     * @param map
     * @return
     */
    @GetMapping("getIndexChartData")
    public Result getIndexChartData(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功");
    }

    /**
     * 获取首页数据(新闻热搜, todo列表, 个人信息数据)
     * @param map
     * @return
     */
    @GetMapping("getIndexInfoData")
    public Result getIndexInfoData(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功");
    }


}