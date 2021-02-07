package com.yang.test.service;

import com.yang.test.po.User;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:41
 * @Description:
 */
public interface IUserService {

    //查询所有
    List<User> findAllUsers();

    //登录
    User login(User user) ;

    //注册
    Boolean register(User user);

    //删除
    Boolean deleteUser(String id);
}
