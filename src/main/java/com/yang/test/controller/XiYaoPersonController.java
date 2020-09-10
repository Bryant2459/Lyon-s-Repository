package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.constants.Constants;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.PrintIncome;
import com.yang.test.po.XiYaoPerson;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.IXiYaoPersonService;
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
@RequestMapping(value = "/xiyao")
public class XiYaoPersonController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired(required = true)
    private IXiYaoPersonService xiYaoPersonService;

    @Autowired(required = true)
    private IActionRecordService actionRecordService;

    //查询所有
    @RequestMapping("/selectAllXiYaoPerson")
    public Response selectAllXiYaoPerson(HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<XiYaoPerson> listRecord = xiYaoPersonService.selectAllXiYaoPerson();
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
        actionRecord.setService(AcitonConstants.SERVICE_XIYAO_PERSON_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);

        // System.out.println("查出来的结果数量："+listRecord.size());
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来xiyao person的结果数量：" + listRecord.size());
        return response;
    }

    //修改数据
    @RequestMapping(value = "/updateXiYaoPersonByID", method = RequestMethod.POST)
    public Response updateXiYaoPersonByID(XiYaoPerson xiYaoPerson, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        XiYaoPerson record = new XiYaoPerson();
        Response response = new Response();
        if (StringUtils.isNotBlank(xiYaoPerson.getId())) {
            record.setId(xiYaoPerson.getId());
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getAddress())) {
            record.setAddress(xiYaoPerson.getAddress());
        } else {
            record.setAddress("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getFamilyMember())) {
            record.setFamilyMember(xiYaoPerson.getFamilyMember());
        } else {
            record.setFamilyMember("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getHeadHousHold())) {
            record.setHeadHousHold(xiYaoPerson.getHeadHousHold());
        } else {
            record.setHeadHousHold("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getIdentityCard())) {
            record.setIdentityCard(xiYaoPerson.getIdentityCard());
        } else {
            record.setIdentityCard("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getPhone())) {
            record.setPhone(xiYaoPerson.getPhone());
        } else {
            record.setPhone("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getRelation())) {
            record.setRelation(xiYaoPerson.getRelation());
        } else {
            record.setRelation("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getRemark())) {
            record.setRemark(xiYaoPerson.getRemark());
        } else {
            record.setRemark("");
        }
        if (xiYaoPerson.getAge() >= 0) {
            record.setAge(xiYaoPerson.getAge());
        } else {
            record.setAge(0);
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getFirstAddtime())) {
            record.setFirstAddtime(xiYaoPerson.getFirstAddtime());
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getLastUpdate())) {
            System.out.println(df.format(new Date()));
            record.setLastUpdate(df.format(new Date()));
        } else {
            record.setLastUpdate(df.format(new Date()));
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getOperator())) {
            record.setOperator(xiYaoPerson.getOperator());
        } else {
            record.setOperator("");
        }
        Boolean updateResult = xiYaoPersonService.updateXiYaoPersonByID(record);
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
        actionRecord.setService(AcitonConstants.SERVICE_XIYAO_PERSON_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //新增
    @RequestMapping(value = "/addXiYaoPerson", method = RequestMethod.POST)
    public Response addXiYaoPerson(XiYaoPerson xiYaoPerson, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");

        Response response = new Response();
        XiYaoPerson addXiYaoPerson = new XiYaoPerson();
        addXiYaoPerson.setId(UUID.randomUUID().toString());
        if (StringUtils.isNotBlank(xiYaoPerson.getId())) {
            addXiYaoPerson.setId(xiYaoPerson.getId());
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getAddress())) {
            addXiYaoPerson.setAddress(xiYaoPerson.getAddress());
        } else {
            addXiYaoPerson.setAddress("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getFamilyMember())) {
            addXiYaoPerson.setFamilyMember(xiYaoPerson.getFamilyMember());
        } else {
            addXiYaoPerson.setFamilyMember("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getHeadHousHold())) {
            addXiYaoPerson.setHeadHousHold(xiYaoPerson.getHeadHousHold());
        } else {
            addXiYaoPerson.setHeadHousHold("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getIdentityCard())) {
            addXiYaoPerson.setIdentityCard(xiYaoPerson.getIdentityCard());
        } else {
            addXiYaoPerson.setIdentityCard("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getPhone())) {
            addXiYaoPerson.setPhone(xiYaoPerson.getPhone());
        } else {
            addXiYaoPerson.setPhone("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getRelation())) {
            addXiYaoPerson.setRelation(xiYaoPerson.getRelation());
        } else {
            addXiYaoPerson.setRelation("");
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getRemark())) {
            addXiYaoPerson.setRemark(xiYaoPerson.getRemark());
        } else {
            addXiYaoPerson.setRemark("");
        }
        if (xiYaoPerson.getAge() >= 0) {
            addXiYaoPerson.setAge(xiYaoPerson.getAge());
        } else {
            addXiYaoPerson.setAge(0);
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getFirstAddtime())) {
            addXiYaoPerson.setFirstAddtime(xiYaoPerson.getFirstAddtime());
        } else {
            addXiYaoPerson.setFirstAddtime(df.format(new Date()));
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getLastUpdate())) {
            addXiYaoPerson.setLastUpdate(df.format(new Date()));
        } else {
            addXiYaoPerson.setLastUpdate(df.format(new Date()));
        }
        if (StringUtils.isNotBlank(xiYaoPerson.getOperator())) {
            addXiYaoPerson.setOperator(xiYaoPerson.getOperator());
        } else {
            addXiYaoPerson.setOperator("");
        }
        Boolean addResult = xiYaoPersonService.addXiYaoPerson(addXiYaoPerson);

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
        actionRecord.setService(AcitonConstants.SERVICE_XIYAO_PERSON_RECORD);
        actionRecord.setRecordId(addXiYaoPerson.getId());
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.ADD_SUCCESS_MESSAGE);
        response.setErroCode(Constants.ADD_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //删除
    @RequestMapping(value = "/deleteXiYaoPersonByID", method = RequestMethod.POST)
    public Response deleteXiYaoPersonByID(XiYaoPerson xiYaoPerson, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Response response = new Response();
        Boolean deleteResult = false;
        Boolean confirm = false;
        String id = xiYaoPerson.getId();
        if (StringUtils.isNotBlank(id) && StringUtils.isNotEmpty(id)) {
            //确保传来的id 数据库中存在
            List<XiYaoPerson> allRecord = xiYaoPersonService.selectAllXiYaoPerson();
            for (XiYaoPerson tempXiYaoPerson : allRecord) {
                if (StringUtils.equals(tempXiYaoPerson.getId(), id)) {
                    confirm = true;
                }
            }
            if (confirm) {
                deleteResult = xiYaoPersonService.deleteXiYaoPersonByID(id);
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
        actionRecord.setService(AcitonConstants.SERVICE_XIYAO_PERSON_RECORD);
        actionRecord.setRecordId(id);
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.DELETE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }
}
