package com.blog.model;

import lombok.Data;

/**
 * @ClassName ReqBlog
 * @Description TODO
 * @Author admin
 * @Date 2022/4/7 9:49
 * @Version 1.0
 **/
@Data
public class ReqBlog {
    private String blogTitle;
    private String blogPreface;
    private String blogContent;
    private Integer blogCategoryId;
    private Integer blogStatus;
}
