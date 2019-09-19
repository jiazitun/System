package com.jzt.system.service.impl;

import com.jzt.system.bean.User;
import com.jzt.system.bean.UserExample;
import com.jzt.system.mapper.UserExtendMapper;
import com.jzt.system.mapper.UserMapper;
import com.jzt.system.service.IUserService;
import com.jzt.system.utils.PageVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserExtendMapper userExtendMapper;

    @Override
    public PageVM<User> pageQuery(int page, int pageSize) {
        List<User> list = userExtendMapper.pageQuery(page,pageSize);
        long total = userExtendMapper.count();
        PageVM<User> pageVM = new PageVM<>();
        pageVM.setPage(page);
        pageVM.setPageSize(pageSize);
        pageVM.setList(list);
        pageVM.setTotal(total);
        return pageVM;
    }

    @Override
    public List<User> findAll() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(User user) {
        if(user.getId() == null){
            userMapper.insert(user);
        }else {
            userMapper.updateByPrimaryKey(user);
        }
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User login(String userName,int userPwd) {
        return userMapper.selectBynameAndpassword(userName,userPwd);
    }

    @Override
    public void batchDelete(long[] ids) {
        for(long id : ids){
            userMapper.deleteByPrimaryKey(id);
        }
    }

}

