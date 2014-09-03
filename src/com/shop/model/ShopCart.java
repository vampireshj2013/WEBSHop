package com.shop.model;

public class ShopCart {
	private int shopCartId ;
	private Product product;
	private User user;
	private int count=1;
	private double totalPrice;
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getShopCartId() {
		return shopCartId;
	}
	public void setShopCartId(int shopCartId) {
		this.shopCartId = shopCartId;
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

}
