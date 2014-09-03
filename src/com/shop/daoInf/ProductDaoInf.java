package com.shop.daoInf;

import java.util.List;

import com.shop.model.Product;

public interface ProductDaoInf {
				List<Product> findAllProducts(Product product,int firstResult,int maxResult);
				Product findProductById(int id);
				List<Product> findProByTypeAndKey(int typeId, int level,String key,int firstResult, int maxResult);
				List<Product> findProByTypeAndKeyOrderByPriceAes(int typeId, int level,String key,int firstResult, int maxResult);
				List<Product> findProByTypeAndKeyOrderBypriceDesc(int typeId, int level,String key,int firstResult, int maxResult);
				List<Product> findProByTypeAndKeyOrderByScansAes(int typeId, int level,String key,int firstResult, int maxResult);
				List<Product> findProByTypeAndKeyOrderByScansDesc(int typeId, int level,String key,int firstResult, int maxResult);
				List<Product> findProductsByType(int level,int id,int firstResult,int maxResult);
				List<Product> findProductsByKey(String search,int firstResult,int maxResult);
				
				List<Product> findProByTypeOrderByPriceDesc(int level,int id,int firstResult,int maxResult);
				List<Product> findProByTypeOrderByPriceAsc(int level,int id,int firstResult,int maxResult);
				
				List<Product> findProByTypeOrderByScansDesc(int level,int id,int firstResult,int maxResult);	
				List<Product> findProByTypeOrderByScansAsc(int level,int id,int firstResult,int maxResult);

				List<Product> findProByKeyOrderByPriceDesc(String search,int firstResult,int maxResult);
				List<Product> findProByKeyOrderByPriceAsc(String search,int firstResult,int maxResult);

				List<Product> findProByKeyOrderByScansDesc(String search,int firstResult,int maxResult);
				List<Product> findProByKeyOrderByScansAsc(String search,int firstResult,int maxResult);
				
				void saveProduct(Product product );
				void changeProduct(Product product);
				void deleteProduct(int id);
				
				List<Product> findProByScans();
				List<Product> findProByIndent();
				List<Product> findProByCarr();
				Product findProById(int id );
				public int  getSize(Product product);
				
}
