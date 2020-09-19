package com.yang.test.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:25
 * @Description:
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1524312998674769761L;
    private String id;
    private String role;
    private String userName;
    private String userPassword;
    private String gender;
    private String phone;
    private String address;
    private String realName;
    private String identityCard;
    private  String registerDate;

    public User() {
        super();
    }
}
