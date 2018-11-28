package com.outdd.aiRead.ui.novel.service;


import com.outdd.aiRead.ui.novel.pojo.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findList(Category record);

    public List<Category> getFirstCategory(Category record);
}
