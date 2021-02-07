package com.yang.test.service;

import com.yang.test.po.OilRecord;
import com.yang.test.po.PrintIncome;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:38
 * @Description:
 */
public interface IOilRecordService {

    //查询所有
    List<OilRecord> selectAllOilRecords();

     //修改
    Boolean updateOilRecordByID(OilRecord oilRecord);

    //新增
    Boolean addOilRecord(OilRecord oilRecord);

    //删除

    Boolean deleteOilRecordByID(String id);

}
