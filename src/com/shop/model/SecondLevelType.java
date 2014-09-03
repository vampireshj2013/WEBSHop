package com.shop.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SecondLevelType {
	
	private int secondId;
	private String  typenName ;
	private int level ;
	private Set<FirstLevelType> firstLevelTypes;
	public int getSecondId() {
		return secondId;
	}
	public void setSecondId(int secondId) {
		this.secondId = secondId;
	}
	public String getTypenName() {
		return typenName;
	}
	public void setTypenName(String typenName) {
		this.typenName = typenName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Set<FirstLevelType> getFirstLevelTypes() {
		return firstLevelTypes;
	}
	public void setFirstLevelTypes(Set<FirstLevelType> firstLevelTypes) {
		this.firstLevelTypes =firstLevelTypes;
	}
	
	

}
