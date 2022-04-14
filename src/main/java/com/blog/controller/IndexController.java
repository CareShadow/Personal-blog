package com.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.dao.BlogMapper;
import com.blog.dao.CategoryMapper;
import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.model.RespBlog;
import com.blog.pojo.Pagination;
import com.blog.pojo.ResultVO;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/12 18:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/index")
@Api(tags = "博客展示接口类--Controller")
public class IndexController {
    @Autowired(required = false)
    private BlogMapper blogMapper;
    @Autowired
    private BlogService blogService;
    @Autowired(required = false)
    private CategoryMapper categoryMapper;
    /**
     * 功能描述：
     * @param: [page, count, search]
     * @return: com.blog.pojo.ResultVO<java.util.Map<java.lang.String,java.lang.Object>>
     * @auther: lxl
     * @date: 2022/4/12 20:06
     */
    @PostMapping("/list")
    @ApiOperation("获取博客列表--search")
    @Transactional
    public ResultVO<Map<String,Object>> getAllBlog(int page, int count, String search){
        // 算出起始位置(page-1)*count;
        Pagination pagination = new Pagination((page-1)*count,count,search);
        List<RespBlog> blogList = blogMapper.getBlog(pagination);
        Integer Count = blogMapper.getTotal(search);
        Integer Pages = Count%page==0?Count/page:Count/page+1;
        List<Category> hotCategory = categoryMapper.getHotCategory();
        List<Blog> newBlog = blogMapper.getNewBlog();
        List<Blog> hotBlog = blogMapper.getHotBlog();
        Map<String,Object> map = new HashMap<>();
        map.put("blogList",blogList);
        map.put("total",Count);
        map.put("size",count);
        map.put("current",page);
        map.put("pages",Pages);
        map.put("hotCategory",hotCategory);
        map.put("newBlog",newBlog);
        map.put("hotBlog",hotBlog);
        return new ResultVO<>(map);
    }
    /**
     * 功能描述：
     * @param: []
     * @return: com.blog.pojo.ResultVO<java.util.List<com.blog.entity.Category>>
     * @auther: lxl
     * @date: 2022/4/12 20:07
     */
    @ApiOperation("获取热门文章分类列表--search")
    @GetMapping("/category/list")
    public ResultVO<List<Category>> getHotCategory(){
        List<Category> hotCategory = categoryMapper.getHotCategory();
        return new ResultVO<>(hotCategory);
    }
    /**
     * 功能描述：
     * @param: []
     * @return: com.blog.pojo.ResultVO<java.util.List<com.blog.entity.Blog>>
     * @auther: lxl
     * @date: 2022/4/12 20:07
     */
    @ApiOperation("获取最新文章分类列表")
    @GetMapping("/new/list")
    public ResultVO<List<Blog>> getNewBlog(){
        List<Blog> newBlog = blogMapper.getNewBlog();
        return new ResultVO<>(newBlog);
    }
    /**
     * 功能描述：
     * @param: []
     * @return: com.blog.pojo.ResultVO<java.util.List<com.blog.entity.Blog>>
     * @auther: lxl
     * @date: 2022/4/12 20:07
     */
    @ApiOperation("获取最热文章分类列表")
    @GetMapping("/hot/list")
    public ResultVO<List<Blog>> getHotBlog(){
        List<Blog> hotBlog = blogMapper.getHotBlog();
        return new ResultVO<>(hotBlog);
    }
    /**
     * 功能描述：
     * @param: [blog_title]
     * @return: com.blog.pojo.ResultVO<com.blog.entity.Blog>
     * @auther: lxl
     * @date: 2022/4/12 22:47
     */
    @ApiOperation("查看博客详情--view")
    @GetMapping("/view/{blog_title}")
    @Transactional
    public ResultVO<Blog> viewBlog(@PathVariable("blog_title") String blog_title){
        Blog blog = blogService.getOne(new QueryWrapper<Blog>().lambda().eq(Blog::getBlogTitle, blog_title));
        blog.setBlogViews(blog.getBlogViews()+1);
        boolean updateById = blogService.updateById(blog);
        return new ResultVO<>(blog);
    }
}
