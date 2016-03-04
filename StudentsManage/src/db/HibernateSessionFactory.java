package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;//会话工厂对象
    
//    构造方法私有化，单例模式
    private HibernateSessionFactory(){
    	
    }
    
//    共有的静态方法，获得会话工厂对象
    public static SessionFactory getSessionFactory(){
    	
    	if(sessionFactory==null){
    		Configuration config = new Configuration().configure();
    		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();  
    		//根据所注册的服务创建sessionFactory  
    		sessionFactory = config.buildSessionFactory(serviceRegistry);
    		return sessionFactory;
    	}else{
    		return sessionFactory;
    	}
    }

}