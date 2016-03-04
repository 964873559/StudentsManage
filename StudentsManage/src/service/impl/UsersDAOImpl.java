package service.impl;




import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import service.UsersDAO;
import db.HibernateSessionFactory;
import entity.Users;

public class UsersDAOImpl implements UsersDAO{

	public boolean usersLogin(Users u) {
//		事物对象
		org.hibernate.Transaction tx = null;
		String hql = "";
		try{
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username = ? and password = ?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1,u.getPassword());
			@SuppressWarnings("rawtypes")
			List list = query.list();
			tx.commit();//提交事物
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
	}

}
