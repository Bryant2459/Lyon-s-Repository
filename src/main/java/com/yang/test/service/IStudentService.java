package com.yang.test.service;

import com.yang.test.po.Student;
import com.yang.test.po.XiYaoPerson;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/7
 * Time: 21:33
 */
public interface IStudentService {
    //查询所有
    List<Student> selectStudents();

    //修改
    Boolean updateStudentByID(Student student);

    //新增
    Boolean addStudent(Student student);

    //删除

    Boolean deleteStudentByID(String id);
}
