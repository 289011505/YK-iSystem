package com.yksys.isystem.common.core.configure.auto;

import com.yksys.isystem.common.core.security.http.YkRestTemplate;
import com.yksys.isystem.common.core.security.oauth2.client.Oauth2ClientProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2019-12-17 17:24
 **/
@Configuration
@EnableConfigurationProperties({Oauth2ClientProperties.class})
public class AutoConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 自定义oauth2请求类
     * @param publisher
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(YkRestTemplate.class)
    public YkRestTemplate ykRestTemplate(ApplicationEventPublisher publisher) {
        YkRestTemplate ykRestTemplate = new YkRestTemplate(publisher);
        return ykRestTemplate;
    }
}