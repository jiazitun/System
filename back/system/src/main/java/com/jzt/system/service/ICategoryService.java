package com.jzt.system.service;

import com.jzt.system.bean.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    void saveOrUpdate(Category category);

    void deleteById(Long id);

    Category findById(Long id);

    void deletebatch(Long[] id);
}
