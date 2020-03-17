package com.yksys.isystem.service.workflow.controller;

import com.github.pagehelper.PageInfo;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.vo.ActivitiModelVo;
import com.yksys.isystem.service.workflow.service.ActivitiProcessService;
import com.yksys.isystem.service.workflow.service.ActivitiService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 工作流模型controller
 * @author: YuKai Fan
 * @create: 2020-03-12 16:18
 **/
@RestController
@RequestMapping("/api/activiti")
public class ActivitiModelController {
    @Autowired
    private ActivitiService activitiService;

    /**
     * 获取模型列表(分页)
     * @param start
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping("/getModels")
    public Result getModels(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                            @RequestParam Map<String, Object> map) {
        PageInfo<Model> pageList = activitiService.getModels(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", pageList);
    }

    /**
     * 获取模型列表
     * @param map
     * @return
     */
    @GetMapping("/getModels/noPage")
    public Result getModels(@RequestParam Map<String, Object> map) {
        List<Model> list = activitiService.getModels(map);
        return new Result(HttpStatus.OK.value(), "获取成功", list);
    }

    /**
     * 新建模型
     * @param request
     * @param response
     * @param activitiModelVo
     * @return
     */
    @PostMapping("/createNewModel")
    public Result createNewModel(HttpServletRequest request, HttpServletResponse response, @RequestBody ActivitiModelVo activitiModelVo) {
        String modelId = activitiService.createNewModel(request, response, activitiModelVo);
        return new Result(HttpStatus.OK.value(), "创建成功", modelId);
    }

    /**
     * 删除模型
     * @param id
     * @return
     */
    @DeleteMapping("/delModel")
    public Result delModel(@RequestParam String id) {
        activitiService.delModel(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 删除模型
     * @param ids
     * @return
     */
    @DeleteMapping("/delModel/{ids}")
    public Result delModelByIds(@PathVariable("ids") String[] ids) {
        activitiService.delModelByIds(ids);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 同步用户, 角色数据 到Activiti表中
     * @return
     */
    @PostMapping("/synchronizeData")
    public Result synchronizeData() {
        activitiService.synchronizeData();
        return new Result(HttpStatus.OK.value(), "同步成功");
    }

    /**
     * 根据模型id部署
     * @param id
     * @return
     */
    @PostMapping("/deployByModelId/{id}")
    public Result deployByModelId(@PathVariable("id") String id) {
        activitiService.deployByModelId(id);
        return new Result(HttpStatus.OK.value(), "部署成功");
    }
}