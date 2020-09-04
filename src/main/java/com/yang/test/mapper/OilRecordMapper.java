package com.yang.test.mapper;

import com.yang.test.po.OilRecord;
import com.yang.test.po.PrintIncome;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:30
 * @Description:
 */
@Repository
public interface OilRecordMapper {

    //查询所有
    List<OilRecord> selectAllOilRecords();

    //按照ID 修改记录
    int updateOilRecordByID(OilRecord oilRecord);

    //新增
    int addOilRecord(OilRecord oilRecord);

    //删除
    int deleteOilRecordByID(String id);
}
