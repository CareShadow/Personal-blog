package com.blog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.blog.entity.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RespBlog
 * @Description TODO
 * @Author admin
 * @Date 2022/4/9 22:59
 * @Version 1.0
 **/

@Data
public class RespBlog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文章标题
     */
    private String blogTitle;

    /**
     * 文章预览
     */
    private String blogPreface;

    /**
     * 文章内容
     */
    private String blogContent;

    /**
     * 文章分类
     */
    private Category category;

    /**
     * 文章状态
     */
    private Integer blogStatus;

    /**
     * 文章查看次数
     */
    private Integer blogViews;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")    //将Date转换成String,一般后台传值给前台时
    private Date updateTime;


}