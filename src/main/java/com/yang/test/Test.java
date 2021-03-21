package com.yang.test;

/**
 * @Author: Lyon
 * @Date: 2020/9/19 20:47
 * @Description:
 */
public class Test {
    public static void main(String[] args) {

        Long a = 1600519766322L;
        Long b = 1600519766332L;
        Double c = 10.0;
        System.out.println("时间差:" + (b - a) / 1000);
        System.out.println("c/1000:" + c / 1000);
    }
}
