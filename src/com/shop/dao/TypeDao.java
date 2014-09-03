package com.shop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.shop.daoInf.IndentDaoInf;
import com.shop.daoInf.TypeDaoInf;
import com.shop.model.FirstLevelType;
import com.shop.model.Product;
import com.shop.model.SecondLevelType;
@Repository("typeDao")
public class TypeDao extends BaseDao implements TypeDaoInf {

	

	@Override
	public FirstLevelType findFirstById(int id) {
		
		FirstLevelType f = (FirstLevelType)getSession().get(FirstLevelType.class, id);
		return f;
	}

	@Override
	public List<SecondLevelType> findAllTypes() {
		String hql = "from SecondLevelType";
		Query q =  getSession().createQuery(hql);
		return q.list();
	}

	
}
