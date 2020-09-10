package com.yang.test.service.impl;

import com.yang.test.mapper.MockMapper;
import com.yang.test.mapper.UserMapper;
import com.yang.test.po.MockData;
import com.yang.test.po.User;
import com.yang.test.service.IMockService;
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
public class MockServiceImpl implements IMockService {

    Logger logger = LoggerFactory.getLogger(MockServiceImpl.class);

    @Autowired(required = true)
    private MockMapper mockMapper;

    @Override
    public List<MockData> findAllMockDatas() {
        List<MockData> allMockDatas = mockMapper.findAllMockDatas();

        if (CollectionUtils.isEmpty(allMockDatas)) {
            logger.info("查回来，没有记录");
        }

        logger.info("selectAll 的结果：" + allMockDatas.size());
        return allMockDatas;


    }

}
