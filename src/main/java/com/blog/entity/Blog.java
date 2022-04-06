package com.blog.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxl
 * @since 2022-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Blog implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 文章标题
     */
    @TableId("blog_title")
    private String blogTitle;

    /**
     * 文章预览
     */
    @TableField("blog_preface")
    private String blogPreface;

    /**
     * 文章内容
     */
    @TableField("blog_content")
    private String blogContent;

    /**
     * 文章分类
     */
    @TableField("blog_category_id")
    private Integer blogCategoryId;

    /**
     * 文章状态
     */
    @TableField("blog_status")
    private Integer blogStatus;

    /**
     * 文章查看次数
     */
    @TableField("blog_views")
    private Integer blogViews;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
