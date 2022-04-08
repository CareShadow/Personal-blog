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
    private Integer page;
    private Integer count;
    private String search;
    public Pagination(Integer page,Integer count,String search){
        this.page = page;
        this.count = count;
        this.search = search;
    }
}
