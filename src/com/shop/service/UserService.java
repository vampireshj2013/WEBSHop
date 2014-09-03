package com.shop.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.UserDao;
import com.shop.daoInf.UserDaoInf;
import com.shop.model.User;

@Service("userService")
public class UserService {
	@Resource
	private UserDaoInf userDao;
	public User login(User user){
		User currentUser = userDao.findUser(user);
		return currentUser;
		
	}
	
	public boolean  hasUser(User user)
	{
		if(userDao.findUserByUsername(user.getUsername())!=null)
			return true;
		
		return false;
	}
	public void register(User user)
	{
		userDao.saveUser(user);
	}
	public List<User> findAllUser(User user)
	{
		return userDao.findAllUser(user);
	}
	public void delUsers(int id){
		userDao.delUser(id);
	}
	public void updateUser(User user )
	{
		userDao.updateUser(user);
	}
	public User findUser(int id)
	{
		return userDao.findUser(id);
	}
}
