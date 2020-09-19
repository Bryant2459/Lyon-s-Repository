package com.yang.test.mapper;

import com.yang.test.po.MQDataRecord;
import org.springframework.stereotype.Repository;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:28
 * @Description:
 */
@Repository
public interface MQDataRecordMapper {

    //注册，新增
    int addMQDataRecord(MQDataRecord mqDataRecord);

}
