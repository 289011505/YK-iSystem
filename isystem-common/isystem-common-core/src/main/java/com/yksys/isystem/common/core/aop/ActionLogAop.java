package com.yksys.isystem.common.core.aop;

import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.annotation.ActionLog;
import com.yksys.isystem.common.core.constants.LogTypeEnum;
import com.yksys.isystem.common.core.utils.TimeUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
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
        //执行切入点, 获取返回值
        Object proceed = joinPoint.proceed();
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
        map.put("ipAddr", "");
        map.put("projectName", targetMethod.getDeclaringClass().getName() + "." + targetMethod.getName());

        if (requestAttributes.getRequest() != null
                && !CollectionUtils.isEmpty(requestAttributes.getRequest().getParameterMap())) {
            String paramReq = "";
            map.put("inputParam", Arrays.toString(args) + ":" + paramReq);
        }

        //存入redis

        //打印log

        return proceed;
    }
}