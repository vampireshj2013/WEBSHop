package com.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.IndentDao;
import com.shop.daoInf.IndentDaoInf;
import com.shop.model.Indent;
import com.shop.model.ShopCart;
import com.shop.model.User;
@Service("indentService")
public class IndentService {
	@Resource(name = "indentDao")
	private IndentDaoInf  indentDao;
	/**
	 * 通过购物车购买所有商品
	 * @param user
	 */
	public void buy(User user){
		Set<ShopCart> carts = user.getShopCarts();
		for(ShopCart c : carts)
		{
			Indent indent = new Indent();
			indent.setProduct(c.getProduct());
			indent.setUser(user);
			indentDao.saveIndent(indent);
		}
	}
	/**
	 * 直接购买
	 * @param indent
	 */
	public void buy(Indent indent)
	{
		indentDao.saveIndent(indent);
	}
	public void deleteIndentById(int indentId)
	{
		indentDao.deleteIndentById(indentId);
	}
	public List<Indent> findAllIndents(User user)
	{
		return indentDao.findAddIndents(user);
	}
	/**
	 * 在jsp页面上控制按钮是否可用
	 * @param indentId
	 */
	public void  changeIndentForUser(int indentId){indentDao.changeIndentForUser(indentId);}
	public void changeIndentForAdmin(int indentId){indentDao.changeIndentForAdmin(indentId);}
	public List<Indent> findIndentByUsername(String username){return indentDao.findIndentByUsername(username);}
	public List<Indent> findAllIndentsByUser(User user,int firstResult,int maxResult){
		
		
		return indentDao.findAllIndentsByUser( user, firstResult, maxResult);
		}
	public List<Indent> findAddIndents(Indent indent,int first,int max) {
		return indentDao.findAddIndents(indent,first,max);
	}
	public int getSize(Indent indent)
	{
		return indentDao.getSize(indent);
	}
	public void onePlus(int id)
	{
		indentDao.onePlus(id);
	}
	public void saveIndent(Indent indent){
		indentDao.saveIndent(indent);
	}
	public Indent findIndentById(int id )
	{
		return indentDao.findIndentById(id);
				
	}
	
}
