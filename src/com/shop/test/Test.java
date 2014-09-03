package com.shop.test;import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.apache.commons.io.FileUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.shop.dao.BaseDao;
import com.shop.dao.UserDao;
import com.shop.daoInf.TypeDaoInf;
import com.shop.daoInf.UserDaoInf;
import com.shop.model.FirstLevelType;
import com.shop.model.Product;
import com.shop.model.SecondLevelType;
import com.shop.model.ShopCart;
import com.shop.model.User;
import com.shop.service.ProductService;
import com.shop.service.UserService;


import junit.framework.TestCase;

public class Test extends TestCase{
	
	private User user = null;
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
	public void  test9()
	{	Session session = getsSession();
	
	try {
		session.beginTransaction();
		Product p = (Product)session.get(Product.class, 8);
		byte[] a = p.getPictureArray();
		String path = "D:/test2.jpg";
		File file =  new File(path);
		FileUtils.writeByteArrayToFile(file, p.getPictureArray());
		session.getTransaction().commit();
		
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	}
	
	}
	public void test1(){
		
		
			
			SecondLevelType secondLevelType = new SecondLevelType();
			secondLevelType.setTypenName("nanzhuang");
			secondLevelType.setLevel(2);
			
			FirstLevelType first1 = new FirstLevelType();
			first1.setTypenName("shangyi");
			first1.setLevel(1);
			
			FirstLevelType first2 = new FirstLevelType();
			first2.setTypenName("maozi");
			first2.setLevel(1);
			
			FirstLevelType first3 = new FirstLevelType();
			first3.setTypenName("kuzi");
			first3.setLevel(1);
			
			
			Product p1 = new Product();
			p1.setProductName("kuzi_1");
			Product p2 = new Product();
			p2.setProductName("kuzi_2");
			Product p3 = new Product();
			p3.setProductName("kuzi_3");
			Product p4 = new Product();
			p4.setProductName("kuzi_4");
			Product p5 = new Product();
			p5.setProductName("kuzi_5");
			
			
			
			
		
			Product p6 = new Product();
			p6.setProductName("maozi_1");
			Product p7 = new Product();
			p7.setProductName("maozi_2");
			Product p8 = new Product();
			p8.setProductName("maozi_3");
			Product p9 = new Product();
			p9.setProductName("maozi_4");
			Product p10 = new Product();
			p10.setProductName("maozi_5");
			
			
			
			
			Product p11 = new Product();
			p11.setProductName("shangyi_1");
			Product p12 = new Product();
			p12.setProductName("shangyi_2");
			Product p13 = new Product();
			p13.setProductName("shangyi_3");
			Product p14 = new Product();
			p14.setProductName("shangyi_4");
			Product p15 = new Product();
			p15.setProductName("shangyi_5");
			
			
			secondLevelType.getFirstLevelTypes().add(first3);
			secondLevelType.getFirstLevelTypes().add(first2);
			secondLevelType.getFirstLevelTypes().add(first1);
			
			
			first1.getProducts().add(p11);
			first1.getProducts().add(p12);
			first1.getProducts().add(p13);
			first1.getProducts().add(p14);
			first1.getProducts().add(p15);
			
			
			first2.getProducts().add(p6);
			first2.getProducts().add(p7);
			first2.getProducts().add(p8);
			first2.getProducts().add(p9);
			first2.getProducts().add(p10);
			
			
			first3.getProducts().add(p1);
			first3.getProducts().add(p2);
			first3.getProducts().add(p3);
			first3.getProducts().add(p4);
			first3.getProducts().add(p5);
			
			BeanFactory factory = new ClassPathXmlApplicationContext("app*.xml");
			ProductService service = (ProductService)factory.getBean("productService");
			service.saveProduct(p15);
			
	}
	
	
	
	public void test2(){
		
		Session session = getsSession();
		try {
			session.beginTransaction();
			SecondLevelType type = (SecondLevelType)session.get(SecondLevelType.class, 1);
			String str = "from Product p where p.type.secondLevelType=:type";
			
			Query q = session.createQuery(str);
			q.setEntity("type", type);
			q.setFirstResult(0);
			q.setMaxResults(8);
			List<Product> p = (List<Product>)q.list();
			for(Product product : p)
			{
				System.out.println(product.getProductName());
			}
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
	
	public void test3(){
		User user = new User();
		user.setAddr("°¡ÄãºÃ");
		user.setTel("15371334578");
		user.setUsername("admin");
		user.setPostcode(225400);
		BeanFactory factory = new ClassPathXmlApplicationContext("app*.xml");
		UserService us= (UserService)factory.getBean("userService");
		
		
	}
	
	public void test5()
	{
		
				
		
	    
		SecondLevelType s1 = new SecondLevelType();
		s1.setLevel(2);
		s1.setTypenName("second_1");
		
		SecondLevelType s2 = new SecondLevelType();
		s2.setLevel(2);
		s2.setTypenName("second_2");
		
		
		FirstLevelType f1 = new FirstLevelType();
		f1.setLevel(1);
		f1.setTypenName("firsr_1");
		
		FirstLevelType f2 = new FirstLevelType();
		f2.setLevel(1);
		f2.setTypenName("firsr_2");
		
		FirstLevelType f3 = new FirstLevelType();
		f3.setLevel(1);
		f3.setTypenName("firsr_3");
		
		FirstLevelType f4 = new FirstLevelType();
		f4.setLevel(1);
		f4.setTypenName("firsr_4");
		
		f1.setSecondLevelType(s1);
		f2.setSecondLevelType(s1);
		f3.setSecondLevelType(s2);
		f4.setSecondLevelType(s2);
		
		
		
		Set<FirstLevelType>  set2 = new HashSet<FirstLevelType>();
		set2.add(f1);
		set2.add(f2);
		s1.setFirstLevelTypes(set2);
		
		
		Set<FirstLevelType>  set3 = new HashSet<FirstLevelType>();
		set3.add(f4);
		set3.add(f3);
		s2.setFirstLevelTypes(set3);
		
		Session session = getsSession();
		
		try {
			session.beginTransaction();
			session.save(s2);
			session.save(s1);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		

		
	}
	
}