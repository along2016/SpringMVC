package com.study.service;

import com.study.entity.Student;
import com.study.util.PagedResult;

public interface StudentService {

	PagedResult<Student> queryByPage(String studentName, Integer pageNo, Integer pageSize);

	int saveStudent(Student student) throws Exception;

	int deleteStudent(String ids) throws Exception;
}
