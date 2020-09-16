package com.yang.test.service.impl;

import com.yang.test.mapper.LifeRecordMapper;
import com.yang.test.po.LifeRecord;
import com.yang.test.service.ILifeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Date: 2020/9/7 12:09
 * @Description:
 */
@Service
public class LifeRecordSerivceImpl implements ILifeRecordService {
    Logger logger = LoggerFactory.getLogger(LifeRecordSerivceImpl.class);

    @Autowired(required = true)
    private LifeRecordMapper lifeRecordMapper;

    //LifeRecord lifeRecord
    @Override
    public List<LifeRecord> selectAllLifeRecord() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<LifeRecord> recordList = lifeRecordMapper.selectAllLifeRecord();
        if (CollectionUtils.isEmpty(recordList)) {
            logger.info("select All Life Record 查回来，没有记录");
        }
        Collections.sort(recordList,
                new Comparator<LifeRecord>() {
                    @Override
                    public int compare(LifeRecord o1, LifeRecord o2) {
                        try {
                            Date parse1 = formatter.parse(o1.getProduceRecordDate());
                            Date parse2 = formatter.parse(o2.getProduceRecordDate());
                            if (parse1.getTime() < parse2.getTime()) {
                                return 1;
                            } else if (parse1.getTime() > parse2.getTime()) {
                                return -1;
                            } else {
                                return 0;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });

        logger.info("selectAll Life Records 的结果：" + recordList.size());
        return recordList;
    }

    @Override
    public List<LifeRecord> selectLifeRecordByCategoryID(Integer categoryId) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<LifeRecord> recordList = lifeRecordMapper.selectLifeRecordByCategoryID(categoryId);
        if (CollectionUtils.isEmpty(recordList)) {
            logger.info("selectLifeRecordByCategoryID : 查回来，没有记录");
        }
        Collections.sort(recordList,
                new Comparator<LifeRecord>() {
                    @Override
                    public int compare(LifeRecord o1, LifeRecord o2) {
                        try {
                            Date parse1 = formatter.parse(o1.getProduceRecordDate());
                            Date parse2 = formatter.parse(o2.getProduceRecordDate());
                            if (parse1.getTime() < parse2.getTime()) {
                                return 1;
                            } else if (parse1.getTime() > parse2.getTime()) {
                                return -1;
                            } else {
                                return 0;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });
        logger.info("selectLifeRecordByCategoryID：" + categoryId + " 的结果：" + recordList.size());
        return recordList;
    }


    @Override
    public Boolean updateLifeRecordByID(LifeRecord lifeRecord) {
        Boolean updateResult = true;
        int impactNum = lifeRecordMapper.updateLifeRecordByID(lifeRecord);
        if (impactNum <= 0) {
            updateResult = false;
            return updateResult;
        }
        logger.info("updateLifeRecordByID  --> ：" + lifeRecord.getId() + " impact num:" + impactNum);
        return updateResult;
    }

    @Override
    public Boolean addLifeRecord(LifeRecord lifeRecord) {
        Boolean addresult = true;
        int impactNum = lifeRecordMapper.addLifeRecord(lifeRecord);
        if (impactNum <= 0) {
            addresult = false;
            return addresult;
        }
        logger.info("add LifeRecord --> " + lifeRecord.getId() + " impact num：" + impactNum);
        return addresult;
    }

    @Override
    public Boolean deleteLifeRecordByID(String id) {
        Boolean delResult = true;
        int impactNum = lifeRecordMapper.deleteLifeRecordByID(id);
        if (impactNum <= 0) {
            delResult = false;
            return delResult;
        }
        logger.info("del LifeRecordByID  --> " + id + " 的结果：" + impactNum);
        return delResult;
    }


}
