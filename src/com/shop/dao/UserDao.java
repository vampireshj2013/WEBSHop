package com.shop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shop.daoInf.IndentDaoInf;
import com.shop.daoInf.UserDaoInf;
import com.shop.model.User;

@Repository("userDao")
public class UserDao  extends BaseDao implements UserDaoInf {

	@Override
	/**
	 * 锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷没锟斤拷锟斤拷没锟斤拷锟斤拷锟斤拷锟斤拷锟窖�拷没锟�
	 */
	public User findUser(User user) {
		String q = "from User user where user.username=:userName and user.password=:password";
		Query query = getSession().createQuery(q);
		query.setParameter("userName", user.getUsername());
		query.setParameter("password", user.getPassword());
		query.uniqueResult();
		List<User> users = (List<User>)query.list();
		if(users.size()>0)
		{
			return users.get(0);
		}
		return null;
		
	}

	@Override
	public User findUserByUsername(String username) {
		try{
			String q = "from User user where user.username=:username";
		
		Query query = getSession().createQuery(q);
		query.setParameter("username", username);
		
		query.uniqueResult();
		List<User> users = (List<User>)query.list();
		if(users.size()>0)
		{
			return users.get(0);
		}
		return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public void saveUser(User user){
		getSession().save(user);
		
		
	}
    public List<User> findAllUser(User user)
    {
    	
    		
    	String hql = "from User u where  1=1  ";
    	if(user.getUserId()!=0)
    		{
    			hql= hql+ " and u.userId = '"+user.getUserId()+"'";
    			
    		}
    	if(user.getUsername()!=null&&!"".equals(user.getUsername()))
    	{
    		hql= hql+ "  and u.username like '%"+user.getUsername()+"%'";
    	}
    	
    	if(user.getSex()!=null&&!"".equals(user.getSex()))
    	{
    		hql= hql+ " and u.sex = '"+user.getSex()+"'";
    	}
    	Query query = getSession().createQuery(hql);
    	return query.list();
		 
    }
	@Override
	public User findUserById(int userId) {
		
		return (User)getSession().get(User.class, userId);
	}

	@Override
	public void delUser(int id) {
		getSession().delete(getSession().get(User.class, id));
		
	}

	@Override
	public void updateUser(User user) {
		getSession().update(user);
		
	}

	@Override
	public User findUser(int id) {
		
		return (User)getSession().get(User.class, id);
	}

	
		
}
