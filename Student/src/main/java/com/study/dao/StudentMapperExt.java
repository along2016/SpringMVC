package com.study.dao;

import com.study.entity.Student;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapperExt extends StudentMapper {
	List<Student> selectStudentByName(@Param("name") String name);
}
