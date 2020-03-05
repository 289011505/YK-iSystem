package com.yksys.isystem.common.core.constants;

/**
 * @program: YK-iSystem
 * @description: oss常量
 * @author: YuKai Fan
 * @create: 2020-03-05 09:17
 **/
public class OSSConstants {
    //存储类型 标准
    public final static String STORAGE_STANDARD = "Standard";
    //存储类型 低频访问
    public final static String STORAGE_INFREQUENT_ACCESS = "InfrequentAccess";
    //存储类型 归档
    public final static String STORAGE_ARCHIVE = "Archive";

    //容灾类型 本地冗余
    public final static String REDUNDANCY_LOCAL = "Local";
    //容灾类型 同城冗余
    public final static String REDUNDANCY_REGION = "Region";

    //存储空间权限 公共读写
    public final static String ACL_READ_WRITE = "PubRW";
    //存储空间权限 公共读
    public final static String ACL_READ = "PubR";
    //存储空间权限 私有
    public final static String ACL_PRIVATE = "Private";
}