package com.yksys.isystem.common.core.constants;

/**
 * @program: YK-iSystem
 * @description: 定时任务常量
 * @author: YuKai Fan
 * @create: 2020-03-24 08:50
 **/
public class ScheduleConstant {
    // 任务类型 简单任务
    public final static String JOB_TRIGGER_SIMPLE = "simple";

    // 任务类型 定时任务
    public final static String JOB_TRIGGER_CRON = "cron";

    /** 默认 */
    public static final String MISFIRE_DEFAULT = "0";

    /** 立即触发执行 */
    public static final String MISFIRE_IGNORE_MISFIRES = "1";

    /** 触发一次执行 */
    public static final String MISFIRE_FIRE_AND_PROCEED = "2";

    /** 不触发立即执行 */
    public static final String MISFIRE_DO_NOTHING = "3";

    /** 本地任务 */
    public static final String LOCAL_TASK = "local_task";

    /** 远程任务 */
    public static final String HTTP_TASK = "http_task";

    /** 执行目标key */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";
}