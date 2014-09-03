package com.shop.model;

import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class FirstLevelType implements Comparable{
			private int firstId;
			private String  typenName ;
			private int level ;
			private Set<Product> products = new HashSet<Product>();
			private SecondLevelType secondLevelType = new SecondLevelType();
			
			public SecondLevelType getSecondLevelType() {
				return secondLevelType;
			}
			public void setSecondLevelType(SecondLevelType secondLevelType) {
				this.secondLevelType = secondLevelType;
			}
			public int getFirstId() {
				return firstId;
			}
			public void setFirstId(int firstId) {
				this.firstId = firstId;
			}
			public String getTypenName() {
				return typenName;
			}
			public int getLevel() {
				return level;
			}
			
			public void setTypenName(String typenName) {
				this.typenName = typenName;
			}
			public void setLevel(int level) {
				this.level = level;
			}
			public void setProducts(Set<Product> products) {
				this.products = products;
			}
			public Set<Product> getProducts() {
				return products;
			}
			@Override
			public int compareTo(Object o) {
				FirstLevelType tt = (FirstLevelType)o;
				return tt.getFirstId()-this.firstId;
			}
			
			
}
