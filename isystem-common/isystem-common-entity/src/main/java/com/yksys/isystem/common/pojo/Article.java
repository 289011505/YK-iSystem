package com.yksys.isystem.common.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * 文章管理表
 *
 * @author YuKai Fan
 * @create 2020-03-05 16:19:34
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    //唯一标识
    private String id;
    //文章标题
    private String title;
    //文章内容
    private String content;
    //文章封面
    private String articleCover;
    //文章摘要
    private String articleSummary;
    //重要性
    private Integer importance;
    //文章分类id
    private String articleClassId;
    //发布时间
    private String publishTime;
    //是否开启评论:0关闭1开启
    private Integer comment;
    //备注
    private String remark;
    //状态:0删除1已发布2未发布草稿
    private Integer status;

}
