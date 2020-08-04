package com.yang.test.service.impl;

import com.yang.test.mapper.UserMapper;
import com.yang.test.po.User;
import com.yang.test.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:42
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required=true)
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        List<User> allUsers = userMapper.findAllUsers();

        if(CollectionUtils.isEmpty(allUsers)){
            System.out.println("查回来，没有记录");
        }
        System.out.println("selectAll 的结果："+allUsers.size());
        return allUsers;


    }

    @Override
    public User login(User user) {

        User loginUser = userMapper.login(user);

        System.out.println("当前请求登录的用户为："+user.getId());

        return loginUser;
    }

    @Override
    public Boolean register(User user) {
        Boolean booleanResult= true;
        int registerResult = userMapper.register(user);
        if(registerResult<=0){
            booleanResult=false;
            System.out.println("当前注册用户为："+user.getId()+";注册结果："+booleanResult);
            return  booleanResult;
        }
        System.out.println("当前注册用户为："+user.getId()+";注册结果："+booleanResult);
        return booleanResult;
    }
}
