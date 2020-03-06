package com.yksys.isystem.common.core.utils.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @program: YK-iSystem
 * @description: 文件上传实体
 * @author: YuKai Fan
 * @create: 2020-03-06 10:39
 **/
@Data
public class FileModel implements Serializable {
    private static final long serialVersionUID = 6379008756033414785L;

    private MultipartFile file;
    //存储空间名称
    private String bucketName;
    //存储类型
    private String storageType;
    //数据容灾类型
    private String dataRedundancyType;
    //存储空间权限
    private String cannedACL;
}