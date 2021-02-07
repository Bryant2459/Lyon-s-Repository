package com.yang.test.service.impl;

import com.yang.test.mapper.StudentMapper;
import com.yang.test.mapper.XiYaoPersonMapper;
import com.yang.test.po.Student;
import com.yang.test.po.XiYaoPerson;
import com.yang.test.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/7
 * Time: 21:39
 */
@Service
public class StudentServiceImpl implements IStudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired(required = true)
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectStudents() {
        List<Student> studentList = studentMapper.selectStudents();
        if (CollectionUtils.isEmpty(studentList)) {
            logger.info("select All studentList 查回来，没有记录");
        }
        logger.info("select All Students的结果：" + studentList.size());
        return studentList;

    }

    @Override
    public Boolean updateStudentByID(Student student) {
        Boolean updateResult = true;
        int impactNum = studentMapper.updateStudentByID(student);
        if (impactNum <= 0) {
            updateResult = false;
            return updateResult;
        }
        logger.info("updateStudentByID  --> ：" + student.getId() + " impact num:" + impactNum);
        return updateResult;
    }

    @Override
    public Boolean addStudent(Student student) {
        Boolean addresult = true;
        int impactNum = studentMapper.addStudent(student);
        if (impactNum <= 0) {
            addresult = false;
            return addresult;
        }
        logger.info("add Student --> " + student.getId() + " impact num：" + impactNum);
        return addresult;
    }

    @Override
    public Boolean deleteStudentByID(String id) {
        Boolean delResult = true;
        int impactNum = studentMapper.deleteStudentByID(id);
        if (impactNum <= 0) {
            delResult = false;
            return delResult;
        }
        logger.info("deleteStudentByID   --> " + id + " 的结果：" + impactNum);
        return delResult;
    }
}
