package com.jzt.system.web.controller;

import com.jzt.system.bean.Category;
import com.jzt.system.service.ICategoryService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/category")

public class CategoryController{

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("findAll")
    private Message findAll(){
        List<Category> list=categoryService.findAll();
        return MessageUtils.success(list);
    }
    @GetMapping("findById")
    private Message findById(Long id){
        Category category=categoryService.findById(id);
        return MessageUtils.success(category);
    }

    @PostMapping("saveOrUpdate")
    private Message saveOrUpdate(Category category){
        categoryService.saveOrUpdate(category);
        return MessageUtils.success("操作成功");
    }

    @GetMapping("delete")
    private Message delete(Long id){
        categoryService.deleteById(id);
        return MessageUtils.success("操作成功");
    }

    @PostMapping("deletebatch")
    private Message deletebatch(@RequestBody Long[] id){
        System.out.println(Arrays.toString(id));
        categoryService.deletebatch(id);
        return MessageUtils.success("操作成功");
        }

    }

