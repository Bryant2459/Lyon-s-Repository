package com.yang.test.constants;

/**
 * @Author: Lyon
 * @Date: 2020/7/30 19:09
 * @Description:
 */
public interface Constants {

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
    public static final String SELECT_SUCCESS_MESSAGE = "查询成功";
    public static final String SELECT_FAILED_CODE = "22";
    public static final String SELECT_FAILED_MESSAGE = "查询失败";
    public static final String SELECT_REMARK_MESSAGE = "没有数据";

    public static final String UPDATE_SUCCESS_CODE = "00";
    public static final String UPDATE_SUCCESS_MESSAGE = "修改成功";
    public static final String UPDATE_FAILED_CODE = "33";
    public static final String UPDATE_FAILED_MESSAGE = "修改失败";

    public static final String DELETE_SUCCESS_CODE = "00";
    public static final String DELETE_SUCCESS_MESSAGE = "删除成功";
    public static final String DELETE_FAILED_CODE = "44";
    public static final String DELETE_FAILED_MESSAGE = "删除失败";
    public static final String DELETE_FAILED_MESSAGE_NO_DATA = "没有传来数据";
    public static final String DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID = "不存在这条数据！";
    public static final String DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION = "前台没有传过来ID";

    public static final String LOGIN_SUCCESS_CODE = "00";
    public static final String LOGIN_SUCCESS_MESSAGE = "登陆成功";
    public static final String LOGIN_FAILED_CODE = "55";
    public static final String LOGIN_FAILED_MESSAGE_NO_INFO_FROM_FRONT = "登陆失败-前台没有传过来需要登录的账户名或者密码";
    public static final String LOGIN_FAILED_MESSAGE_NAME_NULL = "登陆失败-账号没有输入，请输入用户名";
    public static final String LOGIN_FAILED_MESSAGE_PASSWORD_NULL = "登陆失败-密码没有输入，请输入密码";
    public static final String LOGIN_FAILED_MESSAGE_NAME_PASSWORD_NULL = "登陆失败-账号密码不存在，请检查登录名和密码";


    public static final String REGISTER_SUCCESS_CODE = "00";
    public static final String REGISTER_SUCCESS_MESSAGE = "注册成功";
    public static final String REGISTER_FAILED_CODE = "66";
    public static final String REGISTER_FAILED_MESSAGE = "注册失败";
    public static final String REGISTER_FAILED_MESSAGE_NO_USERNAME_FROM_FRONT = "注册失败-前台没有传过来需要注册的用户名";
    public static final String REGISTER_FAILED_MESSAGE_PASSWORD_NULL = "注册失败-前台没有传过来需要注册的密码";
    public static final String REGISTER_FAILED_MESSAGE_USERNAME_REPEAT = "注册失败-用户名已被占用";

    public static final String UPLOAD_SUCCESS_CODE = "00";
    public static final String UPLOAD_SUCCESS_MESSAGE = "上传成功";
    public static final String UPLOAD_FAILED_CODE = "77";
    public static final String UPLOAD_FAILED_MESSAGE = "上传失败";



    public static final int LIFE_RECORD_SUPERMARKET = 1;
    public static final int LIFE_RECORD_SALARY = 2;
    public static final int LIFE_RECORD_ABOUT_CAR = 3;
    public static final int LIFE_RECORD_PRINT = 4;
    public static final int LIFE_RECORD_CALSS = 5;
    public static final int LIFE_RECORD_BACK_HOME = 6;
    public static final int LIFE_RECORD_OTHER = 7;
    public static final int LIFE_RECORD_WATER = 8;
    public static final int LIFE_RECORD_ELECTRICITY = 9;
    public static final int LIFE_RECORD_MEDICAL = 10;
    public static final int LIFE_RECORD_INSURANCE = 11;

    public static final String LIFE_RECORD_SUPERMARKET_CH = "超市";
    public static final String LIFE_RECORD_SALARY_CH = "工资";
    public static final String LIFE_RECORD_ABOUT_CAR_CH = "车辆";
    public static final String LIFE_RECORD_PRINT_CH = "打印";
    public static final String LIFE_RECORD_CALSS_CH = "上课";
    public static final String LIFE_RECORD_BACK_HOME_CH = "回家";
    public static final String LIFE_RECORD_OTHER_CH = "其他";
    public static final String LIFE_RECORD_WATER_CH = "水费";
    public static final String LIFE_RECORD_ELECTRICITY_CH = "电费";
    public static final String LIFE_RECORD_MEDICAL_CH = "医药费";
    public static final String LIFE_RECORD_INSURANCE_CH = "保险";

    public static final String LIFE_RECORD_PERSON_LYON = "1";
    public static final String LIFE_RECORD_PERSON_YUYING = "2";
    public static final String LIFE_RECORD_ABOUT_OTHER = "3";

    public static final String LIFE_RECORD_PERSON_LYON_CH = "李阳";
    public static final String LIFE_RECORD_PERSON_YUYING_CH = "党玉莹";
    public static final String LIFE_RECORD_ABOUT_OTHER_CH = "其他";




}
