package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import service.StudentsDAO;
import service.impl.StudentsDAOImpl;
import entity.Students;

//实现学生类
public class StudentsAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//查询所有学生的动作
	public String Query(){
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list = sdao.queryAllStudents();
		//放入session中
		if(list!=null&&list.size()>0){
			session.setAttribute("students_list", list);
		}
		return "students_query_success";
	}
	
	//删除学生动作
	public String Delete(){
		StudentsDAO sdao = new StudentsDAOImpl();
		String sid = request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
	}
	
	//添加学生动作
	public String Add() throws Exception{
		Students s = new Students();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao = new StudentsDAOImpl();
		sdao.addStudents(s);
		
		return "add_success";
	}
	//修改学生资料动作
	public String Modify(){
		//获得传递过来的学生编号
		String sid = request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = sdao.queryStudentsBySid(sid);
		//保存s在会话中
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	
	//保存修改后的学生资料动作
	public String Save() throws Exception{
		Students s = new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao = new StudentsDAOImpl();
		sdao.updateStudents(s);
		return "save_success";
	}
}
