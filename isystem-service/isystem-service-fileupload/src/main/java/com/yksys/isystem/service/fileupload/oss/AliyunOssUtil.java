package com.yksys.isystem.service.fileupload.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.DataRedundancyType;
import com.aliyun.oss.model.StorageClass;
import com.yksys.isystem.common.core.constants.ComConstants;
import com.yksys.isystem.common.core.constants.OSSConstants;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.SpringContextUtil;
import com.yksys.isystem.common.vo.BucketVo;
import com.yksys.isystem.service.fileupload.configuration.AliyunOSSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @program: YK-iSystem
 * @description: 阿里云oss工具类
 * @author: YuKai Fan
 * @create: 2020-03-05 08:46
 **/
@Slf4j
@Component
public class AliyunOssUtil {

    private static AliyunOSSProperties aliyunOSSProperties;

    @Autowired
    public void init(AliyunOSSProperties aliyunOSSProperties) {
        AliyunOssUtil.aliyunOSSProperties = aliyunOSSProperties;
    }

    /**
     * 初始化oss
     * @return OSS
     */
    public static OSS OSSInit() {

        OSS ossClient = new OSSClientBuilder().build(aliyunOSSProperties.getEndPoint(),
                aliyunOSSProperties.getAccessKeyId(), aliyunOSSProperties.getAccessKeySecret());
        return ossClient;
    }

    /**
     * 创建存储空间
     * @param bucketVo
     * @return OSS
     */
    public static OSS createBucket(BucketVo bucketVo) {
        OSS oss = OSSInit();
        if (!oss.doesBucketExist(bucketVo.getBucketName())) {
            //创建CreateBucketRequest对象
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketVo.getBucketName());
            createBucketRequest.setStorageClass(storageClass(bucketVo.getStorageType()));
            createBucketRequest.setDataRedundancyType(dataRedundancyType(bucketVo.getDataRedundancyType()));
            createBucketRequest.setCannedACL(cannedACL(bucketVo.getCannedACL()));
            //创建存储空间
            oss.createBucket(createBucketRequest);
        }
        return oss;
    }

    /**
     * byte数组简单上传文件
     * @param bucketVo
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String uploadSimpleByByte(BucketVo bucketVo, ByteArrayInputStream byteArrayInputStream, String filePath) {
        OSS oss = createBucket(bucketVo);
        //上传文件byte数组
        try {
            oss.putObject(bucketVo.getBucketName(), filePath, byteArrayInputStream);
            oss.shutdown();
            return getFileUrl(bucketVo, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParameterException("上传失败");
        }
    }

    private static String getFileUrl(BucketVo bucketVo, String filePath) {
        String url = "";
        if (OSSConstants.ACL_READ.equals(bucketVo.getCannedACL()) || OSSConstants.ACL_READ_WRITE.equals(bucketVo.getCannedACL())) {
            url = ComConstants.HTTP + bucketVo.getBucketName() + "." + aliyunOSSProperties.getEndPoint() + "/" + filePath;
        } else {
            //如果访问权限为私有, 则需要签名访问
            url = ComConstants.HTTP + bucketVo.getBucketName() + "." + aliyunOSSProperties.getEndPoint() + "/" + filePath + "?xxxxx";
        }
        return url;
    }

    /**
     * 判断存储空间权限
     * @param cannedACL
     * @return
     */
    private static CannedAccessControlList cannedACL(String cannedACL) {
        if (OSSConstants.ACL_PRIVATE.equals(cannedACL)) {
            return CannedAccessControlList.Private;
        } else if (OSSConstants.ACL_READ.equals(cannedACL)) {
            return CannedAccessControlList.PublicRead;
        } else if (OSSConstants.ACL_READ_WRITE.equals(cannedACL)) {
            return CannedAccessControlList.PublicReadWrite;
        } else {
            return CannedAccessControlList.Private;
        }
    }

    /**
     * 判断容灾类型
     * @param dataRedundancyType
     * @return
     */
    private static DataRedundancyType dataRedundancyType(String dataRedundancyType) {
        if (OSSConstants.REDUNDANCY_REGION.equals(dataRedundancyType)) {
            return DataRedundancyType.ZRS;
        } else if (OSSConstants.REDUNDANCY_LOCAL.equals(dataRedundancyType)) {
            return DataRedundancyType.LRS;
        } else {
            return DataRedundancyType.LRS;
        }
    }

    /**
     * 判断存储类型
     * @param storageType
     * @return
     */
    private static StorageClass storageClass(String storageType) {
        if (OSSConstants.STORAGE_STANDARD.equals(storageType)) {
            return StorageClass.Standard;
        } else if (OSSConstants.STORAGE_ARCHIVE.equals(storageType)) {
            return StorageClass.Archive;
        } else if (OSSConstants.STORAGE_INFREQUENT_ACCESS.equals(storageType)) {
            return StorageClass.IA;
        } else {
            return StorageClass.Standard;
        }
    }
}