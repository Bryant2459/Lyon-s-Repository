package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.constants.Constants;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.LifeRecord;
import com.yang.test.po.XiYaoPerson;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.ILifeRecordService;
import com.yang.test.service.IUserService;
import com.yang.test.service.IXiYaoPersonService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/lifeRecord")
public class LifeRecordController {
    Logger logger = LoggerFactory.getLogger(LifeRecordController.class);

    //LifeRecord lifeRecord
    @Autowired(required = true)
    private ILifeRecordService lifeRecordService;

    @Autowired(required = true)
    private IActionRecordService actionRecordService;

    //查询所有
    @RequestMapping("/selectAllLifeRecord")
    public Response selectAllLifeRecord(HttpSession session) {
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String userName = (String) session.getAttribute("currentUser");
        String password = (String) session.getAttribute("passWord");
        String realname = (String) session.getAttribute("realName");
        List<LifeRecord> listRecord = lifeRecordService.selectAllLifeRecord();
        Response response = new Response();
        if (CollectionUtils.isEmpty(listRecord)) {
            response.setStatus(Constants.FAILED_CODE);
            response.setMessage(Constants.SELECT_REMARK_MESSAGE);
            response.setErroCode(Constants.SELECT_FAILED_CODE);
            response.setData(null);
            return response;
        }
        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_SELECT);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_LIFE_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);
        // System.out.println("查出来的结果数量："+listRecord.size());
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来 life Record 的结果数量：" + listRecord.size());
        return response;
    }

    //查询所有
    @RequestMapping(value = "/selectLifeRecordByCategoryID", method = RequestMethod.POST)
    public Response selectLifeRecordByCategoryID(Integer categoryId, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<LifeRecord> listRecord = lifeRecordService.selectLifeRecordByCategoryID(categoryId);
        Response response = new Response();
        if (CollectionUtils.isEmpty(listRecord)) {
            response.setStatus(Constants.FAILED_CODE);
            response.setMessage(Constants.SELECT_REMARK_MESSAGE);
            response.setErroCode(Constants.SELECT_FAILED_CODE);
            response.setData(null);
            return response;
        }
        // System.out.println("查出来的结果数量："+listRecord.size());
        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_SELECT);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_LIFE_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);


        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来 life Record 的结果数量：" + listRecord.size());
        return response;
    }

    //修改数据
    @RequestMapping(value = "/updateLifeRecordByID", method = RequestMethod.POST)
    public Response updateLifeRecordByID(LifeRecord lifeRecord, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        LifeRecord record = new LifeRecord();
        Response response = new Response();
        if (StringUtils.isNotBlank(lifeRecord.getId())) {
            record.setId(lifeRecord.getId());
        }
        if (StringUtils.isNotBlank(lifeRecord.getProduceRecordDate())) {
            record.setProduceRecordDate(lifeRecord.getProduceRecordDate());
        } else {
            record.setProduceRecordDate("");
        }
        if (lifeRecord.getCategoryId() != null && lifeRecord.getCategoryId() >= 0) {
            record.setCategoryId(lifeRecord.getCategoryId());
        } else {
            record.setCategoryId(null);
        }
        if (StringUtils.isNotBlank(lifeRecord.getCategory())) {
            record.setCategory(lifeRecord.getCategory());
        } else {
            record.setCategory("");
        }
        if (StringUtils.isNotBlank(lifeRecord.getRecordProducer())) {
            record.setRecordProducer(lifeRecord.getRecordProducer());
        } else {
            record.setRecordProducer("");
        }
        if (lifeRecord.getMoney() != null) {
            record.setMoney(lifeRecord.getMoney());
        } else {
            record.setMoney(null);
        }
        if (StringUtils.isNotBlank(lifeRecord.getFirstAddDate())) {
            record.setFirstAddDate(lifeRecord.getFirstAddDate());
        }
        if (StringUtils.isNotBlank(lifeRecord.getLastUpdateDate())) {
            record.setLastUpdateDate(df.format(new Date()));
        } else {
            record.setLastUpdateDate(df.format(new Date()));
        }
        if (StringUtils.isNotBlank(lifeRecord.getRemark())) {
            record.setRemark(lifeRecord.getRemark());
        } else {
            record.setRemark("");
        }

        if (StringUtils.isNotBlank(lifeRecord.getOperator())) {
            record.setOperator(lifeRecord.getOperator());
        } else {
            record.setOperator("");
        }
        Boolean updateResult = lifeRecordService.updateLifeRecordByID(record);
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
        actionRecord.setService(AcitonConstants.SERVICE_LIFE_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //新增
    @RequestMapping(value = "/addLifeRecord", method = RequestMethod.POST)
    public Response addLifeRecord(LifeRecord lifeRecord, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        Response response = new Response();
        LifeRecord record = new LifeRecord();
        record.setId(UUID.randomUUID().toString());
        if (StringUtils.isNotBlank(lifeRecord.getProduceRecordDate())) {
            record.setProduceRecordDate(lifeRecord.getProduceRecordDate());
        } else {
            record.setProduceRecordDate("");
        }
        if (lifeRecord.getCategoryId() != null && lifeRecord.getCategoryId() >= 0) {
            record.setCategoryId(lifeRecord.getCategoryId());
            switch (lifeRecord.getCategoryId()) {
                case Constants.LIFE_RECORD_SUPERMARKET:
                    record.setCategory(Constants.LIFE_RECORD_SUPERMARKET_CH);
                    break;
                case Constants.LIFE_RECORD_SALARY:
                    record.setCategory(Constants.LIFE_RECORD_SALARY_CH);
                    break;
                case Constants.LIFE_RECORD_ABOUT_CAR:
                    record.setCategory(Constants.LIFE_RECORD_ABOUT_CAR_CH);
                    break;
                case Constants.LIFE_RECORD_PRINT:
                    record.setCategory(Constants.LIFE_RECORD_PRINT_CH);
                    break;
                case Constants.LIFE_RECORD_CALSS:
                    record.setCategory(Constants.LIFE_RECORD_CALSS_CH);
                    break;
                case Constants.LIFE_RECORD_BACK_HOME:
                    record.setCategory(Constants.LIFE_RECORD_BACK_HOME_CH);
                    break;
                default:
                    record.setCategory(Constants.LIFE_RECORD_OTHER_CH);
                    break;
            }
        } else {
            record.setCategoryId(null);
        }
        if (StringUtils.isNotBlank(lifeRecord.getRecordProducer())) {
            record.setRecordProducer(lifeRecord.getRecordProducer());
            switch (lifeRecord.getRecordProducer()) {
                case Constants.LIFE_RECORD_PERSON_LYON:
                    record.setRecordProducer(Constants.LIFE_RECORD_PERSON_LYON_CH);
                    break;
                case Constants.LIFE_RECORD_PERSON_YUYING:
                    record.setRecordProducer(Constants.LIFE_RECORD_PERSON_YUYING_CH);
                    break;
                default:
                    record.setRecordProducer(Constants.LIFE_RECORD_ABOUT_OTHER_CH);
                    break;
            }
        } else {
            record.setRecordProducer("");
        }
        if (lifeRecord.getMoney() != null) {
            record.setMoney(lifeRecord.getMoney());
        } else {
            record.setMoney(null);
        }
        if (StringUtils.isNotBlank(lifeRecord.getFirstAddDate())) {
            record.setFirstAddDate(lifeRecord.getFirstAddDate());
        } else {
            record.setFirstAddDate(df.format(new Date()));
        }
        if (StringUtils.isNotBlank(lifeRecord.getLastUpdateDate())) {
            record.setLastUpdateDate(df.format(new Date()));
        } else {
            record.setLastUpdateDate(df.format(new Date()));
        }
        if (StringUtils.isNotBlank(lifeRecord.getRemark())) {
            record.setRemark(lifeRecord.getRemark());
        } else {
            record.setRemark("");
        }
        if (StringUtils.isNotBlank(lifeRecord.getOperator())) {
            record.setOperator(lifeRecord.getOperator());
        } else {
            record.setOperator("");
        }

        Boolean addResult = lifeRecordService.addLifeRecord(record);

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
        actionRecord.setService(AcitonConstants.SERVICE_LIFE_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);


        response.setData(null);
        response.setMessage(Constants.ADD_SUCCESS_MESSAGE);
        response.setErroCode(Constants.ADD_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //删除
    @RequestMapping(value = "/deleteLifeRecordByID", method = RequestMethod.POST)
    public Response deleteLifeRecordByID(LifeRecord lifeRecord, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Response response = new Response();
        Boolean deleteResult = false;
        Boolean confirm = false;
        String id = lifeRecord.getId();
        if (StringUtils.isNotBlank(id)) {
            //确保传来的id 数据库中存在
            List<LifeRecord> allRecord = lifeRecordService.selectAllLifeRecord();
            for (LifeRecord tempLifeRecord : allRecord) {
                if (StringUtils.equals(tempLifeRecord.getId(), id)) {
                    confirm = true;
                    break;
                }
            }
            if (confirm) {
                deleteResult = lifeRecordService.deleteLifeRecordByID(id);
            } else {
                response.setData(null);
                response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                response.setErroCode(Constants.DELETE_FAILED_CODE);
                response.setStatus(Constants.FAILED_CODE);
                System.out.println(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                return response;
            }
        } else {
            response.setData(null);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            System.out.println(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
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
        actionRecord.setService(AcitonConstants.SERVICE_LIFE_RECORD);
        actionRecord.setRecordId(lifeRecord.getId());
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.DELETE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }
}
