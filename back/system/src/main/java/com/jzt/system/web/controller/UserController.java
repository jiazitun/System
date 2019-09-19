package com.jzt.system.web.controller;

import com.jzt.system.bean.User;
import com.jzt.system.service.IUserService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import com.jzt.system.utils.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("pageQuery")
    public Message pageQuery(int page,int pageSize){
        PageVM<User> pageVM = userService.pageQuery(page,pageSize);
        return MessageUtils.success(pageVM);
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
    @PostMapping("login")
    private Message login(@RequestParam("userName")String userName, @RequestParam("userPwd")String password){
        int userPwd = Integer.parseInt(password);
        User login = userService.login(userName, userPwd);
        if(login != null) {
            return MessageUtils.success("登陆成功",login);
        }else {
            return MessageUtils.success("登陆失败");
        }
    }
    @PostMapping("batchDelete")
    public Message batchDelete(@RequestBody long[] ids){
        userService.batchDelete(ids);
        return MessageUtils.success("删除成功！");
    }
}


