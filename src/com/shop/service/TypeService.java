package com.shop.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.TypeDao;
import com.shop.daoInf.TypeDaoInf;
import com.shop.model.FirstLevelType;
import com.shop.model.SecondLevelType;

@Service("typeService")
public class TypeService {
	@Resource(name =  "typeDao")
	private TypeDaoInf typeDao;
	public List<SecondLevelType> findAllTypes(){
		return typeDao.findAllTypes();
	}
	public FirstLevelType findFirstById(int id)
	{
		return typeDao.findFirstById(id);
	}
	
	

}
