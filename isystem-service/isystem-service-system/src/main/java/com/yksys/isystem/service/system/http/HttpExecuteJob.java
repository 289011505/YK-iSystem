package com.yksys.isystem.service.system.http;

import com.alibaba.fastjson.JSONObject;
import com.yksys.isystem.common.core.security.http.YkRestTemplate;
import com.yksys.isystem.common.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 微服务远程调用
 * @author: YuKai Fan
 * @create: 2020-04-07 17:10
 **/
@Slf4j
public class HttpExecuteJob implements Job {
    @Autowired
    private YkRestTemplate restTemplate;

    /**
     * 负载均衡
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String serviceId = dataMap.getString("serviceId");
        String method = dataMap.getString("method");
        method = StringUtil.isBlank(method) ? "POST" : method;
        String path = dataMap.getString("path");
        String contentType = dataMap.getString("contentType");
        String body = dataMap.getString("body");
        ServiceInstance serviceIns = loadBalancerClient.choose(serviceId);
        //获取服务实例
        if (serviceIns == null) {
            throw new RuntimeException(String.format("%s服务暂不可用", serviceId));
        }
        String url = String.format("%s%s", serviceIns.getUri(), path);
        HttpHeaders headers = new HttpHeaders();
        HttpMethod httpMethod = HttpMethod.resolve(method.toUpperCase());
        HttpEntity requestEntity = null;
        headers.setContentType(MediaType.parseMediaType(contentType));
        if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            //json格式
            requestEntity = new HttpEntity(body, headers);
        } else {
            //表单形式
            //封装参数, 千万不要替换Map与HashMap，否则参数无法传递
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            if (StringUtil.isNotBlank(body)) {
                Map map = JSONObject.parseObject(body, Map.class);
                params.putAll(map);
                requestEntity = new HttpEntity(params, headers);
            }
        }

        log.debug("====>> url[{}] method[{}] data=[{}]", url, httpMethod, requestEntity);
        ResponseEntity<String> result = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
        log.info(result.getBody());
    }
}