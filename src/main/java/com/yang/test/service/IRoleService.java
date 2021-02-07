package com.yang.test.service;

import com.yang.test.po.Role;


import java.util.List;

public interface IRoleService {
    //查询所有
    List<Role> selectRoles();
}
