package com.blog.controller;

import com.blog.dao.BlogMapper;
import com.blog.entity.Blog;
import com.blog.pojo.Pagination;
import com.blog.pojo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 16:03
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {
    @Autowired(required = false)
    private BlogMapper blogMapper;
    /**
     * 功能描述：获取博客列表的信息
     * @param: [page, count]
     * @return: com.blog.pojo.ResultVO<java.util.List<com.blog.entity.Blog>>
     * @auther: lxl
     * @date: 2022/4/6 20:49
     */
    @GetMapping("/list/{page}/{count}")
    @Transactional
    public ResultVO<Map<String,Object>> getAllBlog(@PathVariable("page") int page, @PathVariable("count") int count){
        // 算出起始位置(page-1)*count;
        Pagination pagination = new Pagination((page-1)*count,count);
        List<Blog> blogList = blogMapper.getBlog(pagination);
        Integer Count = blogMapper.getTotal();
        Integer Pages = Count%page==0?Count/page:Count/page+1;
        Map<String,Object> map = new HashMap<>();
        map.put("blogList",blogList);
        map.put("total",Count);
        map.put("size",count);
        map.put("current",page);
        map.put("pages",Pages);
        return new ResultVO<>(map);
    }
}
