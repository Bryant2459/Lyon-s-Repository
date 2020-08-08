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
    public List<User> findAllUsers();

    //登录
    public User login(User user) ;

    //注册
    public Boolean register(User user);

    //删除
    public Boolean deleteUser(String id);
}
