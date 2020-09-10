package com.yang.test.po;

import lombok.Data;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:25
 * @Description:
 */
@Data
public class ActionRecord {
    private String id;
    private String action;
    private String actionTime;
    private String operator;
    private String service;
    private String recordId;
}
