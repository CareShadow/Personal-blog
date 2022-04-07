package com.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.dao.BlogMapper;
import com.blog.entity.Blog;
import com.blog.model.ReqBlog;
import com.blog.pojo.Pagination;
import com.blog.pojo.ResultVO;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    private BlogService blogService;
    /**
     * 功能描述：获取博客列表的信息 + 搜索值
     * @param: [page, count]
     * @return: com.blog.pojo.ResultVO<java.util.List<com.blog.entity.Blog>
     * @auther: lxl
     * @date: 2022/4/6 20:49
     */
    @GetMapping("/list/{page}/{count}/{search}")
    @Transactional
    public ResultVO<Map<String,Object>> getAllBlog(@PathVariable("page") int page, @PathVariable("count") int count,@PathVariable("search") String search){
        // 算出起始位置(page-1)*count;
        Pagination pagination = new Pagination((page-1)*count,count,search);
        List<Blog> blogList = blogMapper.getBlog(pagination);
        Integer Count = blogMapper.getTotal(search);
        Integer Pages = Count%page==0?Count/page:Count/page+1;
        Map<String,Object> map = new HashMap<>();
        map.put("blogList",blogList);
        map.put("total",Count);
        map.put("size",count);
        map.put("current",page);
        map.put("pages",Pages);
        return new ResultVO<>(map);
    }
    /**
     * 功能描述：修改博客状态
     * @param: [blog_title, blog_status]
     * @return: com.blog.pojo.ResultVO<java.lang.String>
     * @auther: lxl
     * @date: 2022/4/7 9:27
     */
    @GetMapping("/alter/{blog_title}/{blog_status}")
    @Transactional
    public ResultVO<String> alterBlogStatus(@PathVariable("blog_title") String blog_title,@PathVariable("blog_status") int blog_status){
        // blog_status判断
        Blog blog = new Blog();
        blog.setBlogTitle(blog_title);
        blog.setBlogStatus(blog_status);
        boolean updateById = blogService.updateById(blog);
        String msg = updateById?"修改成功":"修改失败";
        return new ResultVO<>(msg);
    }
    /**
     * 功能描述：添加博客
     * @param: [reqBlog]
     * @return: com.blog.pojo.ResultVO<java.lang.String>
     * @auther: lxl
     * @date: 2022/4/7 10:32
     */
    @PostMapping("/addBlog")
    public ResultVO<String> addBlog(@RequestBody ReqBlog reqBlog){
        // 判断文章标题是否存在
        Blog blog = blogService.getOne(new QueryWrapper<Blog>().lambda().eq(Blog::getBlogTitle, reqBlog.getBlogTitle()));
        if(blog==null) return new ResultVO<>("文章标题已存在");
        Blog saveBlog = new Blog().setBlogTitle(reqBlog.getBlogTitle())
                .setBlogStatus(reqBlog.getBlogStatus())
                .setBlogCategoryId(reqBlog.getBlogCategoryId())
                .setBlogContent(reqBlog.getBlogContent())
                .setBlogPreface(reqBlog.getBlogPreface())
                .setBlogViews(0)
                .setCreateTime(new Date())
                .setUpdateTime(new Date());
        boolean save = blogService.save(saveBlog);
        String msg = save?"添加成功":"添加失败";
        return new ResultVO<>(msg);
    }
    /** 删除博客
     * 功能描述：
     * @param: [blog_title]
     * @return: com.blog.pojo.ResultVO<java.lang.String>
     * @auther: lxl
     * @date: 2022/4/7 10:37
     */
    @GetMapping("delete/{blog_title}")
    public ResultVO<String> deleteBlog(@PathVariable("blog_title") String blog_title) {
        boolean removeById = blogService.removeById(blog_title);
        String msg = removeById ? "删除成功" : "删除失败";
        return new ResultVO<>(msg);
    }
    /**
     * 功能描述：查看博客详情
     * @param: [blog_title]
     * @return: com.blog.pojo.ResultVO<com.blog.entity.Blog>
     * @auther: lxl
     * @date: 2022/4/7 17:29
     */
    @GetMapping("/view/{blog_title}")
    public ResultVO<Blog> viewBlog(@PathVariable("blog_title") String blog_title){
        Blog blog = blogService.getOne(new QueryWrapper<Blog>().lambda().eq(Blog::getBlogTitle, blog_title));
        return new ResultVO<>(blog);
    }
}
