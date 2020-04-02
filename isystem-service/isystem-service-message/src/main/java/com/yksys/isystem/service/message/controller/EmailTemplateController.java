package com.yksys.isystem.service.message.controller;

import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.pojo.EmailTemplate;
import com.yksys.isystem.common.vo.EmailTemplateVo;
import com.yksys.isystem.service.message.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @program: YK-iSystem
 * @description: 邮件模板表
 * @author: YuKai Fan
 * @create: 2020-03-30 20:49:36
 *
 */
@RestController
@RequestMapping("/api/emailTemplate")
public class EmailTemplateController {
    @Autowired
    private EmailTemplateService emailTemplateService;

    /**
     * 新增邮件模板表
     * @return
     */
    @PostMapping("/addEmailTemplate")
    public Result addEmailTemplate(@RequestBody EmailTemplateVo emailTemplateVo) {
        EmailTemplate emailTemplate = emailTemplateVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", emailTemplateService.addEmailTemplate(emailTemplate));
    }

    /**
     * 根据id查询邮件模板表
     * @param id
     * @return
     */
    @GetMapping("/getEmailTemplateById")
    public Result getEmailTemplateById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", emailTemplateService.getEmailTemplateById(id));
    }

    /**
     * 更新邮件模板表
     *
     * @return
     */
    @PutMapping("/editEmailTemplate")
    public Result editEmailTemplate(@RequestBody EmailTemplateVo emailTemplateVo) {
        EmailTemplate emailTemplate = emailTemplateVo.convert();
        emailTemplateService.editEmailTemplate(emailTemplate);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除邮件模板表
     * @param id
     * @return
     */
    @DeleteMapping("/delEmailTemplate")
    public Result delEmailTemplateById(@RequestParam String id) {
        emailTemplateService.delEmailTemplateById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除邮件模板表
     * @param ids
     * @return
     */
    @DeleteMapping("/delEmailTemplate/{ids}")
    public Result delEmailTemplateByIds(@PathVariable("ids") String[] ids) {
        emailTemplateService.delEmailTemplateByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的邮件模板表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getEmailTemplates/noPage")
    public Result getEmailTemplates(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", emailTemplateService.getEmailTemplates(map));
    }

    /**
     * 获取所有邮件模板表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getEmailTemplates")
    public Result getEmailTemplates(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        List<Map<String, Object>> list = emailTemplateService.getEmailTemplates(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
