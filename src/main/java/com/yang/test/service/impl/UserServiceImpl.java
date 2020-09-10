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

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = true)
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        List<User> allUsers = userMapper.findAllUsers();

        if (CollectionUtils.isEmpty(allUsers)) {
            logger.info("find All Users 查回来，没有记录");
        }

        logger.info(" select All 的结果：" + allUsers.size());
        return allUsers;


    }

    @Override
    public User login(User user) {
        User loginUser = new User();
        loginUser = userMapper.login(user);

        if (null != loginUser) {
            return loginUser;
        }
        logger.info("当前请求登录的用户为：" + user.getUserName());
        return loginUser;
    }

    @Override
    public Boolean register(User user) {
        Boolean booleanResult = true;
        int registerResult = userMapper.register(user);
        if (registerResult <= 0) {
            booleanResult = false;
            logger.info("当前注册用户为：" + user.getId() + ";注册结果：" + booleanResult);
            return booleanResult;
        }
        logger.info("当前注册用户为：" + user.getId() + ";注册结果：" + booleanResult);
        return booleanResult;
    }

    @Override
    public Boolean deleteUser(String id) {
        Boolean booleanResult = true;

        int deleteResult = userMapper.deleteUser(id);
        if (deleteResult <= 0) {
            booleanResult = false;
            logger.info("删除用户信息失败：" + id);
            return booleanResult;
        }
        logger.info("删除用户信息成功：" + id);
        return booleanResult;
    }
}
