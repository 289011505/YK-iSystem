package com.yksys.isystem.common.core.configure.auto;

import com.yksys.isystem.common.core.interceptor.SqlAppendInterceptor;
import com.yksys.isystem.common.core.security.http.YkRestTemplate;
import com.yksys.isystem.common.core.security.oauth2.client.Oauth2ClientProperties;
import com.yksys.isystem.common.core.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Bean
    @ConditionalOnMissingBean(SpringContextUtil.class)
    public SpringContextUtil springContextUtil() {
        SpringContextUtil springContextUtil = new SpringContextUtil();
        logger.info("springContextUtil [{}]", springContextUtil);
        return springContextUtil;
    }

    @Bean
    @ConditionalOnMissingBean(SqlAppendInterceptor.class)
    public SqlAppendInterceptor sqlAppendInterceptor() {
        return new SqlAppendInterceptor();
    }
}