package com.yang.test.service;

import com.yang.test.po.LifeRecord;
import com.yang.test.po.XiYaoPerson;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:38
 * @Description:
 */
public interface ILifeRecordService {

    //查询所有 selectLifeRecordByCategoryID
    List<LifeRecord> selectAllLifeRecord();

    //根据分类id
    List<LifeRecord> selectLifeRecordByCategoryID(Integer categoryId);

    //修改
    Boolean updateLifeRecordByID(LifeRecord lifeRecord);

    //新增
    Boolean addLifeRecord(LifeRecord lifeRecord);

    //删除

    Boolean deleteLifeRecordByID(String id);

}
