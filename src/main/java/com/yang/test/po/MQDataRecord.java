package com.yang.test.po;

import lombok.Data;

/**
 * @Author: Lyon
 * @Date: 2020/9/19 15:46
 * @Description:
 */
@Data
public class MQDataRecord {
    private String id;
    private String messageId;
    private String createTime;
    private String messageData;
    private String currenTime;
    private String identity;
}
