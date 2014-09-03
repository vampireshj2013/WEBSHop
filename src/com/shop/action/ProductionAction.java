package com.shop.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.Utils.PageUtil;
import com.shop.model.FirstLevelType;
import com.shop.model.Product;
import com.shop.model.SecondLevelType;
import com.shop.model.ShopCart;
import com.shop.model.User;
import com.shop.service.ProductService;
import com.shop.service.ShopCartService;
import com.shop.service.TypeService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller("proAction")
@Scope("prototype")
public class ProductionAction extends ActionSupport{
		private List<SecondLevelType> seconds;
		@Resource(name = "productService")
		private ProductService proService;
		@Resource(name = "typeService")
		private TypeService typeService;
		private Product product = new Product();
		@Resource(name="cartService")
		private ShopCartService cartService;
		private int productId;
		private int typeId = 0;//查询时类别的Id,默认为0
		private String key = null;
		private List<FirstLevelType> relative =  new ArrayList<FirstLevelType>();
		private String ids = null;
		private int price = 1;
		private int scans = 0;
		private List<Product> ps = new ArrayList<Product>();
		private int page;
		private int rows;
		private User user;
		
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}

		//使用flag标记标识是用类别查询还是关键字查询
		private int flag = 0;
		private FirstLevelType firstType;
		
		public FirstLevelType getFirstType() {
			return firstType;
		}
		public void setFirstType(FirstLevelType firstType) {
			this.firstType = firstType;
		}
		public int getFlag() {
			return flag;
		}
		public void setFlag(int flag) {
			this.flag = flag;
		}
		public List<FirstLevelType> getRelative() {
			return relative;
		}
		public void setRelative(List<FirstLevelType> relative) {
			this.relative = relative;
		}

		private PageUtil pagef = new PageUtil();
		
		public List<SecondLevelType> getSeconds() {
			return seconds;
		}
		public void setSeconds(List<SecondLevelType> seconds) {
			this.seconds = seconds;
		}
		public PageUtil getPagef() {
			return pagef;
		}
		public void setPagef(PageUtil pagef) {
			this.pagef = pagef;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getScans() {
			return scans;
		}
		public void setScans(int scans) {
			this.scans = scans;
		}
		public String getIds() {
			return ids;
		}
		public void setIds(String ids) {
			this.ids = ids;
		}
		
		
		public List<Product> getPs() {
			return ps;
		}
		public void setPs(List<Product> ps) {
			this.ps = ps;
		}
		
		public int getTypeId() {
			return typeId;
		}
		public void setTypeId(int typeId) {
			this.typeId = typeId;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		
		public String add()
		{
			return "addproduct";
		}
		
		public int getRows() {
			return rows;
		}
		public void setRows(int rows) {
			this.rows = rows;
		}
		public void setPage(int page) {
			this.page = page;
		}
		
		public int getPage() {
			return page;
		}
		public void addPro()
		{
			try
			{
			int id  = product.getType().getFirstId();
			product.setType(typeService.findFirstById(id));
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			product.setCarrDate(format.format(date));
			product.setRemain(product.getTotals());
			File file = new File(ServletActionContext.getServletContext().getRealPath("upImage")+"/"+product.getPictureFileName());
			if(!file.exists())
				FileUtils.copyFile(product.getPicture(), file);
			proService.saveProduct(product);
			HashMap retMap = new HashMap<String, Object>();
			retMap.put("success", true);
			JSONObject json = JSONObject.fromObject(retMap);
			ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json.toString());
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public String detail()
		{
			product = proService.findProById(productId);
			return "detail";
		}
		public String searchRes() throws Exception
		{
			search();
			return "searchRes";
		}
		public String toSerchRes()
		{
			return "searchRes";
		}
		public String search() throws Exception{
			if(key!=null)key = new String(key.getBytes("ISO8859-1"),"utf-8");
			seconds = typeService.findAllTypes();
			/*使用关键字查询之后 又用类别查询*/
			if(typeId != 0&&key!=null&&!"".equals(key))
			{
				if(price ==1)
				{
					ps = proService.findProByTypeAndKeyOrderByPriceAes( typeId, 1, key,pagef.getIndex(), pagef.getPageSize());
				}
				if(price ==2 )
				{
					ps = proService.findProByTypeAndKeyOrderBypriceDesc( typeId, 1, key,pagef.getIndex(), pagef.getPageSize());
				}
				if(scans == 1)
				{
					ps = proService.findProByTypeAndKeyOrderByScansAes( typeId, 1, key,pagef.getIndex(), pagef.getPageSize());
				}
				if(scans ==2)
				{
					ps = proService.findProByTypeAndKeyOrderByScansDesc( typeId, 1, key,pagef.getIndex(), pagef.getPageSize());
				}
				
			}
			//使用关键字查询
			else if(key!=null&&!"".equals(key))
			{
				if(price ==1)
				{
					ps = proService.findProByKeyOrderByPriceAsc(key, pagef.getIndex(), pagef.getPageSize());
				}
				if(price ==2 )
				{
					ps = proService.findProByKeyOrderByPriceDesc(key, pagef.getIndex(), pagef.getPageSize());
				}
				if(scans == 1)
				{
					ps = proService.findProByKeyOrderByScansAsc(key, pagef.getIndex(), pagef.getPageSize());
				}
				if(scans ==2)
				{
					ps = proService.findProByKeyOrderByScansDesc(key, pagef.getIndex(), pagef.getPageSize());
				}
				
				TreeSet <FirstLevelType> set  = new TreeSet<FirstLevelType>();
				if(ps!=null)
				for(Product p :ps)
				{
					set.add(p.getType());
				}
				relative.addAll(set);
				if(ActionContext.getContext().getSession().get("relative")!=null)
				{
					ActionContext.getContext().getSession().remove("relative");
				}
				ActionContext.getContext().getSession().put("relative", relative);
			}
			//使用类别查询
			else if(typeId!=0)
			{	
				flag = 1; 
				if(price ==1)
				{
					ps = proService.findProByTypeOrderByPriceAsc(1, typeId, pagef.getIndex(), pagef.getPageSize());
				}
				if(price ==2 )
				{
					ps = proService.findProByTypeOrderByPriceDesc(1, typeId, pagef.getIndex(), pagef.getPageSize());
				}
				if(scans == 1)
				{
					ps = proService.findProByTypeOrderByScansAsc(1, typeId, pagef.getIndex(), pagef.getPageSize());
				}
				if(scans ==2)
				{
					ps = proService.findProByTypeOrderByScansDesc(1, typeId, pagef.getIndex(), pagef.getPageSize());
				}
				firstType = typeService.findFirstById(typeId);
			}
			ActionContext.getContext().getSession().put("ps", ps);
			return "search";
			
		}
		
		public String promgInit()
		{
			return "promg";
		}
		public void findAllPro()
		{	
			try
			{
			ps = proService.findAllProducts(product,(page-1)*rows, rows);
			HashMap<String, Object> retMap = new HashMap<String, Object>();
			retMap.put("total", proService.getSize(product));
			retMap.put("rows", ps);
			JsonConfig jsconfig = new JsonConfig();
		    jsconfig.setExcludes(new String[]{"type","indents","pictureArray","pictureContentType"});
			JSONObject json = JSONObject.fromObject(retMap,jsconfig);
			ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json.toString());}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		public String modifyInit(){
			product = proService.findProById(product.getProductId());
			return "modify";
		}
		
		public void modify() throws Exception
		{	
			product.setType(typeService.findFirstById(product.getType().getFirstId()));
			product.setPictureFileName(proService.findProById(product.getProductId()).getPictureFileName());
			product.setCarrDate(proService.findProById(product.getProductId()).getCarrDate());
			proService.changeProduct(product);
			HashMap retMap = new HashMap<String, Object>();
			retMap.put("success", true);
			JSONObject json = JSONObject.fromObject(retMap);
			ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json.toString());
			
			
			
		}
		
		public void delPro(){
			 String  id[] = ids.split(",");
			 for(String i : id)
			 {
				 proService.deleteProduct(Integer.parseInt(i));
			 }
		}
		public String proDetail(){
			product = proService.findProById(productId);
			product.setScans(product.getScans()+1);
			proService.changeProduct(product);
			return "prodetail";
		}
		
		public void addToshopcart() throws Exception{
			User user = (User)ActionContext.getContext().getSession().get("currentUser");
			HashMap retMap = new HashMap<String, Object>();
			if(user==null)
			{
				retMap.put("success", false);
			}
			else{
				
				cartService.addToCart(user.getUserId(), productId);
				retMap.put("success",true);
			}
			JSONObject json = JSONObject.fromObject(retMap);
			ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json.toString());
		}
}
