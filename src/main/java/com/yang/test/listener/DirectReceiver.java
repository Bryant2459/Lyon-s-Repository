package com.yang.test.listener;

import com.alibaba.fastjson.JSON;
import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.MQDataRecord;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.IMQDataRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Lyon
 * @Date: 2020/9/18 17:26
 * @Description:
 */

@Component
@RabbitListener(queues = "Lyon Direct Queue")//监听的队列名称 DirectQueue
public class DirectReceiver {
    Logger logger = LoggerFactory.getLogger(DirectReceiver.class);

    @Autowired(required = true)
    private IActionRecordService actionRecordService;

    @Autowired(required = true)
    private IMQDataRecordService mqDataRecordService;

    @RabbitHandler
    public void process(Map message) {
        logger.info("DirectReceiver消费者收到消息  : " + message.toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String messageId = (String) message.get("messageId");
        String createTime = (String) message.get("createTime");
        String messageData = (String) message.get("messageData");
        Response response = JSON.parseObject(messageData, Response.class);
        logger.info("从接到的消息中，拿到数据，createTime  : " + createTime);
        logger.info("从接到的消息中，拿到数据，messageData --> 转成对象  : " + response);
        logger.info("从接到的消息中，拿到数据，转成对象字符串  : " + response.toString());
        //action record save
        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_MESSAGE_CONSUMER);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator("Lyon");
        actionRecord.setService(AcitonConstants.SERVICE_MQ);
        actionRecord.setRecordId(messageId);
        actionRecordService.addActionRecord(actionRecord);
        MQDataRecord mqDataRecord = new MQDataRecord();
        mqDataRecord.setId(UUID.randomUUID().toString());
        mqDataRecord.setCreateTime(createTime);
        mqDataRecord.setCurrenTime(df.format(new Date()));
        mqDataRecord.setMessageData(messageData);
        mqDataRecord.setMessageId(messageId);
        mqDataRecord.setIdentity(AcitonConstants.MQ_IDENTITY_CONSUMER);
        Boolean addMQResult = mqDataRecordService.addMQDataRecord(mqDataRecord);
        if (addMQResult) {
            logger.info("add mq data successed");
        } else {
            logger.info("add mq data failed");
        }
    }
}
