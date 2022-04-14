package com.blog.dao;

import com.blog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxl
 * @since 2022-04-07
 */
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> getHotCategory();
}
