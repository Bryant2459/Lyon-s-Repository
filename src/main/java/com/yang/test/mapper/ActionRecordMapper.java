package com.yang.test.mapper;

import com.yang.test.po.ActionRecord;
import com.yang.test.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:28
 * @Description:
 */
@Repository
public interface ActionRecordMapper {

    int addActionRecord(ActionRecord actionRecord);


}
