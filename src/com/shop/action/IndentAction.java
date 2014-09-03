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
import com.shop.Utils.JspPrinter;
import com.shop.Utils.ObjectJsonValueProcessor;
import com.shop.Utils.PageUtil;
import com.shop.model.Indent;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.IndentService;
import com.shop.service.ProductService;
import com.shop.service.TypeService;

@Controller("indentAction")
@Scope("prototype")
public class IndentAction extends ActionSupport {
	@Resource(name = "typeService")
	private TypeService typeService;
	@Resource(name = "productService")
	private ProductService proService;
	@Resource(name = "indentService")
	private IndentService indentSrv;
	private List<Product> proFromIndent;
	private List<Product> proFromScans;
	private int page;
	private int rows;
	private Indent indent = new Indent();
	private String ids= null;
	private int productId;
	private User user;
	private List<Indent> indents;
	private PageUtil pageUtil = new PageUtil();
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	public List<Indent> getIndents() {
		return indents;
	}

	public void setIndents(List<Indent> indents) {
		this.indents = indents;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Indent getIndent() {
		return indent;
	}

	public void setIndent(Indent indent) {
		this.indent = indent;
	}

	

	public  String indentInit()
	{
		return "indentInit";
	}
	public void  findAllIndent() throws Exception
	{	
		List<Indent> list = indentSrv.findAddIndents(indent,(page-1)*rows, rows);
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("total", indentSrv.getSize(indent));
		retMap.put("rows", list);
		JsonConfig jsconfig = new JsonConfig();
		jsconfig.registerJsonValueProcessor(User.class,   
			       new ObjectJsonValueProcessor(new String[]{"username","userId"},User.class));
		jsconfig.registerJsonValueProcessor(Product.class,   
			       new ObjectJsonValueProcessor(new String[]{"productName","productId"},Product.class));
		JSONObject json = JSONObject.fromObject(retMap,jsconfig);
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
	}
	
	public void onePlus()
	{
		String  id[] = ids.split(",");
		 for(String i : id)
		 {
			 indentSrv.onePlus(Integer.parseInt(i));
		 }
	}
	public void foreOnePlus()
	{
		indentSrv.onePlus(id);
	}
	public void isLogin() throws Exception{
		User user = (User)ActionContext.getContext().getSession().get("currentUser");
		if(user==null)
		{
			JspPrinter.print(false);
		}
		else JspPrinter.print(true);
	}
	public String detailaddr() throws Exception
	{	
		return "detailaddr";
	}
	public String buy() throws Exception
	{	
		Product product = proService.findProById(productId);
		User currentUser = (User)ActionContext.getContext().getSession().get("currentUser");
		if(product.getRemain()<=0)
		 {	ServletActionContext.getResponse().setCharacterEncoding("GBK");
			ServletActionContext.getResponse().getWriter().print("<script>alert('±ß«∏£¨√ª”– £”‡ø‚¥Ê£°');window.location.href='indentAction_detailaddr.action?productId="+productId+"'</script>");
			return null;
			}
		else{
			
			product.setRemain(product.getRemain()-1);
			proService.changeProduct(product);
		Indent indent = new Indent();
		if(user!=null)
		{
			indent.setAddr(user.getAddr());
			indent.setPostcode(user.getPostcode());
			indent.setTel(user.getTel());
			
		}
		indent.setUser(currentUser);
		indent.setProduct(product);
		indent.setTotalPrice(indent.getCount()*indent.getProduct().getPrice());
		indentSrv.saveIndent(indent);
		indents = indentSrv.findAllIndentsByUser(currentUser,pageUtil.getIndex(),pageUtil.getPageSize());
		if(pageUtil.getRecordCount()==0)pageUtil.setRecordCount(indentSrv.findAllIndents(currentUser).size());
		return "foreindent";}
		
	}
	public String foreIndentInit() throws Exception
	{	
		User user =(User)ActionContext.getContext().getSession().get("currentUser");
		if(user==null)  {ServletActionContext.getResponse().sendRedirect("index");return null;}
		else{indents = indentSrv.findAllIndentsByUser(user,pageUtil.getIndex(),pageUtil.getPageSize());
		List<Indent> ii = indentSrv.findAllIndents(user);
		int i = ii==null?0:ii.size();
		if(pageUtil.getRecordCount()==0)pageUtil.setRecordCount(i);
		return "foreindent";}
		
	}
	public void deleteIndent() throws Exception
	{
		String id[] = ids.split(",");
		for(String i :id)
		{
			indentSrv.deleteIndentById(Integer.parseInt(i));
		}
		JspPrinter.print(true);
	}
	}


