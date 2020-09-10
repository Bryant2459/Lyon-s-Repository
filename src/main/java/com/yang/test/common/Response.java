package com.yang.test.common;

import lombok.Data;

/**
 * @Author: Lyon
 * @Date: 2020/7/30 20:52
 * @Description:
 */
@Data
public class Response {
    String message;
    String status;
    String erroCode;
    Object data;
}
