package com.shop.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shop.daoInf.IndentDaoInf;
import com.shop.model.Indent;
import com.shop.model.User;
@Repository("indentDao")
public class IndentDao extends BaseDao implements IndentDaoInf {

	@Override
	public void deleteIndentById(int id) {
		Indent i = (Indent)getSession().get(Indent.class,id);
		getSession().delete(i);
		
	}

	@Override
	public void saveIndent(Indent indent) {
		getSession().save(indent);
		
	}

	@Override
	public List<Indent> findAddIndents() {
			String s = "from Indent";
			List<Indent> indents = getSession().createQuery(s).list();
			if(indents.size()>0)
			{
				return indents;
			}
			return null;
	}
	public List<Indent> findAddIndents(User user) {
		String s = "from Indent i where i.user = :user";
		
		List<Indent> indents = getSession().createQuery(s).setEntity("user", user).list();
		if(indents.size()>0)
		{
			return indents;
		}
		return null;
}
	@Override
	public void changeIndentForUser(int indentId) {
		Indent indent = (Indent)getSession().get(Indent.class, indentId);
		indent.setMessage(indent.getMessage()+1);
		getSession().update(indent);
		
	}

	@Override
	public void changeIndentForAdmin(int indentId) {
		Indent indent = (Indent)getSession().get(Indent.class, indentId);
		indent.setMessage(indent.getMessage()+1);
		getSession().update(indent);
		
		
	}

	@Override
	public List<Indent> findIndentByUsername(String username) {
		Query query = getSession().createQuery("from Indent i where i.user.username=:username");
		query.setParameter("username", username);
		
		List<Indent> indents = query.list();
		if(indents.size()>0)
		{
			return indents;
		}
		return null;
	}

	@Override
	public List<Indent> findAddIndents(Indent indent,int first,int max) {
		String hql = "from Indent  i where  1=1  ";
    	if(indent != null&&indent.getIndentId()!=0)
    		{
    			hql= hql+ " and i.indentId = '"+indent.getIndentId()+"'";
    			
    		}
    	if(indent.getUser()!= null&&indent.getUser().getUsername()!=null&&!"".equals(indent.getUser().getUsername()))
    	{
    		hql= hql+ "  and i.user.username like '%"+indent.getUser().getUsername()+"%'";
    	}
    	
    	if(indent.getProduct()!=null&&indent.getProduct().getProductName()!=null&&!"".equals(indent.getProduct().getProductName()))
    	{
    		hql= hql+ " and i.product.productName = '"+indent.getProduct().getProductName()+"'";
    	}
		Query  q = getSession().createQuery(hql);
		q.setFirstResult(first);
		q.setMaxResults(max);
		List<Indent> p  = q.list();
		if(p.size()>0)
		{
			return p;
		}
		return null;
	}

	@Override
	public int getSize(Indent indent) {
		String hql = "from Indent  i where  1=1  ";
    	if(indent != null&&indent.getIndentId()!=0)
    		{
    			hql= hql+ " and i.indentId = '"+indent.getIndentId()+"'";
    			
    		}
    	if(indent.getUser()!= null&&indent.getUser().getUsername()!=null&&!"".equals(indent.getUser().getUsername()))
    	{
    		hql= hql+ "  and i.user.username like '%"+indent.getUser().getUsername()+"%'";
    	}
    	
    	if(indent.getProduct()!=null&&indent.getProduct().getProductName()!=null&&!"".equals(indent.getProduct().getProductName()))
    	{
    		hql= hql+ " and i.product.productName = '"+indent.getProduct().getProductName()+"'";
    	}
		Query  q = getSession().createQuery(hql);
		List<Indent> p  = q.list();
		return p.size();
	}

	@Override
	public void onePlus(int id) {
		Indent indent = (Indent)getSession().get(Indent.class, id);
		if(indent.getMessage() ==1)
		{
			indent.setMessage(2);
			getSession().update(indent);
		}
		else if(indent.getMessage() ==2)
		{
			indent.setMessage(3);
			getSession().update(indent);
		}
		else{
			throw new RuntimeException("MESSAGE不为正常值");
		}
		
		
	}

	@Override
	public List<Indent> findAllIndentsByUser(User user, int first, int max) {
		Query query = getSession().createQuery("from Indent i where i.user=:user");
		query.setEntity("user", user);
		query.setFirstResult(first);
		query.setMaxResults(max);
		List<Indent> indents = query.list();
		if(indents.size()>0)
		{
			return indents;
		}
		return null;
	}

	@Override
	public Indent findIndentById(int id) {
		return (Indent)getSession().get(Indent.class, id);
	}

}
