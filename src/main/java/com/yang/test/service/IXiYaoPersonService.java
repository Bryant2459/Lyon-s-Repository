package com.yang.test.service;

import com.yang.test.po.PrintIncome;
import com.yang.test.po.XiYaoPerson;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:38
 * @Description:
 */
public interface IXiYaoPersonService {

    //查询所有
     public List<XiYaoPerson> selectAllXiYaoPerson();

     //修改
    public Boolean updateXiYaoPersonByID(XiYaoPerson xiYaoPerson);

    //新增
    public Boolean addXiYaoPerson(XiYaoPerson xiYaoPerson);

    //删除

    public Boolean deleteXiYaoPersonByID(String id);

}
