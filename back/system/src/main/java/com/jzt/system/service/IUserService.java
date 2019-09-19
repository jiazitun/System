package com.jzt.system.service;

import com.jzt.system.bean.User;
import com.jzt.system.utils.PageVM;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    void saveOrUpdate(User user);

    void deleteById(Long id);

    User findById(Long id);

    User login(String userName,int password);

    PageVM<User> pageQuery(int page, int pageSize);

    void batchDelete(long[] ids);

}
