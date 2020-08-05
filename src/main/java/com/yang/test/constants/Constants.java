package com.yang.test.constants;

/**
 * @Author: Lyon
 * @Date: 2020/7/30 19:09
 * @Description:
 */
public class Constants {

    /**
     * Descriptions:公用成功失败状态码
     */
    public static final String SUCCESS_CODE = "0000"; //成功状态码
    public static final String FAILED_CODE = "7777"; //失败状态码

    public static final String ADD_SUCCESS_CODE = "00";
    public static final String ADD_SUCCESS_MESSAGE = "新增成功";
    public static final String ADD_FAILED_CODE = "11";
    public static final String ADD_FAILED_MESSAGE = "新增失败";

    public static final String SELECT_SUCCESS_CODE = "00";
    public static final String SELECT_SUCCESS_MESSAGE ="查询成功";
    public static final String SELECT_FAILED_CODE = "22";
    public static final String SELECT_FAILED_MESSAGE ="查询失败";
    public static final String SELECT_REMARK_MESSAGE ="没有数据";

    public static final String UPDATE_SUCCESS_CODE = "00";
    public static final String UPDATE_SUCCESS_MESSAGE ="修改成功";
    public static final String UPDATE_FAILED_CODE = "33";
    public static final String UPDATE_FAILED_MESSAGE ="修改失败";

    public static final String DELETE_SUCCESS_CODE = "00";
    public static final String DELETE_SUCCESS_MESSAGE ="删除成功";
    public static final String DELETE_FAILED_CODE = "44";
    public static final String DELETE_FAILED_MESSAGE ="删除失败";
    public static final String DELETE_FAILED_MESSAGE_NO_DATA ="没有传来数据";
    public static final String DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID ="不存在这条数据！";
    public static final String DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION ="前台没有传过来ID";

    public static final String LOGIN_SUCCESS_CODE="00";
    public static final String LOGIN_SUCCESS_MESSAGE="登陆成功";
    public static final String LOGIN_FAILED_CODE="55";
    public static final String LOGIN_FAILED_MESSAGE_NO_INFO_FROM_FRONT="登陆失败-前台没有传过来需要登录的账户名或者密码";
    public static final String LOGIN_FAILED_MESSAGE_NAME_NULL="登陆失败-账号没有输入，请输入用户名";
    public static final String LOGIN_FAILED_MESSAGE_PASSWORD_NULL="登陆失败-密码没有输入，请输入密码";
    public static final String LOGIN_FAILED_MESSAGE_NAME_PASSWORD_NULL="登陆失败-账号密码不存在，请检查登录名和密码";


    public static final String REGISTER_SUCCESS_CODE="00";
    public static final String REGISTER_SUCCESS_MESSAGE="注册成功";
    public static final String REGISTER_FAILED_CODE="66";
    public static final String REGISTER_FAILED_MESSAGE="注册失败";
    public static final String REGISTER_FAILED_MESSAGE_NO_USERNAME_FROM_FRONT="注册失败-前台没有传过来需要注册的用户名";
    public static final String REGISTER_FAILED_MESSAGE_PASSWORD_NULL="注册失败-前台没有传过来需要注册的密码";
    public static final String REGISTER_FAILED_MESSAGE_USERNAME_REPEAT="注册失败-用户名已被占用";






}
