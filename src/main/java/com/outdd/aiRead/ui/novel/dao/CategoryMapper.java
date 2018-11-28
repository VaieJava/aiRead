package com.outdd.aiRead.ui.novel.dao;


import com.outdd.aiRead.ui.novel.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cateId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cateId);

    List<Category> findList(Category record);

    List<Category> getFirstCategory(Category record);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}