package com.blog.controller;

import com.blog.entity.Category;
import com.blog.pojo.ResultVO;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/7 18:05
 * @Version 1.0
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 功能描述：返回所有的分类列表
     * @param: []
     * @return: com.blog.pojo.ResultVO<java.util.List<com.blog.entity.Category>>
     * @auther: lxl
     * @date: 2022/4/7 18:15
     */
    @GetMapping("/list")
    public ResultVO<List<Category>> getCategoryList(){
        List<Category> categories = categoryService.list();
        return new ResultVO<>(categories);
    }
}
