package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.constants.Constants;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.PrintIncome;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.impl.PrintIncomeSerivceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/print")
public class PrintController {
    Logger logger = LoggerFactory.getLogger(PrintController.class);

    // 获取.yml 文件中值
    @Value("${name}")
    private String name;

    @Autowired(required = true)
    private PrintIncomeSerivceImpl printIncomeService;

    @Autowired(required = true)
    private IActionRecordService actionRecordService;

    //查询所有
    @RequestMapping("/findAllPrintRecords")
    public Response findAllPrintRecords(HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<PrintIncome> listRecord = printIncomeService.findAll();
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
        actionRecord.setService(AcitonConstants.SERVICE_PRINT_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);
        // System.out.println("查出来的结果数量："+listRecord.size());
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来的结果数量：" + listRecord.size());
        return response;
    }

    //修改数据
    @RequestMapping(value = "/updatePrintRecordById", method = RequestMethod.POST)
    public Response updatePerson(PrintIncome printIncome, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PrintIncome record = new PrintIncome();
        Response response = new Response();
        if (StringUtils.isNotBlank(printIncome.getId()) && StringUtils.isNotEmpty(printIncome.getId())) {
            record.setId(printIncome.getId());
        }
        if (StringUtils.isNotBlank(printIncome.getDate()) && StringUtils.isNotEmpty(printIncome.getDate())) {
            record.setDate(printIncome.getDate());
        }
        if (null != printIncome.getMoney()) {
            record.setMoney(printIncome.getMoney());
        }

        if (StringUtils.isNotBlank(printIncome.getRemark()) && StringUtils.isNotEmpty(printIncome.getRemark())) {
            record.setRemark(printIncome.getRemark());
        } else {
            record.setRemark("");
        }
        Boolean updateResult = printIncomeService.updateRecordByID(record);
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
        actionRecord.setService(AcitonConstants.SERVICE_PRINT_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);


        response.setData(null);
        response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //新增
    @RequestMapping(value = "/addPrintRecord", method = RequestMethod.POST)
    public Response addRecord(PrintIncome printIncome, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Response response = new Response();
        PrintIncome addPrintIncome = new PrintIncome();
        addPrintIncome.setId(UUID.randomUUID().toString());
        if (StringUtils.isNotBlank(printIncome.getDate()) && StringUtils.isNotEmpty(printIncome.getDate())) {
            addPrintIncome.setDate(printIncome.getDate());
        } else {
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            addPrintIncome.setDate(df.format(new Date()));
        }
        if (null != printIncome.getMoney()) {
            addPrintIncome.setMoney(printIncome.getMoney());
        } else {
            addPrintIncome.setMoney(0.00);
        }
        if (StringUtils.isNotBlank(printIncome.getRemark()) && StringUtils.isNotEmpty(printIncome.getRemark())) {
            addPrintIncome.setRemark(printIncome.getRemark());
        }
        Boolean addResult = printIncomeService.addRecord(addPrintIncome);

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
        actionRecord.setService(AcitonConstants.SERVICE_PRINT_RECORD);
        actionRecord.setRecordId(printIncome.getId());
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setMessage(Constants.ADD_SUCCESS_MESSAGE);
        response.setErroCode(Constants.ADD_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //删除
    @RequestMapping(value = "/deletePrintRecord", method = RequestMethod.POST)
    public Response deleteRecord(PrintIncome printIncome, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Response response = new Response();
        Boolean deleteResult = false;
        Boolean confirm = false;
        String id = printIncome.getId();
        if (StringUtils.isNotBlank(id) && StringUtils.isNotEmpty(id)) {
            //确保传来的id 数据库中存在
            List<PrintIncome> allRecord = printIncomeService.findAll();
            for (PrintIncome tempPrintIncome : allRecord) {
                if (StringUtils.equals(tempPrintIncome.getId(), id)) {
                    confirm = true;
                }
            }
            if (confirm) {
                deleteResult = printIncomeService.delleteRecord(id);
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
        actionRecord.setService(AcitonConstants.SERVICE_PRINT_RECORD);
        actionRecord.setRecordId(id);
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.DELETE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }
}
