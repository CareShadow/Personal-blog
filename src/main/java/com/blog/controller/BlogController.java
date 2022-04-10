package com.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.dao.BlogMapper;
import com.blog.entity.Blog;
import com.blog.model.ReqBlog;
import com.blog.model.RespBlog;
import com.blog.pojo.Pagination;
import com.blog.pojo.ResultVO;
import com.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "博客接口类--Controller")
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
    @PostMapping("/list")
    @ApiOperation("获取博客列表--search")
    @Transactional
    public ResultVO<Map<String,Object>> getAllBlog(int page,int count,String search){
        // 算出起始位置(page-1)*count;
        Pagination pagination = new Pagination((page-1)*count,count,search);
        List<RespBlog> blogList = blogMapper.getBlog(pagination);
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
    @ApiOperation("修改博客状态--update")
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
    @ApiOperation("增加博客--add")
    public ResultVO<String> addBlog(@RequestBody ReqBlog reqBlog){
        // 判断文章标题是否存在
        Blog blog = blogService.getOne(new QueryWrapper<Blog>().lambda().eq(Blog::getBlogTitle, reqBlog.getBlogTitle()));
        Blog saveBlog = new Blog().setBlogTitle(reqBlog.getBlogTitle())
                .setBlogStatus(reqBlog.getBlogStatus())
                .setBlogCategoryId(reqBlog.getBlogCategoryId())
                .setBlogContent(reqBlog.getBlogContent())
                .setBlogPreface(reqBlog.getBlogPreface())
                .setBlogViews(0)
                .setCreateTime(new Date())
                .setUpdateTime(new Date());
        boolean save =false,updateById=false;
        if(blog==null) {
             save = blogService.save(saveBlog);
        }else{
             updateById = blogService.updateById(saveBlog);
        }

        String msg = save&&updateById?"添加成功":"添加失败";
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
    @ApiOperation("删除博客--delete")
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
    @ApiOperation("查看博客详情--view")
    @GetMapping("/view/{blog_title}")
    public ResultVO<Blog> viewBlog(@PathVariable("blog_title") String blog_title){
        Blog blog = blogService.getOne(new QueryWrapper<Blog>().lambda().eq(Blog::getBlogTitle, blog_title));
        return new ResultVO<>(blog);
    }
    /**
     * 功能描述：用于测试拦截器是否正常
     * @param: []
     * @return: com.blog.pojo.ResultVO<java.lang.String>
     * @auther: lxl
     * @date: 2022/4/8 18:45
     */
    @GetMapping("/text")
    @ApiOperation("测试接口--text")
    public ResultVO<String> api(){
        return new ResultVO<>("测试");
    }
}
