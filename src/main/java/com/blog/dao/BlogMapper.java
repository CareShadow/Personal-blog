package com.blog.dao;

import com.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.Pagination;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxl
 * @since 2022-04-06
 */
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> getBlog(Pagination pagination);
    Integer getTotal();
}
