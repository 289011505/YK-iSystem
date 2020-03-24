package com.yksys.isystem.service.system.util;

import com.yksys.isystem.common.core.utils.SpringContextUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.model.TaskInfo;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: YK-iSystem
 * @description: 任务执行工具类
 * @author: YuKai Fan
 * @create: 2020-03-24 09:54
 **/
public class TaskInvokeUtil {

    /**
     * 执行方法
     * @param taskInfo
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     */
    public static void invoke(TaskInfo taskInfo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        String jobClassName = taskInfo.getJobClassName();
        String beanName = getBeanName(jobClassName);
        String methodName = getMethodName(jobClassName);
        List<Object[]> methodParams = getMethodParams(jobClassName);

        if (!isValidClassName(beanName)) {
            Object bean = SpringContextUtil.getBean(beanName);
            invokeMethod(bean, methodName, methodParams);
        } else {
            Object bean = Class.forName(beanName).newInstance();
            invokeMethod(bean, methodName, methodParams);
        }
    }

    /**
     * 调用任务方法
     *
     * @param bean 目标对象
     * @param methodName 方法名称
     * @param methodParams 方法参数
     */
    private static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        if (StringUtil.isNotBlank(methodParams) && methodParams.size() > 0) {
            Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        }
        else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }

    /**
     * 校验是否为为class包名
     *
     * @param jobClassName 名称
     * @return true是 false否
     */
    public static boolean isValidClassName(String jobClassName) {
        return StringUtils.countMatches(jobClassName, ".") > 1;
    }

    /**
     * 获取bean名称
     *
     * @param jobClassName 目标类名
     * @return bean名称
     */
    public static String getBeanName(String jobClassName) {
        String beanName = StringUtil.substringBefore(jobClassName, "(");
        return StringUtil.substringBeforeLast(beanName, ".");
    }

    /**
     * 获取bean方法
     *
     * @param jobClassName 目标类名
     * @return method方法
     */
    public static String getMethodName(String jobClassName) {
        String methodName = StringUtil.substringBefore(jobClassName, "(");
        return StringUtil.substringAfterLast(methodName, ".");
    }

    /**
     * 获取method方法参数相关列表
     *
     * @param jobClassName 目标类名
     * @return method方法相关参数列表
     */
    public static List<Object[]> getMethodParams(String jobClassName) {
        String methodStr = StringUtils.substringBetween(jobClassName, "(", ")");
        if (StringUtils.isEmpty(methodStr))
        {
            return null;
        }
        String[] methodParams = methodStr.split(",");
        List<Object[]> classs = new LinkedList<>();
        for (int i = 0; i < methodParams.length; i++)
        {
            String str = StringUtils.trimToEmpty(methodParams[i]);
            // String字符串类型，包含'
            if (StringUtils.contains(str, "'"))
            {
                classs.add(new Object[] { StringUtils.replace(str, "'", ""), String.class });
            }
            // boolean布尔类型，等于true或者false
            else if (StringUtils.equals(str, "true") || StringUtils.equalsIgnoreCase(str, "false"))
            {
                classs.add(new Object[] { Boolean.valueOf(str), Boolean.class });
            }
            // long长整形，包含L
            else if (StringUtils.containsIgnoreCase(str, "L"))
            {
                classs.add(new Object[] { Long.valueOf(StringUtils.replaceIgnoreCase(str, "L", "")), Long.class });
            }
            // double浮点类型，包含D
            else if (StringUtils.containsIgnoreCase(str, "D"))
            {
                classs.add(new Object[] { Double.valueOf(StringUtils.replaceIgnoreCase(str, "D", "")), Double.class });
            }
            // 其他类型归类为整形
            else
            {
                classs.add(new Object[] { Integer.valueOf(str), Integer.class });
            }
        }
        return classs;
    }

    /**
     * 获取参数类型
     *
     * @param methodParams 参数相关列表
     * @return 参数类型列表
     */
    public static Class<?>[] getMethodParamsType(List<Object[]> methodParams)
    {
        Class<?>[] classs = new Class<?>[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams)
        {
            classs[index] = (Class<?>) os[1];
            index++;
        }
        return classs;
    }

    /**
     * 获取参数值
     *
     * @param methodParams 参数相关列表
     * @return 参数值列表
     */
    public static Object[] getMethodParamsValue(List<Object[]> methodParams)
    {
        Object[] classs = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams)
        {
            classs[index] = (Object) os[0];
            index++;
        }
        return classs;
    }
}