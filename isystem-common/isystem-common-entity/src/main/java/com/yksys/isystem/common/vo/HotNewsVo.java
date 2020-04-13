package com.yksys.isystem.common.vo;

import java.io.Serializable;

import lombok.Data;
import com.google.common.base.Converter;
import com.yksys.isystem.common.pojo.HotNews;
import org.springframework.beans.BeanUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 热点新闻表 vo类
 *
 * @author YuKai Fan
 * @create 2020-04-13 15:32:29
 */
@Data
@ApiModel(value = "热点新闻表vo对象", description = "热点新闻表")
public class HotNewsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //热点新闻标识
    @ApiModelProperty(value = "热点新闻标识", dataType = "String")
    private String id;
    //热点新闻主题
    @ApiModelProperty(value = "热点新闻主题", dataType = "String")
    private String subject;
    //热点新闻标题
    @ApiModelProperty(value = "热点新闻标题", dataType = "String")
    private String title;
    //热点新闻内容
    @ApiModelProperty(value = "热点新闻内容", dataType = "String")
    private String content;
    //新闻链接
    @ApiModelProperty(value = "新闻链接", dataType = "String")
    private String url;
    //发布时间
    @ApiModelProperty(value = "发布时间", dataType = "DateTime")
    private String publishTime;
    //发布平台
    @ApiModelProperty(value = "发布平台", dataType = "String")
    private String publishPlatform;
    //转发数
    @ApiModelProperty(value = "转发数", dataType = "Integer")
    private Integer forwardNum;
    //评论数
    @ApiModelProperty(value = "评论数", dataType = "Integer")
    private Integer commentNum;
    //点赞数
    @ApiModelProperty(value = "点赞数", dataType = "Integer")
    private Integer likeNum;
    //序号
    @ApiModelProperty(value = "序号", dataType = "Integer")
    private Integer sort;
    //新闻类型
    @ApiModelProperty(value = "新闻类型", dataType = "Integer")
    private Integer type;
    //热点类型1：热, 2: 新, 3: 荐
    @ApiModelProperty(value = "热点类型1：热, 2: 新, 3: 荐", dataType = "Integer")
    private Integer hotType;
    //备注
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;
    //状态:0  已禁用 1 正在使用
    @ApiModelProperty(value = "状态:0  已禁用 1 正在使用", dataType = "Integer")
    private Integer status;

    public HotNews convert() {
        HotNewsVoConvert hotNewsVoConvert = new HotNewsVoConvert();
        HotNews hotNews = hotNewsVoConvert.convert(this);
        return hotNews;
    }

    private static class HotNewsVoConvert extends Converter<HotNewsVo, HotNews> {

        @Override
        protected HotNews doForward(HotNewsVo hotNewsVo) {
            HotNews hotNews = new HotNews();
            BeanUtils.copyProperties(hotNewsVo, hotNews);
            return hotNews;
        }

        @Override
        protected HotNewsVo doBackward(HotNews hotNews) {
            return null;
        }
    }

}
