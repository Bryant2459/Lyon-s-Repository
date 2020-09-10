package com.yang.test.service.impl;

import com.yang.test.mapper.ActionRecordMapper;
import com.yang.test.mapper.UserMapper;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.User;
import com.yang.test.service.IActionRecordService;
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
public class ActionRecordServiceImpl implements IActionRecordService {

    Logger logger= LoggerFactory.getLogger(ActionRecordServiceImpl.class);

    @Autowired(required=true)
    private ActionRecordMapper actionRecordMapper;



    @Override
    public Boolean addActionRecord(ActionRecord actionRecord) {
        Boolean booleanResult= true;
        int registerResult = actionRecordMapper.addActionRecord(actionRecord);
        if(registerResult<=0) {
            booleanResult = false;
            logger.info("当前新增 action record 为：" + actionRecord.getId() + ";新增结果：" + booleanResult);
            return booleanResult;
        }
        logger.info("当前新增 action record 为："+actionRecord.getId()+";新增结果："+booleanResult);
        return booleanResult;
    }


}
