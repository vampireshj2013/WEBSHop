package com.shop.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shop.model.Product;
import com.shop.model.SecondLevelType;

import junit.framework.TestCase;

public class Test2 extends TestCase {
	static Session getsSession(){
		Session session = null;
			try {
				Configuration configuration = new Configuration().configure();
				SessionFactory factory = configuration.buildSessionFactory();
				session =  factory.openSession();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return session;	
	}
	
	public void test1()
	{
		Session session = getsSession();
		try {
			session.beginTransaction();
			
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally
		{
			session.close();
		}
	}

}
