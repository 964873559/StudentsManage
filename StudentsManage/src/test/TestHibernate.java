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
//		创建配置对象
		Configuration config = new Configuration().configure();
//		创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//根据所注册的服务创建sessionFactory  
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//	    创建session对象
	    @SuppressWarnings("unused")
		Session session = sessionFactory.getCurrentSession();
//	    创建SchemaExport对象
	    SchemaExport export = new SchemaExport(config);
	    export.create(true, true);
	}
	
	
	@Test
	
	public void testSaveStudents(){
		
//		创建配置对象
		Configuration config = new Configuration().configure();
//		创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//根据所注册的服务创建sessionFactory  
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//	    创建session对象
		Session session = sessionFactory.getCurrentSession();
//		创建事物对象
		Transaction tx = session.beginTransaction();
		Students s1 = new Students("哈工大","黄运智",new Date(),"男","H6666666");
		Students s2 = new Students("哈工大","刘星焜",new Date(),"男","H7777777");
		Students s3 = new Students("哈工大","贾智斌",new Date(),"男","H8888888");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		tx.commit();
		sessionFactory.close();
		
	}
}
