package test;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import service.StudentsDAO;
import service.impl.StudentsDAOImpl;
import entity.Students;

public class TestStudentsDAOImpl {

	@Test
	public void testQueryAllStudents(){
		StudentsDAOImpl sdao = new StudentsDAOImpl();
		List<Students> list = sdao.queryAllStudents();
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
//	@Test
//	public void testStudentsId(){
//		StudentsDAOImpl sdao = new StudentsDAOImpl();
//		System.out.println(sdao.getNewSid());
//	}
	
	@Test
	public void testAddStudents(){
		Students s = new Students();
		s.setSname("¼ÖÖÇ±ó");
		s.setGender("ÄÐ");
		s.setBirthday(new Date());
		s.setAddress("¹þ¹¤´ó");
		StudentsDAO sdao = new StudentsDAOImpl();
		Assert.assertEquals(true,sdao.addStudents(s));
		
	}
}
