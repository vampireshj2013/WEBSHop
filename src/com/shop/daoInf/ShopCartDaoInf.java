package com.shop.daoInf;

import java.util.List;

import com.shop.model.Product;
import com.shop.model.ShopCart;
import com.shop.model.User;

public interface ShopCartDaoInf {
		void saveShopCart(ShopCart cart);
		List <ShopCart> findAllCart(User user ,int firstResult,int maxResult);
		void delCart(int id);
		void update(ShopCart cart);
		ShopCart findCartById(int id);
		ShopCart findCartByUP(User user,Product product);
		int  findAllCartSize(User user);
}
