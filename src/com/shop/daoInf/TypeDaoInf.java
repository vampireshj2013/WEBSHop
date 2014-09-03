package com.shop.daoInf;

import java.util.List;

import com.shop.model.FirstLevelType;
import com.shop.model.SecondLevelType;
 
public interface TypeDaoInf {
			public FirstLevelType findFirstById(int id);
			public List<SecondLevelType> findAllTypes();
}
