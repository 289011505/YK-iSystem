package com.yksys.isystem.service.base.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.service.base.service.IndexService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
     * 获取首页数据(cpu, jvm服务性能图)
     * @param map
     * @return
     */
    @GetMapping("getIndexChartData")
    public Result getIndexChartData(@RequestParam Map<String, Object> map) {
        Map<String, Object> indexChartData = indexService.getIndexChartData(map);
        return new Result(HttpStatus.OK.value(), "获取成功", indexChartData);
    }

    /**
     * 获取首页数据(新闻热搜, todo列表, 个人信息数据)
     * @param map
     * @return
     */
    @GetMapping("getIndexInfoData")
    public Result getIndexInfoData(@RequestParam Map<String, Object> map) {
        Map<String, Object> indexInfoData = indexService.getIndexInfoData(map);
        return new Result(HttpStatus.OK.value(), "获取成功", indexInfoData);
    }

    /**
     * 获取所有热点新闻表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getHotNews")
    public Result getHotNews(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                             @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = indexService.getHotNews(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }


}