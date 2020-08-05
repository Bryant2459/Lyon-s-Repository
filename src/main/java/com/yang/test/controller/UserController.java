package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.Constants;
import com.yang.test.po.PrintIncome;
import com.yang.test.po.User;
import com.yang.test.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:45
 * @Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired(required=true)
    private IUserService userService;

    //查询所有
    @RequestMapping("/findAllUser")
    public Response findAll(){
        List<User> listRecord= userService.findAllUsers();
        Response response=new Response();
        if(CollectionUtils.isEmpty(listRecord)){
            response.setStatus(Constants.FAILED_CODE);
            response.setMessage(Constants.SELECT_REMARK_MESSAGE);
            response.setErroCode(Constants.SELECT_FAILED_CODE);
            response.setData(null);
            return response;
        }
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来的结果数量："+listRecord.size());
        return response;
    }
   //登录
   @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(User  user) throws Exception {
       User loginUser=new User();
       Response response=new Response();
       List<User> allUser= userService.findAllUsers();
       if(null == user){
           response.setStatus(Constants.FAILED_CODE);
           response.setErroCode(Constants.LOGIN_FAILED_CODE);
           response.setMessage(Constants.LOGIN_FAILED_MESSAGE_NO_INFO_FROM_FRONT);
           response.setData(null);
           return response;
       }
       if(StringUtils.isEmpty(user.getUserName())){
           response.setStatus(Constants.FAILED_CODE);
           response.setErroCode(Constants.LOGIN_FAILED_CODE);
           response.setMessage(Constants.LOGIN_FAILED_MESSAGE_NAME_NULL);
           response.setData(null);
           return response;
       }
       if(StringUtils.isEmpty(user.getUserPassword())){
           response.setStatus(Constants.FAILED_CODE);
           response.setErroCode(Constants.LOGIN_FAILED_CODE);
           response.setMessage(Constants.LOGIN_FAILED_MESSAGE_PASSWORD_NULL);
           response.setData(null);
           return response;
       }
       loginUser.setUserName(user.getUserName());
       loginUser.setUserPassword(user.getUserPassword());


       User userResult = userService.login(loginUser);

       if(null ==userResult){
           response.setStatus(Constants.FAILED_CODE);
           response.setErroCode(Constants.LOGIN_FAILED_CODE);
           response.setMessage(Constants.LOGIN_FAILED_MESSAGE_NAME_PASSWORD_NULL);
           response.setData(null);
           return response;
       }
       response.setStatus(Constants.SUCCESS_CODE);
       response.setErroCode(Constants.LOGIN_SUCCESS_CODE);
       response.setMessage(Constants.LOGIN_SUCCESS_MESSAGE);
       response.setData(null);
       return response;
   }

    //新增
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Response addRecord(User user){
        Response response=new Response();
        User userBack=new User();
        userBack.setId(UUID.randomUUID().toString());
        if(StringUtils.isNotBlank(user.getRegisterDate()) && StringUtils.isNotEmpty(user.getRegisterDate()) ){
            userBack.setRegisterDate(user.getRegisterDate());
        }else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            userBack.setRegisterDate(df.format(new Date()));
        }
        if(StringUtils.isBlank(user.getUserName()) || StringUtils.isEmpty(user.getUserName()) ){
            response.setData(null);
            response.setMessage(Constants.REGISTER_FAILED_MESSAGE_NO_USERNAME_FROM_FRONT);
            response.setErroCode(Constants.REGISTER_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }else{
            userBack.setUserName(user.getUserName());
        }
        if(StringUtils.isBlank(user.getUserPassword()) || StringUtils.isEmpty(user.getUserPassword()) ){
            response.setData(null);
            response.setMessage(Constants.REGISTER_FAILED_MESSAGE_PASSWORD_NULL);
            response.setErroCode(Constants.REGISTER_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }else{
            userBack.setUserPassword(user.getUserPassword());
        }
       if(StringUtils.isNotBlank(user.getPhone()) && StringUtils.isNotEmpty(user.getPhone()) ){
           userBack.setPhone(user.getPhone());
       }
        if(StringUtils.isNotBlank(user.getAddress()) && StringUtils.isNotEmpty(user.getAddress()) ){
            userBack.setAddress(user.getAddress());
        }
        if(StringUtils.isNotBlank(user.getGender()) && StringUtils.isNotEmpty(user.getGender()) ){
            userBack.setGender(user.getGender());
        }
        if(StringUtils.isNotBlank(user.getIdentityCard()) && StringUtils.isNotEmpty(user.getIdentityCard()) ){
            userBack.setIdentityCard(user.getIdentityCard());
        }
        if(StringUtils.isNotBlank(user.getRealName()) && StringUtils.isNotEmpty(user.getRealName()) ){
            userBack.setRealName(user.getRealName());
        }
        List<User> allUser= userService.findAllUsers();
        for (User tempUser:allUser) {
           if(StringUtils.equals(user.getUserName(),tempUser.getUserName())){
               response.setData(null);
               response.setMessage(Constants.REGISTER_FAILED_MESSAGE_USERNAME_REPEAT);
               logger.info("当前已经占用的用户名："+user.getUserName());
               response.setErroCode(Constants.REGISTER_FAILED_CODE);
               response.setStatus(Constants.FAILED_CODE);
               return response;
           }
        }
        Boolean registerResult = userService.register(userBack);

        if(!registerResult){
            response.setData(null);
            response.setMessage(Constants.REGISTER_FAILED_MESSAGE);
            response.setErroCode(Constants.REGISTER_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }
        response.setData(null);
        response.setMessage(Constants.REGISTER_SUCCESS_MESSAGE);
        response.setErroCode(Constants.REGISTER_SUCCESS_CODE);
        response.setStatus(Constants.REGISTER_SUCCESS_CODE);
        return response;
    }

    //删除
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Response deleteRecord(User  user){
        Response response=new Response();
        Boolean deleteResult=false;
        Boolean confirm=false;
        if(null == user){
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_DATA);
            response.setStatus(Constants.FAILED_CODE);
            response.setData(null);
            return response;
        }else{
           if(StringUtils.isEmpty(user.getId())||StringUtils.isBlank(user.getId())){
           response.setErroCode(Constants.DELETE_FAILED_CODE);
           response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
           response.setStatus(Constants.FAILED_CODE);
           response.setData(null);
           return response;
           }else{
              deleteResult = userService.deleteUser(user.getId());
               if(!deleteResult){
                   response.setErroCode(Constants.DELETE_FAILED_CODE);
                   response.setMessage(Constants.DELETE_FAILED_MESSAGE);
                   response.setStatus(Constants.FAILED_CODE);
                   response.setData(null);
                   return response;
               }
               response.setErroCode(Constants.DELETE_SUCCESS_CODE);
               response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
               response.setStatus(Constants.SUCCESS_CODE);
               response.setData(null);
               return response;
           }
        }
    }
}
