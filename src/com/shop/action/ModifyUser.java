package com.shop.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shop.Utils.JspPrinter;
import com.shop.model.User;
import com.shop.service.UserService;
@Controller("modifyUser")
@Scope("prototype")
public class ModifyUser extends ActionSupport {
	private User user;
	@Resource
	private UserService userService;
	public String modifyInit()
	{	User user1 = (User)ActionContext.getContext().getSession().get("currentUser");
		user = userService.findUser(user1.getUserId()); 
		return "modify";
	}
	public void  modify() throws Exception
	{
		User user1= (User)ActionContext.getContext().getSession().get("currentUser");
		User currentUser = userService.findUser(user1.getUserId());
		if(!user.getUsername().equals(currentUser.getUsername()))
		{
			currentUser.setUsername(user.getUsername());
		}
		if(!user.getAddr().equals(currentUser.getAddr()))
		{
				currentUser.setAddr(user.getAddr());
		}
		
		 if(user.getAge()!=currentUser.getAge())
		{
			currentUser.setAge(user.getAge());
		}
		 
		 if(user.getPostcode()!=currentUser.getPostcode())
		 {
			 currentUser.setPostcode(user.getPostcode());
		 }
		 if(!user.getSex().equals(currentUser.getSex()))
		 {
			 currentUser.setSex(user.getSex());
		 }
		 if(!user.getTel().equals(currentUser.getTel()))
		 {
			 currentUser.setTel(user.getTel());
		 }
		 userService.updateUser(currentUser);
		 JspPrinter.print(true);
	}
	public void modifyPass() throws Exception
	{	User user1= (User)ActionContext.getContext().getSession().get("currentUser");
		User currentUser = userService.findUser(user1.getUserId());
		if (!user.getPassword().equals(currentUser.getPassword()))
		 {
			 currentUser.setPassword(user.getPassword());
		 }
		userService.updateUser(currentUser);
		 JspPrinter.print(true);
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
