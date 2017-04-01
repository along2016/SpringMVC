package com.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.study.dao.StudentMapperExt;
import com.study.entity.Student;
import com.study.entity.StudentExample;
import com.study.service.StudentService;
import com.study.util.BeanUtil;
import com.study.util.CommonUtils;
import com.study.util.PagedResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapperExt studentMapperExt;

	public PagedResult<Student> queryByPage(String name, Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNo,pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		StudentExample example = new StudentExample();
		StudentExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(name)){
			criteria.andNameEqualTo(name);
		}
		List<Student> students = studentMapperExt.selectByExample(example);
		return BeanUtil.toPagedResult(students);
	}

	public int saveStudent(Student student) throws Exception{
		if(student.getId() == null){
			return studentMapperExt.insert(student);
		}
		return studentMapperExt.updateByPrimaryKey(student);
	}

	public int deleteStudent(String ids) throws Exception{
		StudentExample studentExample = new StudentExample();
		StudentExample.Criteria StudentExample = studentExample.createCriteria();
		StudentExample.andIdIn(Arrays.asList(CommonUtils.StringtoIntArr(ids)));
		return studentMapperExt.deleteByExample(studentExample);
	}

}
