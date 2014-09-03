package com.shop.daoInf;

import java.util.List;

import com.shop.model.User;

public interface UserDaoInf {
	
		 User findUser(User user);
		User findUserByUsername(String username);
		void saveUser(User user);
		User findUserById(int userId);
		public List<User> findAllUser(User user);
		public void delUser(int id);
		public void updateUser(User user);
		public User findUser(int id );
}
