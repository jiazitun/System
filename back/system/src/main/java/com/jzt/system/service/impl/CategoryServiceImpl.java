package com.jzt.system.service.impl;

import com.jzt.system.bean.Category;
import com.jzt.system.bean.CategoryExample;
import com.jzt.system.mapper.CategoryMapper;
import com.jzt.system.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        CategoryExample example = new CategoryExample();
        return categoryMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(Category category) {
        if(category.getId()!=null){
            categoryMapper.updateByPrimaryKey(category);

        } else{
            categoryMapper.insert(category);
        }
    }

    @Override
    public void deleteById(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deletebatch(Long[] id) {
        categoryMapper.deleteByPrimaryKeybatch(id);
    }


}
