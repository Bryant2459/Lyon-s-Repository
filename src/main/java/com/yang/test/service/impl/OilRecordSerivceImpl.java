package com.yang.test.service.impl;

import com.yang.test.mapper.OilRecordMapper;
import com.yang.test.po.OilRecord;
import com.yang.test.service.IOilRecordService;
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
 * @Date: 2020/7/28 19:40
 * @Description:
 */
@Service
public class OilRecordSerivceImpl implements IOilRecordService {
    Logger logger= LoggerFactory.getLogger(OilRecordSerivceImpl.class);

    @Autowired(required = true)
    private OilRecordMapper oilRecordMapper;

    @Override
    public List<OilRecord> selectAllOilRecords() {

        List<OilRecord> recordList = oilRecordMapper.selectAllOilRecords();
        if (CollectionUtils.isEmpty(recordList)) {
            logger.info("select All Oil Records 查回来，没有记录");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Collections.sort(recordList,
                new Comparator<OilRecord>() {
                    @Override
                    public int compare(OilRecord o1, OilRecord o2) {
                        try {
                            Date parse1 = formatter.parse(o1.getAddOilDate());
                            Date parse2 = formatter.parse(o2.getAddOilDate());
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
        logger.info("SelectAll Oil Record 的结果：" + recordList.size());
        return recordList;
    }


    @Override
    public Boolean updateOilRecordByID(OilRecord oilRecord) {
        Boolean updateResult = true;
        int impactNum = oilRecordMapper.updateOilRecordByID(oilRecord);
        if (impactNum <= 0) {
            updateResult = false;
            return updateResult;
        }
        logger.info("Update Oil RecordByID  --> ：" + oilRecord.getId() + ": impact num:" + impactNum);
        return updateResult;
    }

    @Override
    public Boolean addOilRecord(OilRecord oilRecord) {
        Boolean addresult = true;
        int impactNum = oilRecordMapper.addOilRecord(oilRecord);
        if (impactNum <= 0) {
            addresult = false;
            return addresult;
        }
        logger.info("Add Oil Record --> " + oilRecord.getId() + " : impact num：" + impactNum);
        return addresult;
    }

    @Override
    public Boolean deleteOilRecordByID(String id) {
        Boolean delResult = true;
        int impactNum = oilRecordMapper.deleteOilRecordByID(id);
        if (impactNum <= 0) {
            delResult = false;
            return delResult;
        }
        logger.info("Del Oil Record id --> " + id + ": 的结果：" + impactNum);
        return delResult;
    }


}
