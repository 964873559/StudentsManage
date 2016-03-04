package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import service.StudentsDAO;
import service.impl.StudentsDAOImpl;
import entity.Students;

//ʵ��ѧ����
public class StudentsAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��ѯ����ѧ���Ķ���
	public String Query(){
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list = sdao.queryAllStudents();
		//����session��
		if(list!=null&&list.size()>0){
			session.setAttribute("students_list", list);
		}
		return "students_query_success";
	}
	
	//ɾ��ѧ������
	public String Delete(){
		StudentsDAO sdao = new StudentsDAOImpl();
		String sid = request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
	}
	
	//���ѧ������
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
	//�޸�ѧ�����϶���
	public String Modify(){
		//��ô��ݹ�����ѧ�����
		String sid = request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = sdao.queryStudentsBySid(sid);
		//����s�ڻỰ��
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	
	//�����޸ĺ��ѧ�����϶���
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
