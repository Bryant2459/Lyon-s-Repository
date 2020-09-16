package com.yang.test.po;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Lyon
 * @Date: 2020/9/16 15:39
 * @Description:
 */
@Data
public class Image {
    private  String id;
    private  String recordId;
    private  String url;
    private  String firstAddTime;
    private  String imgName;
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        df.format(new Date());
        System.out.println(df.format(new Date()));
    }
}
