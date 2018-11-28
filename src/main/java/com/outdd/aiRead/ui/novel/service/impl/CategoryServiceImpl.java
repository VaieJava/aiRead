package com.outdd.aiRead.ui.novel.service.impl;

import com.outdd.aiRead.ui.novel.dao.CategoryMapper;
import com.outdd.aiRead.ui.novel.pojo.Category;
import com.outdd.aiRead.ui.novel.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 * TODO: [NAME]
 * @author VAIE
 * @date: 2018/11/13-11:33
 * @version v1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    public List<Category> findList(Category record){
        return categoryMapper.findList(record);
    }

    public List<Category> getFirstCategory(Category record){
        return categoryMapper.getFirstCategory(record);
    }
}
