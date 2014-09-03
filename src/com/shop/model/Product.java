package com.shop.model;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Product implements Cloneable{
			private int productId = 0;
			private String productName = null;
			private String simplyIntro = null;//简述
			private double price;
			private int scans;//点击量
			private File picture =null;
			private byte[] pictureArray = null;
			private String pictureFileName = null;
			private String pictureContentType = null;
			
			private Set<Indent> indents = new HashSet<Indent>();
			private FirstLevelType type = new FirstLevelType();
			/**
			 * 新增属性
			 */
			private String materia = null;//材质
			private int  remain ;//剩余量
			private String carrDate = null;//上架时间
			private String manuDate = null;//生产日期
			private String size = null;//尺寸
			private String pack = null;//包装
			private String craft = null;//工艺（手工，半手工等）
			private String brand = null;//品牌
			private String proAddr = null;//产地

			private String logo = null;//加印LOGO
			private String faceCraft = null;//表面工艺：（镀彩，喷绘，彩绘等）
			private int totals = 0;
			
			
			
			
			
			
			
			
			
			

			public int getTotals() {
				return totals;
			}
			public void setTotals(int totals) {
				this.totals = totals;
			}

			public void setCarrDate(String carrDate) {
				this.carrDate = carrDate;
			}
			public void setManuDate(String manuDate) {
				this.manuDate = manuDate;
			}

			
			public String getMateria() {
				return materia;
			}
			public void setMateria(String materia) {
				this.materia = materia;
			}
			public int getRemain() {
				return remain;
			}
			public void setRemain(int remain) {
				this.remain = remain;
			}
			
			public String getSize() {
				return size;
			}
			public void setSize(String size) {
				this.size = size;
			}
			public String getPack() {
				return pack;
			}
			public void setPack(String pack) {
				this.pack = pack;
			}
			public String getCraft() {
				return craft;
			}
			public void setCraft(String craft) {
				this.craft = craft;
			}
			public String getBrand() {
				return brand;
			}
			public void setBrand(String brand) {
				this.brand = brand;
			}
			public String getProAddr() {
				return proAddr;
			}
			public void setProAddr(String proAddr) {
				this.proAddr = proAddr;
			}
			public String getLogo() {
				return logo;
			}
			public void setLogo(String logo) {
				this.logo = logo;
			}
			public String getFaceCraft() {
				return faceCraft;
			}
			public void setFaceCraft(String faceCraft) {
				this.faceCraft = faceCraft;
			}
			
			private int indentSize;
			public int getProductId() {
				return productId;
			}
			public void setProductId(int productId) {
				this.productId = productId;
			}
			public String getProductName() {
				return productName;
			}
			public void setProductName(String productName) {
				this.productName = productName;
			}
			public String getSimplyIntro() {
				return simplyIntro;
			}
			public void setSimplyIntro(String simplyIntro) {
				this.simplyIntro = simplyIntro;
			}
			public double getPrice() {
				return price;
			}
			public void setPrice(double price) {
				this.price = price;
			}
			public int getScans() {
				return scans;
			}
			public void setScans(int scans) {
				this.scans = scans;
			}
			public File getPicture() {
				return picture;
			}
			public void setPicture(File picture) {
				this.picture = picture;
			}
			public byte[] getPictureArray() throws IOException {
				if(this.pictureArray == null&&this.picture!=null)
					return FileUtils.readFileToByteArray(this.picture);
				return this.pictureArray;
			}
			public void setPictureArray(byte[] pictureArray) {
				this.pictureArray = pictureArray;
			}
			public String getPictureFileName() {
				return this.pictureFileName;
			}
			public String getCarrDate() {
				return carrDate;
			}
			public String getManuDate() {
				return manuDate;
			}
			public void setPictureFileName(String pictureFileName) {
				this.pictureFileName = pictureFileName;
			}
			public String getPictureContentType() {
				return pictureContentType;
			}
			public void setPictureContentType(String pictureContentType) {
				this.pictureContentType = pictureContentType;
			}
			public Set<Indent> getIndents() {
				return indents;
			}
			public void setIndents(Set<Indent> indents) {
				this.indents = indents;
			}
			public FirstLevelType getType() {
				return type;
			}
			public void setType(FirstLevelType type) {
				this.type = type;
			}
			public int getIndentSize() {
				return indentSize;
			}
			public void setIndentSize(int indentSize) {
				this.indentSize = indentSize;
			}
			@Override
			public Object clone() throws CloneNotSupportedException {
				 Object o=null;   
			        try   
			         {   
			         o=(Product)super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。   
			         }   
			        catch(CloneNotSupportedException e)   
			         {   
			             System.out.println(e.toString());   
			         }   
			        return o;
			}
	     
}
