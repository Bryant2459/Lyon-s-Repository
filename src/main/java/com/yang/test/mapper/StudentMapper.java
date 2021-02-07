package com.yang.test.mapper;

import com.yang.test.po.Student;
import com.yang.test.po.XiYaoPerson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    //查询
    List<Student> selectStudents();

    //按照ID 修改记录
    int updateStudentByID(Student student);

    //新增
    int addStudent(Student student);

    //删除
    int deleteStudentByID(String id);

}
