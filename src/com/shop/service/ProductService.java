package com.shop.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import sun.net.www.content.text.plain;

import com.shop.dao.ProductDao;
import com.shop.daoInf.ProductDaoInf;
import com.shop.model.Product;
@Service("productService")
public class ProductService {
	@Resource
	private ProductDaoInf productDao;
	public List<Product> findProductsByType(int level ,int id , int firstResult , int maxResult){
		return productDao.findProductsByType(level, id, firstResult, maxResult);
	}
	
	public List<Product> findProductsByKey(String search,int firstResult,int maxResult)
	{
		return productDao.findProductsByKey(search, firstResult, maxResult);
		
	}
	public List<Product>  findProByTypeOrderByPriceAsc(int level,int  id,int  firstResult, int maxResult)
	{
		return productDao.findProByTypeOrderByPriceAsc(level, id, firstResult, maxResult);
	}
	public List<Product>  findProByTypeOrderByPriceDesc(int level,int  id,int  firstResult, int maxResult)
	{
		return productDao.findProByTypeOrderByPriceDesc(level, id, firstResult, maxResult);
	}
	
	public List<Product>  findProByTypeOrderByScansAsc(int level,int  id,int  firstResult, int maxResult)
	{
		return productDao.findProByTypeOrderByScansAsc(level, id, firstResult, maxResult);
	}
	
	public List<Product>  findProByTypeOrderByScansDesc(int level,int  id,int  firstResult, int maxResult)
	{
		return productDao.findProByTypeOrderByScansDesc(level, id, firstResult, maxResult);
	}
	
	public List<Product> findProByKeyOrderByPriceDesc(String search,int firstResult,int maxResult)
	{
		return productDao.findProByKeyOrderByPriceDesc(search, firstResult, maxResult);
		
	}
	
	public List<Product> findProByKeyOrderByPriceAsc(String search,int firstResult,int maxResult)
	{
		return productDao.findProByKeyOrderByPriceAsc(search, firstResult, maxResult);
		
	}
	
	public List<Product> findProByKeyOrderByScansDesc(String search,int firstResult,int maxResult)
	{
		return productDao.findProByKeyOrderByScansDesc(search, firstResult, maxResult);
		
	}
	
	public List<Product> findProByKeyOrderByScansAsc(String search,int firstResult,int maxResult)
	{
		return productDao.findProByKeyOrderByScansAsc(search, firstResult, maxResult);
		
	}
	
	
	public void changeProduct(Product product){
		productDao.changeProduct(product);
	}
	
	public void deleteProduct(int id)
	{
		productDao.deleteProduct(id);
	}
	public void saveProduct(Product product)
	{
		productDao.saveProduct(product);
	}
	
	
	public List<Product> findProByScans(){
		return productDao.findProByScans();
	}
	public List<Product> findProByIndent(){
		return productDao.findProByIndent();
	}
	
	public Product findProById(int id )
	{
		return productDao.findProById(id);
	}
	public List<Product>  findProByTypeAndKey(int typeId, int level,String key,int firstResult, int maxResult)
	{
		return productDao.findProByTypeAndKey(typeId, level, key, firstResult, maxResult);
	}
	public List<Product> findAllProducts(Product product,int firstResult,int maxResult)
	{
		return productDao.findAllProducts(product,firstResult, maxResult);
	}
	public int  getSize(Product product){
		return productDao.getSize(product);
	}
	
	public List<Product> findProByCarr() {
		return productDao.findProByCarr();
	}
	public List<Product> findProByTypeAndKeyOrderByPriceAes(int typeId, int level,String key,int firstResult, int maxResult){
		return productDao.findProByTypeAndKeyOrderByPriceAes( typeId,  level, key, firstResult,  maxResult);
	}
	public List<Product> findProByTypeAndKeyOrderBypriceDesc(int typeId, int level,String key,int firstResult, int maxResult)
	{
		return productDao.findProByTypeAndKeyOrderBypriceDesc( typeId,  level, key, firstResult,  maxResult);
	}
	public List<Product> findProByTypeAndKeyOrderByScansAes(int typeId,  int level,String key,int firstResult, int maxResult)
	{
		return productDao.findProByTypeAndKeyOrderByScansAes(typeId, level, key, firstResult, maxResult);
	}
	public List<Product> findProByTypeAndKeyOrderByScansDesc(int typeId, int level,String key,int firstResult, int maxResult){
		return productDao.findProByTypeAndKeyOrderByScansDesc(typeId, level, key, firstResult, maxResult);
	}

}
