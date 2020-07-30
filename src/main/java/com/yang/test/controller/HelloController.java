package com.yang.test.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yang.test.po.PrintIncome;
import com.yang.test.service.impl.PrintIncomeSerivceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    // 获取.yml 文件中值
    @Value("${name}")
    private String name;

    @Autowired(required=true)
    private PrintIncomeSerivceImpl printIncomeService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @RequestMapping("/getName")
    public String name() {
        return name;
    }


    @RequestMapping("/all")
    public  String findAll(){

    List<PrintIncome> listRecord= printIncomeService.findAll();
        System.out.println("查出来的结果数量："+listRecord.size());
        String str= JSON.toJSONString(listRecord);
     //  String str= listRecord.toString();

        return str;
    }


}
