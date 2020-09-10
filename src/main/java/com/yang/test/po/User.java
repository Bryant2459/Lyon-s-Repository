package com.yang.test.po;

import lombok.Data;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:25
 * @Description:
 */
@Data
public class User {
    private String id;
    private String userName;
    private String userPassword;
    private String gender;
    private String phone;
    private String address;
    private String realName;
    private String identityCard;
    private  String registerDate;
}
