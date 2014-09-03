package com.shop.service;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.shop.dao.ProductDao;
import com.shop.dao.ShopCartDao;
import com.shop.dao.UserDao;
import com.shop.daoInf.ProductDaoInf;
import com.shop.daoInf.ShopCartDaoInf;
import com.shop.daoInf.UserDaoInf;
import com.shop.model.Product;
import com.shop.model.ShopCart;
import com.shop.model.User;

@Service("cartService")
public class ShopCartService {
	@Resource
	private ShopCartDaoInf shopCartDao;
	@Resource
	private ProductDaoInf productDao;
	@Resource
	private UserDaoInf userDao;
	public void addToCart(int userId,int productId)
	{	User user = userDao.findUserById(userId);
		Product product = productDao.findProductById(productId);
		ShopCart cart = shopCartDao.findCartByUP(user, product);
		if(cart!=null)
		{
			cart.setCount(cart.getCount()+1);
			cart.setTotalPrice(cart.getCount()*cart.getProduct().getPrice());
			shopCartDao.update(cart);
		}
		else{ShopCart sc = new ShopCart();
		sc.setProduct(product);
		sc.setUser(user);
		sc.setTotalPrice(sc.getCount()*sc.getProduct().getPrice());
		shopCartDao.saveShopCart(sc);}
	}
	public List <ShopCart> findAllCart(User user,int firstResult,int maxResult){
		return shopCartDao.findAllCart(user ,firstResult, maxResult);
	}
	
	public void update(ShopCart cart){ shopCartDao.update(cart);}
	public ShopCart findCartById(int id){return shopCartDao.findCartById(id);}
	public void delCart(int id){shopCartDao.delCart(id);}
	public int findAllCartSize(User user){return shopCartDao.findAllCartSize(user);}
}
