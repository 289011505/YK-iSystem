package com.yksys.isystem.service.generate.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.service.generate.service.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 代码生成controller
 * @author: YuKai Fan
 * @create: 2020-01-13 11:40
 **/
@Controller
@RequestMapping("/api/generate")
public class SysGeneratorController {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 获取所有数据库表
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/getDBTables")
    @ResponseBody
    public Result getTables(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                            @RequestParam Map<String, Object> map) {
        List<Map<String, String>> list = sysGeneratorService.getDBTables(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

    /**
     * 代码生成
     * @param tables
     * @param request
     * @param response
     */
    @RequestMapping(value = "/generateCode/{tables}", method = {RequestMethod.GET, RequestMethod.POST})
    public void generateCode(@PathVariable("tables") String[] tables, HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generateCode(tables);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}