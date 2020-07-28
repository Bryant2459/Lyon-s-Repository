package com.yang.test.service.impl;

import com.yang.test.mapper.PrintIncomeMapper;
import com.yang.test.po.PrintIncome;
import com.yang.test.service.IPrintIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:40
 * @Description:
 */
@Service
public class PrintIncomeSerivceImpl implements IPrintIncomeService {

    @Autowired(required=true)
    private PrintIncomeMapper printIncomeMapper;

    @Override
    public List<PrintIncome> findAll() {

        List<PrintIncome> recordList= printIncomeMapper.selectAllRecords();

      if(CollectionUtils.isEmpty(recordList)){
          System.out.println("查回来，没有记录");
      }

        return recordList;
    }
}
