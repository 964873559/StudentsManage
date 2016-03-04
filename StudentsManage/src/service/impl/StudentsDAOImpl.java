package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.StudentsDAO;
import db.HibernateSessionFactory;
import entity.Students;

//ѧ��ҵ���߼��ӿڵ�ʵ��
public class StudentsDAOImpl implements StudentsDAO{

	//��ѯ����ѧ������
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

	//ͨ��ѧ�Ų�ѯѧ����Ϣ
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
		s.setSid(getNewSid());//����ѧ��
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

	
	//����ѧ����ѧ��
	public String getNewSid(){
		Transaction tx = null;
		String hql = "";
		String sid = null;
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//��õ�ǰѧ�������ѧ��
			hql = "select max(sid) from Students";
			Query query = session.createQuery(hql);
			sid = (String)query.uniqueResult();
			if(sid==null||"".equals(sid)){
				sid = "H0000001";
			}else{
				String temp = sid.substring(1);//ȡ����λ
				int i = Integer.parseInt(temp);//׼��Ϊ�ַ���
				i++;
				//ת��Ϊ�ַ���
				temp = String.valueOf(i);
				int len = temp.length();
				//�չ�7λ��
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
