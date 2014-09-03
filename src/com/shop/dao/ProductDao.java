package com.shop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.shop.daoInf.IndentDaoInf;
import com.shop.daoInf.ProductDaoInf;
import com.shop.model.FirstLevelType;
import com.shop.model.Product;
import com.sun.accessibility.internal.resources.accessibility;
@Repository
public class ProductDao extends BaseDao implements ProductDaoInf  {

	@Override
	public Product findProductById(int id) {
		return (Product)getSession().get(Product.class, id);
		
	}
	
	@Override
	public List<Product> findProductsByType(int level, int id, int firstResult,
			int maxResult) {
		String str = null;
		
		if(level == 1)
			{str ="from Product p where p.type.firstId=:id";
			
			
		}
		else if(level == 2)
		{
			str ="from Product p where p.type.secondLevelType.secondId=:id";
		}
		else if(level == 3){
			str ="from Product p where p.type.secondLevelType.thirdLevelType.thirdId=:id";
		}
		
		Query q = getSession().createQuery(str);
		q.setParameter("id", id);
		q.setFirstResult(firstResult);
		q.setMaxResults(maxResult);
		List<Product> pList = (List<Product>) q.list();
		if(pList.size()>0)
		{
			return pList;
		}
		return null;
	}

	@Override
	public List<Product> findProductsByKey(String search, int firstResult,
			int maxResult) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.or(Restrictions.like("productName", search,MatchMode.ANYWHERE), 
										Restrictions.like("simplyIntro", search,MatchMode.ANYWHERE)));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		List<Product> pList = (List<Product>) criteria.list();
		if(pList.size()>0)
		{
			return pList;
		}
		return null;
	}

	@Override
	public List<Product> findProByTypeOrderByPriceDesc(int level, int id,
			int firstResult, int maxResult) {
		String str="";
		if(level == 1)
		{str ="from Product p where p.type.firstId=:id";
		
		
	}
	else if(level == 2)
	{
		str ="from Product p where p.type.secondLevelType.secondId=:id";
	}
	else if(level == 3){
		str ="from Product p where p.type.secondLevelType.thirdLevelType.thirdId=:id";
	}
	str+=" order by p.price  desc";
	Query q = getSession().createQuery(str);
	q.setParameter("id", id);
	q.setFirstResult(firstResult);
	q.setMaxResults(maxResult);
	
	List<Product> pList = (List<Product>) q.list();
	if(pList.size()>0)
	{
		return pList;
	}
	return null;
	}

	@Override
	public List<Product> findProByTypeOrderByScansDesc(int level, int id,
			int firstResult, int maxResult) {
		String str="";
		if(level == 1)
		{str ="from Product p where p.type.firstId=:id";
		
		
	}
	else if(level == 2)
	{
		str ="from Product p where p.type.secondLevelType.secondId=:id";
	}
	else if(level == 3){
		str ="from Product p where p.type.secondLevelType.thirdLevelType.thirdId=:id";
	}
	str+=" order by p.scans desc";
	Query q = getSession().createQuery(str);
	q.setParameter("id", id);
	q.setFirstResult(firstResult);
	q.setMaxResults(maxResult);
	List<Product> pList = (List<Product>) q.list();
	if(pList.size()>0)
	{
		return pList;
	}
	return null;
	}

	

	@Override
	public void saveProduct(Product product) {
		getSession().save(product);		
	}

	/* (non-Javadoc)
	 * @see com.shop.daoInf.ProductDaoInf#changeProduct(com.shop.model.Product)
	 */
	@Override
	public void changeProduct(Product product) {
		try
		{	
			
			Product p = (Product)getSession().get(Product.class, product.getProductId());
			int  i = p.getTotals()-p.getRemain();//成交量量
			p = (Product)product.clone();
			p.setRemain(p.getTotals()-i);
			getSession().merge(p);
			FirstLevelType f = (FirstLevelType)getSession().get(FirstLevelType.class,product.getType().getFirstId());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteProduct(int id) {
		getSession().delete(getSession().get(Product.class, id));
		
	}

	@Override
	public List<Product> findProByTypeOrderByPriceAsc(int level, int id,
			int firstResult, int maxResult) {
		String str="";
		if(level == 1)
		{str ="from Product p where p.type.firstId=:id";
		
		
	}
	else if(level == 2)
	{
		str ="from Product p where p.type.secondLevelType.secondId=:id";
	}
	else if(level == 3){
		str ="from Product p where p.type.secondLevelType.thirdLevelType.thirdId=:id";
	}
	str+=" order by p.price  asc";
	Query q = getSession().createQuery(str);
	q.setParameter("id", id);
	q.setFirstResult(firstResult);
	q.setMaxResults(maxResult);
	
	List<Product> pList = (List<Product>) q.list();
	if(pList.size()>0)
	{
		return pList;
	}
	return null;
		
	}

	@Override
	public List<Product> findProByTypeOrderByScansAsc(int level, int id,
			int firstResult, int maxResult) {
		String str="";
		if(level == 1)
		{str ="from Product p where p.type.firstId=:id";
		
		
	}
	else if(level == 2)
	{
		str ="from Product p where p.type.secondLevelType.secondId=:id";
	}
	else if(level == 3){
		str ="from Product p where p.type.secondLevelType.thirdLevelType.thirdId=:id";
	}
	str+=" order by p.scans asc";
	Query q = getSession().createQuery(str);
	q.setParameter("id", id);
	q.setFirstResult(firstResult);
	q.setMaxResults(maxResult);
	
	List<Product> pList = (List<Product>) q.list();
	if(pList.size()>0)
	{
		return pList;
	}
	return null;
	}

	@Override
	public List<Product> findProByKeyOrderByPriceDesc(String search,
			int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.or(Restrictions.like("productName", search,MatchMode.ANYWHERE), 
										Restrictions.like("simplyIntro", search,MatchMode.ANYWHERE)));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		criteria.addOrder(Order.desc("price"));
		List<Product> pList = (List<Product>) criteria.list();
		if(pList.size()>0)
		{
			return pList;
		}
		return null;
	}

	@Override
	public List<Product> findProByKeyOrderByPriceAsc(String search,
			int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.or(Restrictions.like("productName", search,MatchMode.ANYWHERE), 
										Restrictions.like("simplyIntro", search,MatchMode.ANYWHERE)));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		criteria.addOrder(Order.asc("price"));
		List<Product> pList = (List<Product>) criteria.list();
		if(pList.size()>0)
		{
			return pList;
		}
		return null;
	}

	@Override
	public List<Product> findProByKeyOrderByScansDesc(String search,
			int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.or(Restrictions.like("productName", search,MatchMode.ANYWHERE), 
										Restrictions.like("simplyIntro", search,MatchMode.ANYWHERE)));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		criteria.addOrder(Order.desc("scans"));
		List<Product> pList = (List<Product>) criteria.list();
		if(pList.size()>0)
		{
			return pList;
		}
		return null;
	}

	@Override
	public List<Product> findProByKeyOrderByScansAsc(String search,
			int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.or(Restrictions.like("productName", search,MatchMode.ANYWHERE), 
										Restrictions.like("simplyIntro", search,MatchMode.ANYWHERE)));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		criteria.addOrder(Order.asc("scans"));
		List<Product> pList = (List<Product>) criteria.list();
		if(pList.size()>0)
		{
			return pList;
		}
		return null;
	}

	@Override
	public List<Product> findProByScans() {
		String hql = "from Product p order by p.scans desc";
		Query q = getSession().createQuery(hql);
		
		List<Product> list = (List<Product>)q.list();
		if(list.size()>0)
		{	
			return list;
		}
		return null;
	}

	@Override
	public List<Product> findProByIndent() {
		
		String hql = "from Product p order by p.indents.size desc";
		Query q = getSession().createQuery(hql);
		
		List<Product> list = (List<Product>)q.list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
		
	}

	@Override
	public Product findProById(int id) {
		return (Product)getSession().get(Product.class, id);
	}

	@Override
	public List<Product> findProByTypeAndKey(int typeId, int level,String key,int firstResult, int maxResult) {
				String str="";
					if(level == 1)
					{
					str ="from Product p where p.type.firstId=? and(p.productName like ? or p.simplyIntro like ?)";
					}
				else if(level == 2)
				{
					str ="from Product p where p.type.secondLevelType.secondId=? and(p.productName like ? or p.simplyIntro like ?) ";
				}
				
				
				Query q = getSession().createQuery(str);
				q.setParameter(0, typeId);
				q.setParameter(1, "%"+key+"%");
				q.setParameter(2, "%"+key+"%");
				
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
				List<Product> pList = (List<Product>) q.list();
				if(pList.size()>0)
				{
					return pList;
				}
				return null;
		

		
	}

	@Override
	public List<Product> findAllProducts(Product product,int firstResult,int maxResult) {
		String hql = "from Product  p where  1=1  ";
    	if(product.getProductId()!=0)
    		{
    			hql= hql+ " and p.productId = '"+product.getProductId()+"'";
    			
    		}
    	if(product.getProductName()!=null&&!"".equals(product.getProductName()))
    	{
    		hql= hql+ "  and p.productName like '%"+product.getProductName()+"%'";
    	}
    	
    	if(product.getManuDate()!=null&&!"".equals(product.getManuDate()))
    	{
    		hql= hql+ " and p.manuDate = '"+product.getManuDate()+"'";
    	}
		Query  q = getSession().createQuery(hql);
		q.setFirstResult(firstResult);
		q.setMaxResults(maxResult);
		List<Product> p  = q.list();
		if(p.size()>0)
		{
			return p;
		}
		return null;
	}

	public int  getSize(Product product) {
		String hql = "from Product  p where  1=1  ";
    	if(product.getProductId()!=0)
    		{
    			hql= hql+ " and p.productId = '"+product.getProductId()+"'";
    			
    		}
    	if(product.getProductName()!=null&&!"".equals(product.getProductName()))
    	{
    		hql= hql+ "  and p.productName like '%"+product.getProductName()+"%'";
    	}
    	
    	if(product.getManuDate()!=null&&!"".equals(product.getManuDate()))
    	{
    		hql= hql+ " and p.manuDate = '"+product.getManuDate()+"'";
    	}
		Query  q = getSession().createQuery(hql);
		List<Product> p  = q.list();
		if(p.size()>0)
		{
			return p.size();
		}
		return 0;
	}

	@Override
	public List<Product> findProByCarr() {
		String hql = "from Product p order by p.carrDate asc";
		Query q = getSession().createQuery(hql);
		
		List<Product> list = (List<Product>)q.list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}

	@Override
	public List<Product> findProByTypeAndKeyOrderByPriceAes(int typeId,
			int level, String key, int firstResult, int maxResult) {
					String str="";
					if(level == 1)
					{
					str ="from Product p where p.type.firstId=:id and (p.productName like :name1 or p.simplyIntro like :name2)";
					}
				else if(level == 2)
				{
					str ="from Product p where p.type.secondLevelType.secondId=:id and (p.productName like :name1 or p.simplyIntro like :name2) ";
				}
				
				str += " order by p.price asc";
				Query q = getSession().createQuery(str);
				q.setParameter("id", typeId);
				q.setParameter("name1", "%"+key+"%");
				q.setParameter("name2", "%"+key+"%");
				
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
				List<Product> pList = (List<Product>) q.list();
				if(pList.size()>0)
				{
					return pList;
				}
				return null;

		
	}

	@Override
	public List<Product> findProByTypeAndKeyOrderBypriceDesc(int typeId,
			int level, String key, int firstResult, int maxResult) {
					String str="";
					if(level == 1)
					{
					str ="from Product p where p.type.firstId=? and(p.productName like ? or p.simplyIntro like ?)";
					}
				else if(level == 2)
				{
					str ="from Product p where p.type.secondLevelType.secondId=? and(p.productName like ? or p.simplyIntro like ?) ";
				}
				
				str += "order by p.price desc";
				Query q = getSession().createQuery(str);
				q.setParameter(0, typeId);
				q.setParameter(1, "%"+key+"%");
				q.setParameter(2, "%"+key+"%");
				
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
				List<Product> pList = (List<Product>) q.list();
				if(pList.size()>0)
				{
					return pList;
				}
				return null;
	}

	@Override
	public List<Product> findProByTypeAndKeyOrderByScansAes(int typeId,
			int level, String key, int firstResult, int maxResult) {
				String str="";
				if(level == 1)
				{
				str ="from Product p where p.type.firstId=? and(p.productName like ? or p.simplyIntro like ?)";
				}
			else if(level == 2)
			{
				str ="from Product p where p.type.secondLevelType.secondId=? and(p.productName like ? or p.simplyIntro like ?) ";
			}
			
			str += "order by p.scans asc";
			Query q = getSession().createQuery(str);
			q.setParameter(0, typeId);
			q.setParameter(1, "%"+key+"%");
			q.setParameter(2, "%"+key+"%");
			
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResult);
			List<Product> pList = (List<Product>) q.list();
			if(pList.size()>0)
			{
				return pList;
			}
			return null;
	}

	@Override
	public List<Product> findProByTypeAndKeyOrderByScansDesc(int typeId,
			int level, String key, int firstResult, int maxResult) {
					String str="";
					if(level == 1)
					{
					str ="from Product p where p.type.firstId=? and(p.productName like ? or p.simplyIntro like ?)";
					}
				else if(level == 2)
				{
					str ="from Product p where p.type.secondLevelType.secondId=? and(p.productName like ? or p.simplyIntro like ?) ";
				}
				
				str += "order by p.scans desc";
				Query q = getSession().createQuery(str);
				q.setParameter(0, typeId);
				q.setParameter(1, "%"+key+"%");
				q.setParameter(2, "%"+key+"%");
				
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
				List<Product> pList = (List<Product>) q.list();
				if(pList.size()>0)
				{
					return pList;
				}
				return null;
	}
	
}
