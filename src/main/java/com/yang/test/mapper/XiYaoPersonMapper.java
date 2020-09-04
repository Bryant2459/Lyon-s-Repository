package com.yang.test.mapper;

import com.yang.test.po.PrintIncome;
import com.yang.test.po.XiYaoPerson;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:30
 * @Description:
 */
@Repository
public interface XiYaoPersonMapper {

    //查询
    List<XiYaoPerson> selectAllXiYaoPerson();

    //按照ID 修改记录
    int updateXiYaoPersonByID(XiYaoPerson xiYaoPerson);

    //新增
    int addXiYaoPerson(XiYaoPerson xiYaoPerson);

    //删除
    int deleteXiYaoPersonByID(String id);
}
