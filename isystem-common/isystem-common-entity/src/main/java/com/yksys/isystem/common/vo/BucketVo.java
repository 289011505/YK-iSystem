package com.yksys.isystem.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description:
 * @author: YuKai Fan
 * @create: 2020-03-05 09:13
 **/
@Data
public class BucketVo implements Serializable {

    private static final long serialVersionUID = -4409942019280768867L;

    //存储空间名称
    private String bucketName;
    //存储类型
    private String storageType;
    //数据容灾类型
    private String dataRedundancyType;
    //存储空间权限
    private String cannedACL;
}