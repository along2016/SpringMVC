package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.study.dao.StudentMapperExt;
import com.study.entity.Student;
import com.study.entity.StudentExample;
import com.study.service.StudentService;
import com.study.util.BeanUtil;
import com.study.util.CommonUtils;
import com.study.util.PagedResult;

import org.springframework.stereotype.Service;

import java.util.Arrays;

import javax.annotation.Resource;

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
		System.out.println(1/0);
		return studentMapperExt.updateByPrimaryKey(student);
	}

	public int deleteStudent(String ids) {
		StudentExample studentExample = new StudentExample();
		StudentExample.Criteria StudentExample = studentExample.createCriteria();
		StudentExample.andIdIn(Arrays.asList(CommonUtils.StringtoIntArr(ids)));
		return studentMapperExt.deleteByExample(studentExample);
	}

}
