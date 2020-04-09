package com.yksys.isystem.common.core.aop;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.annotation.ActionLog;
import com.yksys.isystem.common.core.constants.LogTypeEnum;
import com.yksys.isystem.common.core.constants.RedisConstants;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.JsonUtil;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.core.utils.TimeUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

/**
 * @program: yk-isystem
 * @description: 操作日志aop
 * @author: YuKai Fan
 * @create: 2020-03-21 17:32
 **/
@Aspect
@Component
public class ActionLogAop {
    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 拦截所有使用@ActionLog的接口
     */
    @Pointcut("@annotation(com.yksys.isystem.common.core.annotation.ActionLog)")
    private void log(){}

    /**
     * 操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object printAndSaveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取当前请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        //读取ActionLog注解信息
        Method targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ActionLog actionLog = targetMethod.getAnnotation(ActionLog.class);
        //获取type
        LogTypeEnum logTypeEnum = actionLog.logType();
        //获取当前时间
        String currentDatetime = TimeUtil.getCurrentDatetime();
        //获取目标方法的参数
        Object[] args = joinPoint.getArgs();
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", logTypeEnum.getName());
        map.put("type", logTypeEnum.getType());
        map.put("projectName", targetMethod.getDeclaringClass().getName() + "." + targetMethod.getName());
        map.put("actionTime", currentDatetime);
        if (requestAttributes.getRequest() != null
                && CollectionUtils.isEmpty(requestAttributes.getRequest().getParameterMap())) {
            String paramReq = JsonUtil.objectToJson(requestAttributes.getRequest().getParameterMap());
            map.put("inputParam", Arrays.toString(args) + ":" + paramReq);
            map.put("ipAddr", getIpAddr(requestAttributes.getRequest()));
        }
        try {
            //执行切入点, 获取返回值
            Object proceed = joinPoint.proceed();
            map.put("outputParam", JsonUtil.objectToJson(proceed));
            //打印log
            logger.info(logTypeEnum.getName() + ":" + map.toString());

            return proceed;
        } catch (Throwable e) {
            map.put("exceptionInfo", e.getMessage());
        } finally {
            //存入redis
            redisUtil.set(RedisConstants.ACTION_LOG + AppUtil.randomId(), map);
        }

        return null;
    }

    /**
     * 获取请求ip地址
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                        logger.error("获取ip异常：{}" , Throwables.getStackTraceAsString(e));
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(',') > -1) { // "***.***.***.***".length()
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(','));
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}