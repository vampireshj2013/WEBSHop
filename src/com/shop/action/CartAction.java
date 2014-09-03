package com.shop.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shop.Utils.JspPrinter;
import com.shop.Utils.PageUtil;
import com.shop.model.Indent;
import com.shop.model.SecondLevelType;
import com.shop.model.ShopCart;
import com.shop.model.User;
import com.shop.service.IndentService;
import com.shop.service.ShopCartService;
import com.shop.service.TypeService;
@Controller("cartAction")
@Scope("prototype")
public class CartAction extends ActionSupport {
	private List <ShopCart> carts = null;
	@Resource(name = "cartService")
	private ShopCartService cartService = null;
	private PageUtil pageUtil = new PageUtil();
	private List<SecondLevelType> seconds;
	@Resource(name ="typeService")
	private TypeService service;
	@Resource(name ="indentService")
	private IndentService indentService;
	private int cartId;
	private String ss;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<SecondLevelType> getSeconds() {
		return seconds;
	}

	public void setSeconds(List<SecondLevelType> seconds) {
		this.seconds = seconds;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	public List<ShopCart> getCarts() {
		return carts;
	}

	public void setCarts(List<ShopCart> carts) {
		this.carts = carts;
	}
	
	public String cartInit() throws Exception
	{	seconds = service.findAllTypes();
		User user = (User)ActionContext.getContext().getSession().get("currentUser");
		if(user == null)
			{ServletActionContext.getResponse().sendRedirect("index");return null;}
		else
		{	if(pageUtil.getRecordCount()==0)
			{
				pageUtil.setRecordCount(cartService.findAllCartSize(user));
			}
			carts = cartService.findAllCart(user,pageUtil.getIndex(), pageUtil.getPageSize());
			return "cart";
		}
	}
	public void plus()
	{
		ShopCart cart = cartService.findCartById(cartId);
		cart.setCount(cart.getCount()+1);
		cartService.update(cart);
	}
	public void min() throws Exception
	{
		ShopCart cart = cartService.findCartById(cartId);
		if(cart.getCount()<=0)
			JspPrinter.print(false);
		else{
			cart.setCount(cart.getCount()-1);
			JspPrinter.print(true);
		}
		cartService.update(cart);
		
	}
	public void delete() throws Exception
	{
		cartService.delCart(cartId);
		JspPrinter.print(true);
	}
	public String detailAddr(){
		return "detailaddr";
	}
	public void buy() throws Exception
	{
		String[] ids = ss.split(",");
		for(String cartId:ids){
		ShopCart cart = cartService.findCartById(Integer.parseInt(cartId));
			if(cart.getCount()>cart.getProduct().getRemain())
			{
				JspPrinter.print("overflow");
				return;
			}
		}
		for(String cartId:ids)
		{	
			ShopCart cart = cartService.findCartById(Integer.parseInt(cartId));
			Indent indent = new Indent();
			indent.setCount(cart.getCount());
			int remain= cart.getProduct().getRemain();
			cart.getProduct().setRemain(remain-cart.getCount());
			if(user!=null)
			{
					indent.setAddr(user.getAddr());
					indent.setPostcode(user.getPostcode());
					indent.setTel(user.getTel());
			}
			indent.setUser(cart.getUser());
			indent.setProduct(cart.getProduct());
			indent.setTotalPrice(cart.getTotalPrice());
			indentService.saveIndent(indent);
			cartService.delCart(Integer.parseInt(cartId));
		}
		JspPrinter.print(true);
	}
	public void getTotals() throws Exception
	{	double totals = 0.0;
		
		if(ss!=null&&!"".equals(ss))
		{
			String[] ids = ss.split(",");
			for(String cartId:ids)
			{
				ShopCart cart = cartService.findCartById(Integer.parseInt(cartId));
				totals +=cart.getCount()*cart.getProduct().getPrice();
			}
		}
		HashMap retMap = new HashMap<String, Object>();
		retMap.put("totals", totals);
		JSONObject json = JSONObject.fromObject(retMap);
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
	}
}
