package com.spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.entity.Student;

public interface StudentMapperExt extends StudentMapper {
	List<Student> selectStudentByName(@Param("name") String name);
}
