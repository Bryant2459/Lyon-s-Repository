package com.yang.test.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:25
 * @Description:
 */
@ApiModel
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1524312998674769761L;
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "用户角色")
    private String role;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String userPassword;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "身份证")
    private String identityCard;
    @ApiModelProperty(value = "注册日期")
    private String registerDate;


    public User() {
        super();
    }
}
