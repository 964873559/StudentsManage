package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.StudentsDAO;
import db.HibernateSessionFactory;
import entity.Students;

//学生业务逻辑接口的实现
public class StudentsDAOImpl implements StudentsDAO{

	//查询所有学生资料
	public List<Students> queryAllStudents() {
		Transaction tx = null;
		List<Students> list = null;
		String hql = "";
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Students";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return list;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	//通过学号查询学生信息
	@Override
	public Students queryStudentsBySid(String sid) {
		Transaction tx = null;
		Students s = new Students();
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = (Students)session.get(Students.class, sid);
			tx.commit();
			return s;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return s;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	@Override
	public boolean addStudents(Students s) {
		s.setSid(getNewSid());//设置学号
		Transaction tx = null;
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	@Override
	public boolean updateStudents(Students s) {
		Transaction tx = null;
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	@Override
	public boolean deleteStudents(String sid) {
		Transaction tx = null;
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Students s = (Students)session.get(Students.class, sid);
			session.delete(s);
			tx.commit();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}

	
	//生成学生的学号
	public String getNewSid(){
		Transaction tx = null;
		String hql = "";
		String sid = null;
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//获得当前学生的最大学号
			hql = "select max(sid) from Students";
			Query query = session.createQuery(hql);
			sid = (String)query.uniqueResult();
			if(sid==null||"".equals(sid)){
				sid = "H0000001";
			}else{
				String temp = sid.substring(1);//取后七位
				int i = Integer.parseInt(temp);//准换为字符串
				i++;
				//转换为字符串
				temp = String.valueOf(i);
				int len = temp.length();
				//凑够7位数
				for(int j = 0;j<7-len;j++){
					temp = "0" + temp;
				}
				sid = "H" + temp;
			}
			tx.commit();
			return sid;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.commit();
			return null;
		}finally{
			if(tx!=null){
				tx = null;
			}
		}
	}
}
