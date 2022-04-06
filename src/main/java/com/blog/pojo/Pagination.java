package com.blog.pojo;

import lombok.Data;

/**
 * @ClassName Pagination
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 20:32
 * @Version 1.0
 **/
@Data
public class Pagination {
    private int page;
    private int count;
    public Pagination(int page,int count){
        this.page = page;
        this.count = count;
    }
}
