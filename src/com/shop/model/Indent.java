package com.shop.model;

public class Indent {
	
	private int indentId = 0 ;
	private Product product;
	private User user;
	private String addr;
	private String tel;
	private int postcode;
	private int message = 1;// 1表示已付款 2表示已发货 3表示确认收货
	private int count=1;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	private double totalPrice;
	
	public int getIndentId() {
		return indentId;
	}
	public void setIndentId(int indentId) {
		this.indentId = indentId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	

}
