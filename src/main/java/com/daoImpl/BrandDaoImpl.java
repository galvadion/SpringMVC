package com.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BrandDao;
import com.entities.Brand;

@Repository
@Transactional
public class BrandDaoImpl extends GenericDaoImpl<Brand, Integer> implements BrandDao {

	public BrandDaoImpl(){
		super(null);
	}
	
	public BrandDaoImpl(Class<Brand> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
}
