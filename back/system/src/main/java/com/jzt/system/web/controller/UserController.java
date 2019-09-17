package com.jzt.system.web.controller;

import com.jzt.system.bean.User;
import com.jzt.system.service.IUserService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("findAll")
    private Message findAll(){
        List<User> list = userService.findAll();
        return MessageUtils.success(list);
    }

    @PostMapping("saveOrUpdate")
    private Message saveOrUpdate(User user){
        userService.saveOrUpdate(user);
        return MessageUtils.success("操作成功");
    }

    @GetMapping("deleteById")
    private Message deleteById(Long id){
        userService.deleteById(id);
        return MessageUtils.success("删除成功");
    }
    @GetMapping("findById")
    private Message findById(Long id){
        User user=userService.findById(id);
        return MessageUtils.success(user);
    }

}
