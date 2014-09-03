package com.shop.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

public class BaseDao {
	@Resource
	private SessionFactory sessionFactory;
	public  Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
}
