package com.yang.test.service.impl;

import com.yang.test.mapper.PrintIncomeMapper;
import com.yang.test.mapper.RoleMapper;
import com.yang.test.po.Role;
import com.yang.test.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/7
 * Time: 19:43
 */
@Service
public class RoleServiceImpl implements IRoleService {
    Logger logger= LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired(required = true)
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectRoles() {
        logger.info("selectRoles :"+roleMapper.selectRoles().toString());
        return roleMapper.selectRoles();
    }
}
