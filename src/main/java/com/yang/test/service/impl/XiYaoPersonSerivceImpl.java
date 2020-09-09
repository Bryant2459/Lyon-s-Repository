package com.yang.test.service.impl;

import com.yang.test.mapper.PrintIncomeMapper;
import com.yang.test.mapper.XiYaoPersonMapper;
import com.yang.test.po.PrintIncome;
import com.yang.test.po.XiYaoPerson;
import com.yang.test.service.IPrintIncomeService;
import com.yang.test.service.IXiYaoPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/7/28 19:40
 * @Description:
 */
@Service
public class XiYaoPersonSerivceImpl implements IXiYaoPersonService {

    @Autowired(required = true)
    private XiYaoPersonMapper xiYaoPersonMapper;

    //XiYaoPerson xiYaoPerson
    @Override
    public List<XiYaoPerson> selectAllXiYaoPerson() {

        List<XiYaoPerson> recordList = xiYaoPersonMapper.selectAllXiYaoPerson();
        if (CollectionUtils.isEmpty(recordList)) {
            System.out.println("查回来，没有记录");
        }
        System.out.println("selectAll 的结果：" + recordList.size());

        return recordList;
    }


    @Override
    public Boolean updateXiYaoPersonByID(XiYaoPerson xiYaoPerson) {
        Boolean updateResult = true;
        int impactNum = xiYaoPersonMapper.updateXiYaoPersonByID(xiYaoPerson);
        if (impactNum <= 0) {
            updateResult = false;
            return updateResult;
        }
        System.out.println("updateXiYaoPersonByID  --> ：" + xiYaoPerson.getId() + " impact num:" + impactNum);
        return updateResult;
    }

    @Override
    public Boolean addXiYaoPerson(XiYaoPerson xiYaoPerson) {
        Boolean addresult = true;
        int impactNum = xiYaoPersonMapper.addXiYaoPerson(xiYaoPerson);
        if (impactNum <= 0) {
            addresult = false;
            return addresult;
        }
        System.out.println("add XiYaoPerson --> " + xiYaoPerson.getId() + " impact num：" + impactNum);
        return addresult;
    }

    @Override
    public Boolean deleteXiYaoPersonByID(String id) {
        Boolean delResult = true;
        int impactNum = xiYaoPersonMapper.deleteXiYaoPersonByID(id);
        if (impactNum <= 0) {
            delResult = false;
            return delResult;
        }
        System.out.println("del deleteXiYaoPersonByID  --> " + id + " 的结果：" + impactNum);
        return delResult;
    }


}
