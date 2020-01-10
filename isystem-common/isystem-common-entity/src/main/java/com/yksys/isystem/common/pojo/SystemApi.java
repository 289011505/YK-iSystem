package com.yksys.isystem.common.pojo;

import lombok.Data;

/**
 * @program: yk-isystem
 * @description: 系统api接口
 * @author: YuKai Fan
 * @create: 2020-01-10 22:51
 **/
@Data
public class SystemApi {
    //接口标识
    private String id;
    //接口编码
    private String apiCode;
    //接口名称
    private String apiName;
    //接口描述
    private String apiDesc;
    //请求方式
    private String requestType;
    //响应类型
    private String contentType;
    //类名
    private String className;
    //方法名
    private String methodName;
    //接口路径
    private String path;
    //服务id
    private String serviceId;
    //是否保留数据
    private Integer persist;
    //是否需要认证
    private Integer auth;
    //是否公开
    private Integer open;
    //状态
    private Integer status;
}