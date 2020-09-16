package com.yang.test.service.impl;

import com.yang.test.mapper.XiYaoPersonMapper;
import com.yang.test.po.XiYaoPerson;
import com.yang.test.service.IXiYaoPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class XiYaoPersonSerivceImpl implements IXiYaoPersonService {

    Logger logger = LoggerFactory.getLogger(XiYaoPersonSerivceImpl.class);

    @Autowired(required = true)
    private XiYaoPersonMapper xiYaoPersonMapper;

    //XiYaoPerson xiYaoPerson
    @Override
    public List<XiYaoPerson> selectAllXiYaoPerson() {

        List<XiYaoPerson> recordList = xiYaoPersonMapper.selectAllXiYaoPerson();
        if (CollectionUtils.isEmpty(recordList)) {
            logger.info("select All XiYao Person 查回来，没有记录");
        }
        logger.info("selectAll 的结果：" + recordList.size());
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
        logger.info("updateXiYaoPersonByID  --> ：" + xiYaoPerson.getId() + " impact num:" + impactNum);
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
        logger.info("add XiYaoPerson --> " + xiYaoPerson.getId() + " impact num：" + impactNum);
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
        logger.info("del deleteXiYaoPersonByID  --> " + id + " 的结果：" + impactNum);
        return delResult;
    }


}
