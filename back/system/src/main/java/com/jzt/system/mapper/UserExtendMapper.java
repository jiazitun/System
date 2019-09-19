package com.jzt.system.mapper;

import com.jzt.system.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by  on 2019/9/18 15:45
 */
public interface UserExtendMapper {
    List<User> pageQuery(
            @Param("page") int page,
            @Param("pageSize") int pageSize);
    long count();
}
