package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.entity.Student;
import com.spring.service.StudentService;
import com.sun.istack.internal.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class StudentTest{

	private static Logger logger = Logger.getLogger(StudentTest.class);
	
	@Resource
	private StudentService studentService;
	
	@Test
	public void test1(){
//		Student student = studentService.getStudentById(1);
	}
}
