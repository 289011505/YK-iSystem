package com.yksys.isystem.service.business.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.pojo.HotNews;
import com.yksys.isystem.common.vo.HotNewsVo;
import com.yksys.isystem.service.business.service.HotNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 热点新闻表
 * @author: YuKai Fan
 * @create: 2020-04-13 15:32:29
 *
 */
@Api(tags = "热点新闻表")
@RestController
@RequestMapping("/api/hotNews")
public class HotNewsController {
    @Autowired
    private HotNewsService hotNewsService;

    /**
     * 新增热点新闻表
     * @return
     */
    @ApiOperation("新增热点新闻表")
    @PostMapping("/addHotNews")
    public Result addHotNews(@RequestBody @ApiParam(name = "热点新闻表vo对象", required = true) HotNewsVo hotNewsVo) {
        HotNews hotNews = hotNewsVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", hotNewsService.addHotNews(hotNews));
    }

    /**
     * 根据id查询热点新闻表
     * @param id
     * @return
     */
    @ApiOperation("根据id查询热点新闻表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @GetMapping("/getHotNewsById")
    public Result getHotNewsById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", hotNewsService.getHotNewsById(id));
    }

    /**
     * 更新热点新闻表
     *
     * @return
     */
    @ApiOperation("更新热点新闻表")
    @PutMapping("/editHotNews")
    public Result editHotNews(@RequestBody @ApiParam(name = "热点新闻表vo对象", required = true) HotNewsVo hotNewsVo) {
        HotNews hotNews = hotNewsVo.convert();
        hotNewsService.editHotNews(hotNews);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除热点新闻表
     * @param id
     * @return
     */
    @ApiOperation("根据id删除热点新闻表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", paramType = "string")
    })
    @DeleteMapping("/delHotNews")
    public Result delHotNewsById(@RequestParam String id) {
        hotNewsService.delHotNewsById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除热点新闻表
     * @param ids
     * @return
     */
    @ApiOperation("根据ids批量删除热点新闻表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "id数组", paramType = "string[]")
    })
    @DeleteMapping("/delHotNews/{ids}")
    public Result delHotNewsByIds(@PathVariable("ids") String[] ids) {
        hotNewsService.delHotNewsByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的热点新闻表(不分页)
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有的热点新闻表(不分页)")
    @GetMapping("/getHotNews/noPage")
    public Result getHotNews(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", hotNewsService.getHotNews(map));
    }

    /**
     * 获取所有热点新闻表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @ApiOperation("获取所有热点新闻表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", required = true, value = "开始记录", paramType = "int"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "分页大小", paramType = "int")
    })
    @GetMapping("/getHotNews")
    public Result getHotNews(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = hotNewsService.getHotNews(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
