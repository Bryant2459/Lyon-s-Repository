package com.yang.test.service;

import com.yang.test.po.PrintIncome;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:38
 * @Description:
 */
public interface IPrintIncomeService {

    //查询所有
     public List<PrintIncome> findAll();

     //修改
    public Boolean updateRecordByID(PrintIncome printIncome);

    //新增
    public Boolean addRecord(PrintIncome printIncome);

    //删除

    public Boolean delleteRecord(String id);

}
