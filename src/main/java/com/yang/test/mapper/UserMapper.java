package com.yang.test.mapper;

import com.yang.test.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:28
 * @Description:
 */
@Repository
public interface UserMapper {
    //查所有用户
    List<User> findAllUsers();

    //登录
    User login(User user);

    //注册，新增
    int register(User user);

    //删除
    int deleteUser(String id);
}
