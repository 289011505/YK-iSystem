package com.yksys.isystem.service.auth.service.feign.fallback;

import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.hystrix.Fallback;
import com.yksys.isystem.service.auth.service.feign.EmailService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-04-07 16:52
 **/
@Component
public class EmailServiceFallback implements FallbackFactory<EmailService> {
    @Override
    public EmailService create(Throwable throwable) {
        return new EmailService() {
            @Override
            public Result sendEmail(String recipients, String cc, String subject, String content, List<Map<String, String>> attachUrls) {
                return Fallback.badGateway();
            }

            @Override
            public Result sendTplEmail(String recipients, String cc, String subject, String tplCode, String tplParams, List<Map<String, String>> attachUrls) {
                return Fallback.badGateway();
            }
        };
    }
}