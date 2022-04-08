package com.blog.dao;

import com.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxl
 * @since 2022-04-07
 */
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> getBlog(Pagination pagination);
    Integer getTotal(@Param("search") String search);

}
