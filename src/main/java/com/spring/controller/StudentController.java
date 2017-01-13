package com.spring.controller;

import com.spring.entity.Student;
import com.spring.service.StudentService;
import com.spring.util.Constant;
import com.spring.util.PagedResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.annotation.Resource;

@Controller
@RequestMapping(value="/student")
public class StudentController extends BasicController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private StudentService studentService;
	
	@RequestMapping(value="/list")
	public String list(){
		return "student/studentList";
	}
	
	@RequestMapping(value="/queryStudentByName")
	@ResponseBody
	public PagedResult<Student> queryStudentByName(String name, Integer pageNumber, Integer pageSize){
		logger.info("分页查询用户信息列表请求入参：pageNumber{},pageSize{}", pageNumber, pageSize);
		PagedResult<Student> pageResult = studentService.queryByPage(name, pageNumber, pageSize);
		return pageResult;
	}
	
	@RequestMapping(value="/saveStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveStudent(Student student){
		try {
			int saveResult = studentService.saveStudent(student);
			if(saveResult > 0){
				return resultMap(Constant.RET_STATUS_SUCCESS, Constant.SAVE_RET_TIP_SUCCESS);
			} else {
				return resultMap(Constant.RET_STATUS_FAIL, Constant.SAVE_RET_TIP_FAIL);
			}
		} catch (Exception e){
			logger.error("保存信息失败：{}", e);
			return resultMap(Constant.RET_STATUS_EXCEPTION, Constant.SAVE_RET_TIP_EXCEPTION);
		}
	}

	@RequestMapping(value="/delStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteStudent(String ids){
		try {
			int deleteResult = studentService.deleteStudent(ids);
			if(deleteResult > 0){
				return resultMap(Constant.RET_STATUS_SUCCESS, Constant.DEL_RET_TIP_SUCCESS);
			} else {
				return resultMap(Constant.RET_STATUS_FAIL, Constant.DEL_RET_TIP_FAIL);
			}
		} catch (Exception e){
			logger.error("删除信息失败：{}", e);
			return resultMap(Constant.RET_STATUS_EXCEPTION, Constant.DEL_RET_TIP_EXCEPTION);
		}
	}
}
