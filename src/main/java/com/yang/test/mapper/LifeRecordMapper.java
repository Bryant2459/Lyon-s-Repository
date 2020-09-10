package com.yang.test.mapper;

import com.yang.test.po.LifeRecord;
import com.yang.test.po.XiYaoPerson;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:30
 * @Description:
 */
@Repository
public interface LifeRecordMapper {

    //查询
    List<LifeRecord> selectAllLifeRecord();

    //按照ID 修改记录
    int updateLifeRecordByID(LifeRecord lifeRecord);

    //新增
    int addLifeRecord(LifeRecord lifeRecord);

    //删除
    int deleteLifeRecordByID(String id);

    //selectLifeRecordByCategoryID
    List<LifeRecord> selectLifeRecordByCategoryID(Integer categoryId);
}
