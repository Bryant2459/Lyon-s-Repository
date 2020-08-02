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
        System.out.println("selectAll 的结果："+recordList.size());
        return recordList;
    }

    @Override
    public Boolean updateRecordByID(PrintIncome printIncome) {
        Boolean updateResult=true;
        int impactNum = printIncomeMapper.updateRecordByID(printIncome);
        if(impactNum<=0){
            updateResult=false;
            return updateResult;
        }
        System.out.println("updateRecordByID  --> ："+printIncome.getId()+" impact num:"+impactNum);
        return updateResult;
    }

    @Override
    public Boolean addRecord(PrintIncome printIncome) {
        Boolean addresult=true;
        int impactNum = printIncomeMapper.addRecord(printIncome);
        if(impactNum<=0){
            addresult=false;
            return addresult;
        }
        System.out.println("add record --> "+printIncome.getId()+" impact num："+impactNum);
        return addresult;
    }

    @Override
    public Boolean delleteRecord(String id) {
        Boolean delResult=true;
        int impactNum=printIncomeMapper.deleteRecordByID(id);
        if(impactNum<=0){
            delResult=false;
            return delResult;
        }
        System.out.println("del record id --> "+id+" 的结果："+impactNum);
        return delResult;
    }


}
