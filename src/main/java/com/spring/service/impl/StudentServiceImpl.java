package com.spring.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.spring.dao.StudentMapperExt;
import com.spring.entity.Student;
import com.spring.entity.StudentExample;
import com.spring.service.StudentService;
import com.spring.util.BeanUtil;
import com.spring.util.CommonUtils;
import com.spring.util.PagedResult;

import java.util.Arrays;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapperExt studentMapperExt;

	public PagedResult<Student> queryByPage(String name, Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo,pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		return BeanUtil.toPagedResult(studentMapperExt.selectStudentByName(name));
	}

	public int saveStudent(Student student) {
		if(student.getId() == null){
			return studentMapperExt.insert(student);
		}
		return studentMapperExt.updateByPrimaryKey(student);
	}

	public int deleteStudent(String ids) {
		StudentExample studentExample = new StudentExample();
		StudentExample.Criteria StudentExample = studentExample.createCriteria();
		StudentExample.andIdIn(Arrays.asList(CommonUtils.StringtoIntArr(ids)));
		return studentMapperExt.deleteByExample(studentExample);
	}

}
