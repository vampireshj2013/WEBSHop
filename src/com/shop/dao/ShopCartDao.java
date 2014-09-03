package com.shop.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shop.daoInf.ShopCartDaoInf;
import com.shop.model.Product;
import com.shop.model.ShopCart;
import com.shop.model.User;
@Repository
public class ShopCartDao  extends BaseDao implements ShopCartDaoInf {
			@Override
			public void saveShopCart(ShopCart cart) {
				getSession().save(cart);
				
			}

			@Override
			public List<ShopCart> findAllCart(User user,int firstResult, int maxResult) {
				String hql = "from ShopCart t where t.user=:user";
				Query q = getSession().createQuery(hql);
				q.setEntity("user", user);
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
				List list = q.list();
				if(list.size()>0)
					return list;
			    return null;
			}

			@Override
			public void delCart(int id) {
				getSession().delete(getSession().get(ShopCart.class, id));
			}

			@Override
			public void update(ShopCart cart) {
				getSession().update(cart);
				
			}

			@Override
			public ShopCart findCartById(int id) {
				
				return (ShopCart)getSession().get(ShopCart.class, id);
			}

			@Override
			public ShopCart findCartByUP(User user, Product product) {
				String hql = "from ShopCart t where t.user =:user and t.product =:product";
				Query q = getSession().createQuery(hql);
				q.setEntity("user", user);
				q.setEntity("product", product);
				q.uniqueResult();
				List list = q.list();
				if(list.size()<=0) return null;
				else return (ShopCart)q.list().get(0);
			}

			@Override
			public int findAllCartSize(User user) {
				String hql = "from ShopCart t where t.user =:user";
				Query q = getSession().createQuery(hql);
				q.setEntity("user", user);
				List list = q.list();
				if(list.size()<=0) return 0;
				return list.size();
			}
}
