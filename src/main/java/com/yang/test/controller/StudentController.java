package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.constants.Constants;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.Student;
import com.yang.test.po.XiYaoPerson;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.IStudentService;
import com.yang.test.service.IXiYaoPersonService;
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

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/7
 * Time: 21:45
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired(required = true)
    private IStudentService studentService;

    @Autowired(required = true)
    private IActionRecordService actionRecordService;

    //查询所有
    @RequestMapping("/selectStudents")
    public Response selectStudents(HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Student> listRecord = studentService.selectStudents();
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
        actionRecord.setService(AcitonConstants.SERVICE_STUDENT_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);

        // System.out.println("查出来的结果数量："+listRecord.size());
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来 Student 的结果数量：" + listRecord.size());
        return response;
    }

    //修改数据
    @RequestMapping(value = "/updateStudentByID", method = RequestMethod.POST)
    public Response updateStudentByID(Student student, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        Student record = new Student();
        Response response = new Response();
        if (StringUtils.isNotBlank(student.getId())) {
            record.setId(student.getId());
        }
        if (StringUtils.isNotBlank(student.getName())) {
            record.setName(student.getName());
        } else {
            record.setName("");
        }
        if (StringUtils.isNotBlank(student.getSId())) {
            record.setSId(student.getSId());
        } else {
            record.setSId("");
        }
        if (StringUtils.isNotBlank(student.getClasses())) {
            record.setClasses(student.getClasses());
        } else {
            record.setClasses("");
        }
        if (StringUtils.isNotBlank(student.getGrade())) {
            record.setGrade(student.getGrade());
        } else {
            record.setGrade("");
        }
        if (StringUtils.isNotBlank(student.getRoldId())) {
            record.setRoldId(student.getRoldId());
        } else {
            record.setRoldId("");
        }
        if (StringUtils.isNotBlank(student.getFirstDate())) {
            record.setFirstDate(student.getFirstDate());
        } else {
            record.setFirstDate(df.format(new Date()));
        }
        record.setLastDate(df.format(new Date()));

        Boolean updateResult = studentService.updateStudentByID(record);
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
        actionRecord.setService(AcitonConstants.SERVICE_STUDENT_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //新增
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public Response addStudent(Student student, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        Response response = new Response();
        Student addStudent = new Student();
        addStudent.setId(UUID.randomUUID().toString());
        if (StringUtils.isNotBlank(student.getName())) {
            addStudent.setName(student.getName());
        } else {
            addStudent.setName("");
        }
        if (StringUtils.isNotBlank(student.getSId())) {
            addStudent.setSId(student.getSId());
        } else {
            addStudent.setSId("");
        }
        if (StringUtils.isNotBlank(student.getClasses())) {
            addStudent.setClasses(student.getClasses());
        } else {
            addStudent.setClasses("");
        }
        if (StringUtils.isNotBlank(student.getGrade())) {
            addStudent.setGrade(student.getGrade());
        } else {
            addStudent.setGrade("");
        }
        if (StringUtils.isNotBlank(student.getRoldId())) {
            addStudent.setRoldId(student.getRoldId());
        } else {
            addStudent.setRoldId("");
        }
        addStudent.setFirstDate(df.format(new Date()));
        addStudent.setLastDate("");

        Boolean addResult = studentService.addStudent(addStudent);

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
        actionRecord.setService(AcitonConstants.SERVICE_STUDENT_RECORD);
        actionRecord.setRecordId(student.getId());
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.ADD_SUCCESS_MESSAGE);
        response.setErroCode(Constants.ADD_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //删除
    @RequestMapping(value = "/deleteStudentByID", method = RequestMethod.POST)
    public Response deleteStudentByID(Student student, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Response response = new Response();
        Boolean deleteResult = false;
        Boolean confirm = false;
        String id = student.getId();
        if (StringUtils.isNotBlank(id) && StringUtils.isNotEmpty(id)) {
            //确保传来的id 数据库中存在
            List<Student> allRecord = studentService.selectStudents();
            for (Student tempStudent : allRecord) {
                if (StringUtils.equals(tempStudent.getId(), id)) {
                    confirm = true;
                }
            }
            if (confirm) {
                deleteResult = studentService.deleteStudentByID(id);
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
        actionRecord.setService(AcitonConstants.SERVICE_STUDENT_RECORD);
        actionRecord.setRecordId(id);
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.DELETE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);

        return response;
    }

}
