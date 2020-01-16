package com.yksys.isystem.common.core.security.http;

import com.yksys.isystem.common.core.event.RemoteRefreshRouteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.client.RestTemplate;

/**
 * @program: YK-iSystem
 * @description: 自定义RestTemplate请求工具类
 * @author: YuKai Fan
 * @create: 2020-01-16 10:43
 **/
@Slf4j
public class YkRestTemplate extends RestTemplate {

    private ApplicationEventPublisher publisher;
    public YkRestTemplate(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 刷新网关
     * 注:不要频繁调用!
     * 1.资源权限发生变化时可以调用
     * 2.流量限制变化时可以调用
     * 3.IP访问发生变化时可以调用
     * 4.智能路由发生变化时可以调用
     */
    public void refreshGateway() {
        try {
            publisher.publishEvent(new RemoteRefreshRouteEvent(this));
            log.info("refreshGateway: success");
        } catch (Exception e) {
            log.error("refreshGateway error: {}", e.getMessage());
        }
    }
}