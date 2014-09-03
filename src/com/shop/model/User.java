package com.shop.model;

import java.util.Set;

public class User {
		private int userId = 0;
		private String username;
		private String password;
		private String addr;
		private String tel;
		private int postcode ;
		private String sex = null;
		private int age;
		private Set<Indent> indents;
		private Set<ShopCart> shopCarts;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
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
		public Set<Indent> getIndents() {
			return indents;
		}
		public void setIndents(Set<Indent> indents) {
			this.indents = indents;
		}
		public Set<ShopCart> getShopCarts() {
			return shopCarts;
		}
		public void setShopCarts(Set<ShopCart> shopCarts) {
			this.shopCarts = shopCarts;
		}
		
		
		
		
}
