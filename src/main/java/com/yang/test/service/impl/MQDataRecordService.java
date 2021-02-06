package com.yang.test.service.impl;

import com.yang.test.mapper.MQDataRecordMapper;
import com.yang.test.po.MQDataRecord;
import com.yang.test.service.IMQDataRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Lyon
 * @Date: 2020/9/19 15:59
 * @Description:
 */
@Service
public class MQDataRecordService implements IMQDataRecordService {
    Logger logger = LoggerFactory.getLogger(MQDataRecordService.class);

    @Autowired(required = true)
    private MQDataRecordMapper mqDataRecordMapper;

    @Override
    public Boolean addMQDataRecord(MQDataRecord mqDataRecord) {
        Boolean addResult = true;
        int result = mqDataRecordMapper.addMQDataRecord(mqDataRecord);
        if (result <= 0) {
            logger.info("MessageId ："+mqDataRecord.getMessageId()+"：新增 失败");
         return addResult=false;

        }
        logger.info("MessageId ："+mqDataRecord.getMessageId()+"：新增 成功");
        return addResult;
    }
}
