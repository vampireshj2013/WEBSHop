package com.shop.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.Utils.JspPrinter;
import com.shop.Utils.ObjectJsonValueProcessor;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven {
	private User user = null;
	private User currentUser = null;
	private String msg = null;
	@Resource(name = "indexAction")
	private IndexAction indexAction = null;
	@Resource(name = "userService")
	private UserService userService= null;
	private ArrayList<User> users = new ArrayList<User>();
	private String ids = null;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public User getUser(){
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String login()
	{
		return "login";
	}
	
	public void  check() throws IOException
	{	
		
		currentUser = userService.login(user);
		HashMap retMap = new HashMap<String, Object>();
		
			if(currentUser == null)
			{
				retMap.put("success", false);
			}
			else {
				retMap.put("success", true);
				ActionContext.getContext().getSession().put("currentUser", currentUser);
			}
		JSONObject json = JSONObject.fromObject(retMap);
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
	}
	public void checkForCart() throws Exception{
		String s = new String(user.getUsername().getBytes("ISO8859-1"),"utf-8");
		user.setUsername(s);
		currentUser = userService.login(user);
		HashMap retMap = new HashMap<String, Object>();
		
			if(currentUser == null)
			{
				retMap.put("success", false);
			}
			else {
				retMap.put("success", true);
				ActionContext.getContext().getSession().put("currentUser", currentUser);
			}
		JSONObject json = JSONObject.fromObject(retMap);
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
	}
	public String register()
	{
		
		return "register";
	}
	public void checkRegister() throws IOException{
		HashMap retMap = new HashMap<String, Object>();
		
		if(userService.hasUser(user))
		{
			retMap.put("success", false);
		}
		else {
			retMap.put("success", true);
		}
	JSONObject json = JSONObject.fromObject(retMap);
	ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
	ServletActionContext.getResponse().getWriter().write(json.toString());
	}
	public String registerAfter()
	{
			userService.register(user);
			msg="¹§Ï²Äú£¬×¢²á³É¹¦£¡";
			return "registersuccess";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public Object getModel() {
		user = new User();
		return user;
	}
	public String usermgInit()
	{
		return "usermg";
	}
	public void usermg() throws IOException
	{
		users = (ArrayList<User>) userService.findAllUser(user);
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("total", users.size());
		retMap.put("rows", users);
		JsonConfig jsconfig = new JsonConfig();
		jsconfig.setExcludes(new String[]{"shopCarts","indents"});
		JSONObject json = JSONObject.fromObject(retMap,jsconfig);
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
	}
	public void delUsers(){
		 String  id[] = ids.split(",");
		 for(String i : id)
		 {
			 userService.delUsers(Integer.parseInt(i));
		 }
	}
	

}
