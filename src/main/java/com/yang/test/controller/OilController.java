package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.constants.Constants;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.OilRecord;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.impl.OilRecordSerivceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/oil")
public class OilController {
    Logger logger = LoggerFactory.getLogger(OilController.class);

    @Autowired(required = true)
    private OilRecordSerivceImpl oilRecordSerivceImpl;

    @Autowired(required = true)
    private IActionRecordService actionRecordService;

    //查询所有
    @RequestMapping("/findAll")
    public Response findAll(HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<OilRecord> listRecord = oilRecordSerivceImpl.selectAllOilRecords();
        Response response = new Response();
        if (CollectionUtils.isEmpty(listRecord)) {
            response.setStatus(Constants.FAILED_CODE);
            response.setMessage(Constants.SELECT_REMARK_MESSAGE);
            response.setErroCode(Constants.SELECT_FAILED_CODE);
            response.setData(null);
            return response;
        }
        // System.out.println("查出来的结果数量："+listRecord.size());
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_SELECT);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_OIL_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来 Oil Record 的结果数量：" + listRecord.size());
        return response;
    }

    //修改数据
    @RequestMapping(value = "/updateRecordById", method = RequestMethod.POST)
    public Response updatePerson(OilRecord oilRecord, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        OilRecord record = new OilRecord();
        Response response = new Response();
        if (StringUtils.isNotBlank(oilRecord.getId())) {
            record.setId(oilRecord.getId());
        }
        if (StringUtils.isNotBlank(oilRecord.getAddOilDate())) {
            record.setAddOilDate(oilRecord.getAddOilDate());
        } else {
            record.setAddOilDate(df.format(new Date()));
        }
        if (null != oilRecord.getOilVolume()) {
            record.setOilVolume(oilRecord.getOilVolume());
        } else {
            record.setOilVolume(new Double(0.0));
        }
        if (StringUtils.isNotBlank(oilRecord.getDashboardMileage())) {
            record.setDashboardMileage(oilRecord.getDashboardMileage());
        } else {
            record.setDashboardMileage("");
        }
        if (null != oilRecord.getCost()) {
            record.setCost(oilRecord.getCost());
        } else {
            record.setCost(null);
        }
        if (null != oilRecord.getRealCost()) {
            record.setRealCost(oilRecord.getRealCost());
        } else {
            record.setRealCost(null);
        }
        if (null != oilRecord.getOilUnitPrice()) {
            record.setOilUnitPrice(oilRecord.getOilUnitPrice());
        } else {
            record.setOilUnitPrice(null);
        }
        if (null != oilRecord.getRealOilUnitPrice()) {
            record.setRealOilUnitPrice(oilRecord.getRealOilUnitPrice());
        } else {
            record.setRealOilUnitPrice(null);
        }
        if (StringUtils.isNotBlank(oilRecord.getRemark())) {
            record.setRemark(oilRecord.getRemark());
        } else {
            record.setRemark("");
        }
        if (StringUtils.isNotBlank(oilRecord.getRecordFirstDate())) {
            record.setRecordFirstDate(oilRecord.getRecordFirstDate());
        }
        if (StringUtils.isNotBlank(oilRecord.getRecordLastUpdateDate())) {

            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            record.setRecordLastUpdateDate(df.format(new Date()));
        } else {
            record.setRecordLastUpdateDate(df.format(new Date()));
        }
        if (StringUtils.isNotBlank(oilRecord.getOperator())) {
            record.setOperator(oilRecord.getOperator());
        } else {
            record.setOperator(null);
        }
        Boolean updateResult = oilRecordSerivceImpl.updateOilRecordByID(record);
        if (!updateResult) {
            response.setData(null);
            response.setMessage(Constants.UPDATE_FAILED_MESSAGE);
            response.setErroCode(Constants.UPDATE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_UPDATE);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_OIL_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);


        response.setData(null);
        response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //新增
    @RequestMapping(value = "/addOilRecord", method = RequestMethod.POST)
    public Response addOilRecord(OilRecord oilRecord, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Response response = new Response();
        OilRecord record = new OilRecord();
        record.setId(UUID.randomUUID().toString());
        if (StringUtils.isNotBlank(oilRecord.getAddOilDate())) {
            record.setAddOilDate(oilRecord.getAddOilDate());
        } else {
            //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            record.setAddOilDate(df.format(new Date()));
        }
        if (null != oilRecord.getOilVolume()) {
            record.setOilVolume(oilRecord.getOilVolume());
        } else {
            record.setOilVolume(new Double(0.0));
        }
        if (StringUtils.isNotBlank(oilRecord.getDashboardMileage())) {
            record.setDashboardMileage(oilRecord.getDashboardMileage());
        }
        if (null != oilRecord.getCost()) {
            record.setCost(oilRecord.getCost());
        } else {
            record.setCost(new Double(0.0));
        }
        if (null != oilRecord.getRealCost()) {
            record.setRealCost(oilRecord.getRealCost());
        } else {
            record.setRealCost(new Double(0.0));
        }
        if (null != oilRecord.getOilUnitPrice()) {
            record.setOilUnitPrice(oilRecord.getOilUnitPrice());
        } else {
            record.setOilUnitPrice(new Double(0.0));
        }
        if (null != oilRecord.getRealOilUnitPrice()) {
            record.setRealOilUnitPrice(oilRecord.getRealOilUnitPrice());
        } else {
            record.setRealOilUnitPrice(new Double(0.0));
        }

        if (StringUtils.isNotBlank(oilRecord.getRemark())) {
            record.setRemark(oilRecord.getRemark());
        } else {
            record.setRemark("");
        }
        //新增数据，记录时间，之后不变
        if (StringUtils.isBlank(oilRecord.getRecordFirstDate())) {
            record.setRecordFirstDate(df.format(new Date()));
        }
        //更新字段。
        if (StringUtils.isBlank(oilRecord.getRecordLastUpdateDate())) {
            record.setRecordLastUpdateDate(df.format(new Date()));
        } else {
            record.setRecordLastUpdateDate(oilRecord.getRecordLastUpdateDate());
        }

        if (StringUtils.isNotBlank(oilRecord.getOperator())) {
            record.setOperator(oilRecord.getOperator());
        } else {
            record.setOperator("");
        }
        Boolean addResult = oilRecordSerivceImpl.addOilRecord(record);

        if (!addResult) {
            response.setData(null);
            response.setMessage(Constants.ADD_FAILED_MESSAGE);
            response.setErroCode(Constants.ADD_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_ADD);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_OIL_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setMessage(Constants.ADD_SUCCESS_MESSAGE);
        response.setErroCode(Constants.ADD_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //删除
    @RequestMapping(value = "/deleteOilRecordByID", method = RequestMethod.POST)
    public Response deleteOilRecordByID(OilRecord oilRecord, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Response response = new Response();
        Boolean deleteResult = false;
        Boolean confirm = false;
        String id = oilRecord.getId();
        if (StringUtils.isNotBlank(id) && StringUtils.isNotEmpty(id)) {
            //确保传来的id 数据库中存在
            List<OilRecord> allOilRecord = oilRecordSerivceImpl.selectAllOilRecords();
            for (OilRecord tempOilRecord : allOilRecord) {
                if (StringUtils.equals(tempOilRecord.getId(), oilRecord.getId())) {
                    confirm = true;
                }
            }
            if (confirm) {
                deleteResult = oilRecordSerivceImpl.deleteOilRecordByID(id);
            } else {
                response.setData(null);
                response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                response.setErroCode(Constants.DELETE_FAILED_CODE);
                response.setStatus(Constants.FAILED_CODE);
                logger.info(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                return response;
            }
        } else {
            response.setData(null);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            logger.info(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
            return response;
        }

        if (deleteResult == false) {
            response.setData(null);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE);
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_DELETE);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_OIL_RECORD);
        actionRecord.setRecordId(oilRecord.getId());
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.DELETE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }
}
