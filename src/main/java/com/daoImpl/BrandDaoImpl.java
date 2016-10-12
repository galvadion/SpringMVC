package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BrandDao;
import com.entities.Brand;
import com.entities.User;

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
