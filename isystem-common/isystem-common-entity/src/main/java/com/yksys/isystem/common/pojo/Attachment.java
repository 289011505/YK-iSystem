package com.yksys.isystem.common.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * @author YuKai Fan
 * @create 2020-02-28 15:46:32
 */
@Data
public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;

    //附件标识
    private String id;
    //所属标识
    private String ownerId;
    //文件地址url
    private String attachUrl;
    //文件原名
    private String attachOriginTitle;
    //附件属性，例身份证证明
    private String attachUtily;
    //附件类型0图片1文件2视频
    private Integer attachType;
    //文件名
    private String attachName;
    //文件大小
    private Long attachSize;
    //文件后缀
    private String attachPostfix;
    //备注
    private String remark;
    //状态:0  已禁用 1 正在使用
    private Integer status;

}
