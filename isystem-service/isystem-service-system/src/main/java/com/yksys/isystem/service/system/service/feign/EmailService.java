package com.yksys.isystem.service.system.service.feign;

import com.yksys.isystem.common.core.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-07 16:51
 **/
@Component
@FeignClient(value = "isystem-service-message")
public interface EmailService {
    /**
     * 发送邮件
     * @param recipients 接收人 多个用; 号隔开
     * @param cc 抄送人 多个用; 号隔开
     * @param subject 主题
     * @param content 内容
     * @param attachUrls 附件地址
     * @return
     */
    @PostMapping("/api/email/sendEmail")
    Result sendEmail(@RequestParam(value = "recipients") String recipients,
                     @RequestParam(value = "cc") String cc,
                     @RequestParam(value = "subject") String subject,
                     @RequestParam(value = "content") String content,
                     @RequestParam(value = "attachUrls", required = false) List<Map<String, String>> attachUrls);

    /**
     * 发送模板邮件
     * @param recipients 接收人 多个用; 号隔开
     * @param cc 抄送人 多个用; 号隔开
     * @param subject 主题
     * @param tplCode 模板编码
     * @param tplParams 模板参数
     * @param attachUrls 附件地址
     * @return
     */
    @PostMapping("/api/email/sendTplEmail")
    Result sendTplEmail(@RequestParam(value = "recipients") String recipients,
                        @RequestParam(value = "cc") String cc,
                        @RequestParam(value = "subject") String subject,
                        @RequestParam(value = "tplCode") String tplCode,
                        @RequestParam(value = "tplParams") String tplParams,
                        @RequestParam(value = "attachUrls", required = false) List<Map<String, String>> attachUrls);
}