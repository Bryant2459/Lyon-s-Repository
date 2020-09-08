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
     public List<LifeRecord> selectAllLifeRecord();

    //根据分类id
    public List<LifeRecord> selectLifeRecordByCategoryID(Integer categoryId);

     //修改
    public Boolean updateLifeRecordByID(LifeRecord lifeRecord);

    //新增
    public Boolean addLifeRecord(LifeRecord lifeRecord);

    //删除

    public Boolean deleteLifeRecordByID(String id);

}
