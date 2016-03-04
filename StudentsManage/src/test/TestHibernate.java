package test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import entity.Students;


public class TestHibernate {
	
	@Test
	
	public void testHibernate(){
//		�������ö���
		Configuration config = new Configuration().configure();
//		��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//������ע��ķ��񴴽�sessionFactory  
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//	    ����session����
	    @SuppressWarnings("unused")
		Session session = sessionFactory.getCurrentSession();
//	    ����SchemaExport����
	    SchemaExport export = new SchemaExport(config);
	    export.create(true, true);
	}
	
	
	@Test
	
	public void testSaveStudents(){
		
//		�������ö���
		Configuration config = new Configuration().configure();
//		��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//������ע��ķ��񴴽�sessionFactory  
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//	    ����session����
		Session session = sessionFactory.getCurrentSession();
//		�����������
		Transaction tx = session.beginTransaction();
		Students s1 = new Students("������","������",new Date(),"��","H6666666");
		Students s2 = new Students("������","���ǟj",new Date(),"��","H7777777");
		Students s3 = new Students("������","���Ǳ�",new Date(),"��","H8888888");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		tx.commit();
		sessionFactory.close();
		
	}
}
