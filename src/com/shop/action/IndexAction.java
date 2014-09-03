package com.shop.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.dao.ProductDao;
import com.shop.daoInf.TypeDaoInf;
import com.shop.model.Product;
import com.shop.model.SecondLevelType;
import com.shop.model.User;
import com.shop.service.ProductService;
import com.shop.service.TypeService;
@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport implements ModelDriven{
	private List<SecondLevelType> seconds;
	@Resource(name =  "typeService")
	private TypeService typeService;
	@Resource
	private ProductService proService;
	private List<Product> p_i;//按成交量排名的商品集合
	private List<Product> p_s;//按浏览量排名的商品集合
	private User user = null;
	public List<Product> getP_i() {
		return p_i;
	}
	public void setP_i(List<Product> p_i) {
		this.p_i = p_i;
	}
	public List<Product> getP_s() {
		return p_s;
	}
	public void setP_s(List<Product> p_s) {
		this.p_s = p_s;
	}
	public List<Product> getP_c() {
		return p_c;
	}
	public void setP_c(List<Product> p_c) {
		this.p_c = p_c;
	}

	private List<Product> p_c;//按上架时间排名的商品集合
	public List<SecondLevelType> getSeconds() {
		return seconds;
	}
	public void setSeconds(List<SecondLevelType> seconds) {
		this.seconds = seconds;
	}
	
	public String execute()
	{	
		seconds = typeService.findAllTypes();
		p_i = proService.findProByIndent();
		p_s = proService.findProByScans();
		p_c = proService.findProByCarr();
		return "index";
	}
	
	public String loginAfter(){
		execute();
		return "index";
	
}
public void exit() throws IOException{
	ActionContext.getContext().getSession().clear();
	HashMap<String, Object> retMap = new HashMap<String, Object>();
	retMap.put("success", true);
	JSONObject json = JSONObject.fromObject(retMap);
	ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
	ServletActionContext.getResponse().getWriter().write(json.toString());
}
@Override
public Object getModel() {
	user = new User();
	return user;
}

}
