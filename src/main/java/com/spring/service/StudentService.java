package com.spring.service;

import com.spring.entity.Student;
import com.spring.util.PagedResult;

public interface StudentService {

	PagedResult<Student> queryByPage(String studentName, Integer pageNo,Integer pageSize);

	int saveStudent(Student student);

	int deleteStudent(String ids);
}
