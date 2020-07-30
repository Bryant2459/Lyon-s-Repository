package com.yang.test.mapper;

import com.yang.test.po.PrintIncome;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:30
 * @Description:
 */
@Repository
public interface PrintIncomeMapper {

    //查询所有
   // @Select("select * from printincome")
    List<PrintIncome> selectAllRecords();


}
