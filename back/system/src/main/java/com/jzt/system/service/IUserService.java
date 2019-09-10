package com.jzt.system.service;

import com.jzt.system.bean.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    void saveOrUpdate(User user);

    void deleteById(Long id);



}
