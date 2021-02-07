package com.yang.test.mapper;

import com.yang.test.po.PrintIncome;
import com.yang.test.po.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> selectRoles();
}
