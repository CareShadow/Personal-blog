package com.blog.controller;

import com.blog.entity.Category;
import com.blog.pojo.ResultVO;
import com.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/7 18:05
 * @Version 1.0
 **/
@RestController
@Api(tags = "文章分类接口类--Controller")
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
    @ApiOperation("文章分类接口--select")
    @GetMapping("/list")
    public ResultVO<List<Category>> getCategoryList(){
        List<Category> categories = categoryService.list();
        return new ResultVO<>(categories);
    }
    @ApiOperation("增加文章分类接口--insert")
    @PostMapping("/insert")
    public ResultVO<String> insertCategory(String categoryName){
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryRank(0);
        category.setCategoryStatus(0);
        category.setCreateTime(new Date());
        boolean save = categoryService.save(category);
        String msg =  save?"增加成功":"增加失败";
        return new ResultVO<>(msg);
    }
    @ApiOperation("修改文章分类状态")
    @GetMapping("/alter/{category_id}/{category_status}")
    public ResultVO<String> alterCategory(@PathVariable("category_id") Integer category_id,
                                          @PathVariable("category_status") Integer category_status){
        Category category = new Category();
        category.setCategoryId(category_id).setCategoryStatus(category_status);
        boolean updateById = categoryService.updateById(category);
        String msg = updateById?"修改成功":"修改失败";
        return new ResultVO<>(msg);
    }
    @ApiOperation("删除文章状态")
    @GetMapping("/delete/{category_id}")
    public ResultVO<String> deleteCategory(@PathVariable("category_id") Integer category_id){
        boolean removeById = categoryService.removeById(category_id);
        String msg = removeById?"删除成功":"删除失败";
        return new ResultVO<>(msg);
    }
}
