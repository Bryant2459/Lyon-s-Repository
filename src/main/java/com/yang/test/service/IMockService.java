package com.yang.test.service;

import com.yang.test.po.MockData;
import com.yang.test.po.User;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:41
 * @Description:
 */
public interface IMockService {

    //查询所有
    List<MockData> findAllMockDatas();


}
